package plang;

import plang.ast.*;
import plang.gen.CodeGenerator;
import plang.gen.Label;
import plang.gen.OpCode;
import plang.tokens.IdentifierToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Implements the P compiler.
 */
public final class Compiler {
    /**
     * The identifier of the assign idlist code emission.
     */
    private static final int IDLIST_ASSIGN = 0;
    /**
     * The identifier of the read idlist code emission.
     */
    private static final int IDLIST_READ = 1;
    /**
     * The identifier of the print exprlist code emission.
     */
    private static final int EXPRLIST_PRINT = 0;
    /**
     * The identifier of the add exprlist code emission.
     */
    private static final int EXPRLIST_ADD = 1;
    /**
     * The identifier of the multiply exprlist code emission.
     */
    private static final int EXPRLIST_MULTIPLY = 2;
    /**
     * The abstract syntax tree of the program
     */
    private final AstNode ast;
    /**
     * The code generator.
     */
    private final CodeGenerator codeGen = new CodeGenerator();

    /**
     * Initializes a new {@link Compiler} for a source file.
     *
     * @param filePath The path of the source file to be compiled.
     */
    public Compiler(String filePath) throws IOException {
        ast = new Parser(filePath).parse();
    }

    /**
     * The entry point of the compiler.
     * <br>
     * The first argument shall be the input file path, and the second argument shall be the output file path.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        try {
            if (!Files.exists(Path.of(args[0])))
                throw new IOException("Unable to find input file: " + args[0]);

            var compiler = new Compiler(args[0]);
            compiler.compile(args[1]);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Compiles the specified
     *
     * @param outPath The output file path.
     * @throws IOException If an I/O exception occurs when generating the code.
     */
    public void compile(String outPath) throws IOException {
        if (ast.getType() != AstNodeType.PROG)
            throw new IllegalStateException("The AST node root should be a program");

        emitProgNode((ProgAstNode) ast);
        codeGen.generate(outPath);
    }

    /**
     * Emits the code for a {@code <prog>} AST node.
     *
     * @param prog The {@code <prog>} AST node.
     */
    private void emitProgNode(ProgAstNode prog) {
        Label eofLabel = new Label();
        emitStatListNode(prog.getStatList(), eofLabel);
        codeGen.addLabel(eofLabel);
    }

    /**
     * Emits the code for a {@code <statlist>} AST node.
     *
     * @param statList The {@code <statlist>} AST node.
     * @param eofLabel The end-of-file label.
     */
    private void emitStatListNode(StatListAstNode statList, Label eofLabel) {
        Label nextStatLabel = new Label();
        emitStatNode(statList.getStat(), nextStatLabel);
        codeGen.addLabel(nextStatLabel);
        emitStatListExNode(statList.getStatListEx());
        codeGen.addGotoInstruction(eofLabel);
    }

    /**
     * Emits the code for a {@code <statlistex>} AST node.
     *
     * @param statListEx The {@code <statlistex>} AST node.
     */
    private void emitStatListExNode(StatListExAstNode statListEx) {
        switch (statListEx.getProductionId()) {
            case DefaultStatListExAstNode.ID -> {
                DefaultStatListExAstNode defaultStatListEx = (DefaultStatListExAstNode) statListEx;
                Label nextStatLabel = new Label();
                emitStatNode(defaultStatListEx.getStat(), nextStatLabel);
                codeGen.addLabel(nextStatLabel);
                emitStatListExNode(defaultStatListEx.getStatListEx());
            }
            case EmptyStatListExAstNode.ID -> {
            }
            default ->
                    throw new IllegalStateException("Unexpected <statlist> production id: " + statListEx.getProductionId());
        }
    }

    /**
     * Emits the code for a {@code <stat>} AST node.
     *
     * @param stat          The {@code <stat>} AST node.
     * @param nextStatLabel The label at which the next statement is defined.
     */
    private void emitStatNode(StatAstNode stat, Label nextStatLabel) {
        switch (stat.getProductionId()) {
            case AssignStatAstNode.ID -> {
                AssignStatAstNode assignStat = (AssignStatAstNode) stat;
                emitExprNode(assignStat.getExpr());
                emitIdListNode(assignStat.getIdList(), IDLIST_ASSIGN);
                codeGen.addGotoInstruction(nextStatLabel);
            }
            case PrintStatAstNode.ID -> {
                PrintStatAstNode printStat = (PrintStatAstNode) stat;
                emitExprListNode(printStat.getExprList(), EXPRLIST_PRINT);
                codeGen.addGotoInstruction(nextStatLabel);
            }
            case ReadStatAstNode.ID -> {
                ReadStatAstNode readStat = (ReadStatAstNode) stat;
                codeGen.addReadInstruction();
                emitIdListNode(readStat.getIdList(), IDLIST_READ);
                codeGen.addGotoInstruction(nextStatLabel);
            }
            case WhileStatAstNode.ID -> {
                WhileStatAstNode whileStat = (WhileStatAstNode) stat;
                Label trueLabel = new Label();
                Label falseLabel = nextStatLabel;
                nextStatLabel = new Label();
                codeGen.addLabel(nextStatLabel);
                emitBoolExprNode(whileStat.getBoolExpr(), trueLabel, falseLabel);
                codeGen.addLabel(trueLabel);
                emitStatNode(whileStat.getStat(), nextStatLabel);
            }
            case IfStatAstNode.ID -> {
                IfStatAstNode ifStat = (IfStatAstNode) stat;
                Label trueLabel = new Label();
                Label falseLabel = new Label();
                emitBoolExprNode(ifStat.getBoolExpr(), trueLabel, falseLabel);
                codeGen.addLabel(trueLabel);
                emitStatNode(ifStat.getStat(), nextStatLabel);
                codeGen.addLabel(falseLabel);
                emitIfExNode(ifStat.getIfEx(), nextStatLabel);
            }
            case BlockStatAstNode.ID -> {
                BlockStatAstNode blockStat = (BlockStatAstNode) stat;
                emitStatListNode(blockStat.getStatList(), nextStatLabel);
            }
            default -> throw new IllegalStateException("Unexpected <stat> production id: " + stat.getProductionId());
        }
    }

    /**
     * Emits the code for a {@code <ifex>} AST node.
     *
     * @param ifEx      The {@code <ifex>} AST node.
     * @param nextLabel The label of the first instruction after the if block.
     */
    private void emitIfExNode(IfExAstNode ifEx, Label nextLabel) {
        switch (ifEx.getProductionId()) {
            case EndIfExAstNode.ID -> codeGen.addGotoInstruction(nextLabel);
            case ElseIfExAstNode.ID -> {
                ElseIfExAstNode elseIfEx = (ElseIfExAstNode) ifEx;
                emitStatNode(elseIfEx.getStat(), nextLabel);
            }
            default -> throw new IllegalStateException("Unexpected <ifex> production id: " + ifEx.getProductionId());
        }
    }

    /**
     * Emits the code for a {@code <idlist>} AST node.
     *
     * @param idList              The {@code <idlist>} AST node.
     * @param idListFunctionIndex The index of the function that contains the id list.
     */
    private void emitIdListNode(IdListAstNode idList, int idListFunctionIndex) {
        codeGen.addStoreVarInstruction(idList.getIdentifier().getLexeme());
        emitIdListExNode(idList.getIdListEx(), idListFunctionIndex, idList.getIdentifier());
    }

    /**
     * Emits the code for a {@code <idlistex>} AST node.
     *
     * @param idListEx            The {@code <idlistex>} AST node.
     * @param idListFunctionIndex The index of the function that contains the id list.
     * @param identifier          The identifier of the first identifier of the list.
     */
    private void emitIdListExNode(IdListExAstNode idListEx, int idListFunctionIndex, IdentifierToken identifier) {
        switch (idListEx.getProductionId()) {
            case DefaultIdListExAstNode.ID -> {
                DefaultIdListExAstNode defaultIdListEx = (DefaultIdListExAstNode) idListEx;

                switch (idListFunctionIndex) {
                    case IDLIST_READ -> codeGen.addReadInstruction();
                    case IDLIST_ASSIGN -> codeGen.addLoadVarInstruction(identifier.getLexeme());
                }

                codeGen.addStoreVarInstruction(defaultIdListEx.getIdentifier().getLexeme());
                emitIdListExNode(defaultIdListEx.getIdListEx(), idListFunctionIndex, identifier);
            }
            case EmptyIdListExAstNode.ID -> {
            }
            default ->
                    throw new IllegalStateException("Unexpected <idlistex> production id: " + idListEx.getProductionId());
        }
    }

    /**
     * Emits the code for a {@code <boolexpr>} AST node.
     *
     * @param boolExpr   The {@code <boolexpr>} AST node.
     * @param trueLabel  The label to go to if the condition is true.
     * @param falseLabel The label to go to if the condition is false.
     */
    private void emitBoolExprNode(BoolExprAstNode boolExpr, Label trueLabel, Label falseLabel) {
        switch (boolExpr.getProductionId()) {
            case EqBoolExprAstNode.ID -> {
                EqBoolExprAstNode eqBoolExpr = (EqBoolExprAstNode) boolExpr;
                emitExprNode(eqBoolExpr.getExpr1());
                emitExprNode(eqBoolExpr.getExpr2());
                codeGen.addInstruction(OpCode.IF_NE, falseLabel);
            }
            case NeBoolExprAstNode.ID -> {
                NeBoolExprAstNode neBoolExpr = (NeBoolExprAstNode) boolExpr;
                emitExprNode(neBoolExpr.getExpr1());
                emitExprNode(neBoolExpr.getExpr2());
                codeGen.addInstruction(OpCode.IF_EQ, trueLabel);
            }
            case LtBoolExprAstNode.ID -> {
                LtBoolExprAstNode ltBoolExpr = (LtBoolExprAstNode) boolExpr;
                emitExprNode(ltBoolExpr.getExpr1());
                emitExprNode(ltBoolExpr.getExpr2());
                codeGen.addInstruction(OpCode.IF_GE, falseLabel);
            }
            case LeBoolExprAstNode.ID -> {
                LeBoolExprAstNode leBoolExpr = (LeBoolExprAstNode) boolExpr;
                emitExprNode(leBoolExpr.getExpr1());
                emitExprNode(leBoolExpr.getExpr2());
                codeGen.addInstruction(OpCode.IF_GT, falseLabel);
            }
            case GtBoolExprAstNode.ID -> {
                GtBoolExprAstNode gtBoolExpr = (GtBoolExprAstNode) boolExpr;
                emitExprNode(gtBoolExpr.getExpr1());
                emitExprNode(gtBoolExpr.getExpr2());
                codeGen.addInstruction(OpCode.IF_LE, falseLabel);
            }
            case GeBoolExprAstNode.ID -> {
                GeBoolExprAstNode geBoolExpr = (GeBoolExprAstNode) boolExpr;
                emitExprNode(geBoolExpr.getExpr1());
                emitExprNode(geBoolExpr.getExpr2());
                codeGen.addInstruction(OpCode.IF_LT, falseLabel);
            }
            case AndBoolExprAstNode.ID -> {
                AndBoolExprAstNode andBoolExpr = (AndBoolExprAstNode) boolExpr;
                Label nextBoolExprLabel = new Label();
                emitBoolExprNode(andBoolExpr.getBoolExpr1(), nextBoolExprLabel, falseLabel);
                codeGen.addLabel(nextBoolExprLabel);
                emitBoolExprNode(andBoolExpr.getBoolExpr2(), trueLabel, falseLabel);
            }
            case OrBoolExprAstNode.ID -> {
                OrBoolExprAstNode orBoolExpr = (OrBoolExprAstNode) boolExpr;
                Label nextBoolExprLabel = new Label();
                emitBoolExprNode(orBoolExpr.getBoolExpr1(), trueLabel, nextBoolExprLabel);
                codeGen.addGotoInstruction(trueLabel);
                codeGen.addLabel(nextBoolExprLabel);
                emitBoolExprNode(orBoolExpr.getBoolExpr2(), trueLabel, falseLabel);
            }
            case NotBoolExprAstNode.ID -> {
                NotBoolExprAstNode notBoolExpr = (NotBoolExprAstNode) boolExpr;
                emitBoolExprNode(notBoolExpr.getBoolExpr(), falseLabel, trueLabel);
                codeGen.addGotoInstruction(falseLabel);
            }
            default ->
                    throw new IllegalStateException("Unexpected <boolexpr> production id: " + boolExpr.getProductionId());
        }
    }

    /**
     * Emits the code for a {@code <expr>} AST node.
     *
     * @param expr The {@code <expr>} AST node.
     */
    private void emitExprNode(ExprAstNode expr) {
        switch (expr.getProductionId()) {
            case AddExprAstNode.ID -> {
                AddExprAstNode addExpr = (AddExprAstNode) expr;
                emitExprListNode(addExpr.getExprList(), EXPRLIST_ADD);
            }
            case SubExprAstNode.ID -> {
                SubExprAstNode subExpr = (SubExprAstNode) expr;
                emitExprNode(subExpr.getExpr1());
                emitExprNode(subExpr.getExpr2());
                codeGen.addInstruction(OpCode.SUBTRACT);
            }
            case MulExprAstNode.ID -> {
                MulExprAstNode mulExpr = (MulExprAstNode) expr;
                emitExprListNode(mulExpr.getExprList(), EXPRLIST_MULTIPLY);
            }
            case DivExprAstNode.ID -> {
                DivExprAstNode divExpr = (DivExprAstNode) expr;
                emitExprNode(divExpr.getExpr1());
                emitExprNode(divExpr.getExpr2());
                codeGen.addInstruction(OpCode.DIVIDE);
            }
            case NumberExprAstNode.ID -> {
                NumberExprAstNode numberExpr = (NumberExprAstNode) expr;
                codeGen.addInstruction(OpCode.LOAD_CONST, numberExpr.getNumber().getValue());
            }
            case IdentifierExprAstNode.ID -> {
                IdentifierExprAstNode identifierExpr = (IdentifierExprAstNode) expr;
                codeGen.addLoadVarInstruction(identifierExpr.getIdentifier().getLexeme());
            }
            default -> throw new IllegalStateException("Unexpected <expr> production id: " + expr.getProductionId());
        }
    }

    /**
     * Emits the code for a {@code <exprlist>} AST node.
     *
     * @param exprList              The {@code <exprlist>} AST node.
     * @param exprListFunctionIndex The index of the function that contains this expression list.
     */
    private void emitExprListNode(ExprListAstNode exprList, int exprListFunctionIndex) {
        emitExprNode(exprList.getExpr());

        if (exprListFunctionIndex == EXPRLIST_PRINT)
            codeGen.addPrintInstruction();

        emitExprListExNode(exprList.getExprListEx(), exprListFunctionIndex);
    }

    private void emitExprListExNode(ExprListExAstNode exprListEx, int exprListFunctionId) {
        switch (exprListEx.getProductionId()) {
            case DefaultExprListExAstNode.ID -> {
                DefaultExprListExAstNode defaultExprListEx = (DefaultExprListExAstNode) exprListEx;
                emitExprNode(defaultExprListEx.getExpr());

                switch (exprListFunctionId) {
                    case EXPRLIST_PRINT -> codeGen.addPrintInstruction();
                    case EXPRLIST_ADD -> codeGen.addInstruction(OpCode.ADD);
                    case EXPRLIST_MULTIPLY -> codeGen.addInstruction(OpCode.MULTIPLY);
                }

                emitExprListExNode(defaultExprListEx.getExprListEx(), exprListFunctionId);
            }
            case EmptyExprListExAstNode.ID -> {
            }
            default ->
                    throw new IllegalStateException("Unexpected <exprlistex> production id: " + exprListEx.getProductionId());
        }
    }
}
