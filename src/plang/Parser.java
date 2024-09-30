package plang;

import plang.ast.*;
import plang.tokens.IdentifierToken;
import plang.tokens.NumberToken;
import plang.tokens.Token;
import plang.tokens.TokenKind;

import java.io.IOException;

/**
 * Implements a recursive descent parser, which parses a sequence of
 * tokens into an AST (Abstract Syntax Tree) following the P language grammar.
 */
public final class Parser {
    /**
     * The lexer that tokenize the source code.
     */
    private final Lexer lexer;
    /**
     * The lookahead token (e.g., the last read token).
     */
    private Token lookahead;

    /**
     * Initializes a new {@link Parser} for the specified source file.
     *
     * @param filePath The path of the P source code file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public Parser(String filePath) throws IOException {
        this.lexer = new Lexer(filePath);
    }

    /**
     * Updates the token lookahead with the next token from the lexer.
     *
     * @throws IOException If the lexer throws a {@link IOException}.
     */
    private void move() throws IOException {
        this.lookahead = lexer.nextToken();
    }

    private void match(TokenKind tokenKind) throws IOException {
        if (lookahead.getKind() == tokenKind) {
            if (tokenKind != TokenKind.END_OF_FILE) move();
        } else throwIOException("Unexpected token (expected '" + tokenKind + "', got '" + lookahead.getKind() + "')");
    }

    /**
     * Parses the input and returns the root of the Abstract Syntax Tree.
     *
     * @return The root node of the AST.
     * @throws IOException If the lexer throws a {@link IOException} or an invalid token is found.
     */
    public AstNode parse() throws IOException {
        move();
        return parseProg();
    }

    private ProgAstNode parseProg() throws IOException {
        switch (lookahead.getKind()) {
            case KWD_ASSIGN:
            case KWD_PRINT:
            case KWD_READ:
            case KWD_WHILE:
            case KWD_IF:
            case LEFT_BRACE:
                StatListAstNode statList = parseStatList();
                match(TokenKind.END_OF_FILE);
                return new ProgAstNode(statList);

            default:
                throwIOException("Unexpected token in program'" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private StatListAstNode parseStatList() throws IOException {
        switch (lookahead.getKind()) {
            case KWD_ASSIGN:
            case KWD_PRINT:
            case KWD_READ:
            case KWD_WHILE:
            case KWD_IF:
            case LEFT_BRACE:
                StatAstNode stat = parseStat();
                StatListExAstNode statListEx = parseStatListEx();
                return new StatListAstNode(stat, statListEx);

            default:
                throwIOException("Unexpected token in statement list '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private StatListExAstNode parseStatListEx() throws IOException {
        switch (lookahead.getKind()) {
            case SEMICOLON:
                match(TokenKind.SEMICOLON);
                StatAstNode stat = parseStat();
                StatListExAstNode statListEx = parseStatListEx();
                return new DefaultStatListExAstNode(stat, statListEx);

            case END_OF_FILE:
            case RIGHT_BRACE:
                return new EmptyStatListExAstNode();

            default:
                throwIOException("Unexpected token in statement list'" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private StatAstNode parseStat() throws IOException {
        IdListAstNode idList;
        BoolExprAstNode boolExpr;
        StatAstNode stat;

        switch (lookahead.getKind()) {
            case KWD_ASSIGN:
                match(TokenKind.KWD_ASSIGN);
                ExprAstNode expr = parseExpr();
                match(TokenKind.KWD_TO);
                idList = parseIdList();
                return new AssignStatAstNode(expr, idList);

            case KWD_PRINT:
                match(TokenKind.KWD_PRINT);
                match(TokenKind.LEFT_PARENTHESIS);
                ExprListAstNode exprList = parseExprList();
                match(TokenKind.RIGHT_PARENTHESIS);
                return new PrintStatAstNode(exprList);

            case KWD_READ:
                match(TokenKind.KWD_READ);
                match(TokenKind.LEFT_PARENTHESIS);
                idList = parseIdList();
                match(TokenKind.RIGHT_PARENTHESIS);
                return new ReadStatAstNode(idList);

            case KWD_WHILE:
                match(TokenKind.KWD_WHILE);
                match(TokenKind.LEFT_PARENTHESIS);
                boolExpr = parseBoolExpr();
                match(TokenKind.RIGHT_PARENTHESIS);
                stat = parseStat();
                return new WhileStatAstNode(boolExpr, stat);

            case KWD_IF:
                match(TokenKind.KWD_IF);
                match(TokenKind.LEFT_PARENTHESIS);
                boolExpr = parseBoolExpr();
                match(TokenKind.RIGHT_PARENTHESIS);
                stat = parseStat();
                IfExAstNode ifEx = parseIfEx();
                return new IfStatAstNode(boolExpr, stat, ifEx);

            case LEFT_BRACE:
                match(TokenKind.LEFT_BRACE);
                StatListAstNode statList = parseStatList();
                match(TokenKind.RIGHT_BRACE);
                return new BlockStatAstNode(statList);

            default:
                throwIOException("Unexpected token in statement list '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private IfExAstNode parseIfEx() throws IOException {
        switch (lookahead.getKind()) {
            case KWD_END:
                match(TokenKind.KWD_END);
                return new EndIfExAstNode();

            case KWD_ELSE:
                match(TokenKind.KWD_ELSE);
                StatAstNode stat = parseStat();
                match(TokenKind.KWD_END);
                return new ElseIfExAstNode(stat);

            default:
                throwIOException("Unexpected token in if statement '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    private IdListAstNode parseIdList() throws IOException {
        switch (lookahead.getKind()) {
            case IDENTIFIER:
                IdentifierToken identifier = (IdentifierToken) lookahead;
                match(TokenKind.IDENTIFIER);
                IdListExAstNode idListEx = parseIdListEx();
                return new IdListAstNode(identifier, idListEx);

            default:
                throwIOException("Unexpected token in identifier list '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private IdListExAstNode parseIdListEx() throws IOException {
        switch (lookahead.getKind()) {
            case COMMA:
                match(TokenKind.COMMA);

                if (lookahead.getKind() == TokenKind.IDENTIFIER) {
                    IdentifierToken identifier = (IdentifierToken) lookahead;
                    match(TokenKind.IDENTIFIER);
                    IdListExAstNode idListEx = parseIdListEx();
                    return new DefaultIdListExAstNode(identifier, idListEx);
                }

                throwIOException("Unexpected token in identifier list '" + lookahead.getKind() + "'");
                break;

            case RIGHT_PARENTHESIS:
            case SEMICOLON:
            case END_OF_FILE:
            case KWD_ELSE:
            case RIGHT_BRACE:
            case KWD_END:
                return new EmptyIdListExAstNode();

            default:
                throwIOException("Unexpected token in identifier list '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private BoolExprAstNode parseBoolExpr() throws IOException {
        ExprAstNode expr1;
        ExprAstNode expr2;

        switch (lookahead.getKind()) {
            case EQUALITY:
                match(TokenKind.EQUALITY);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new EqBoolExprAstNode(expr1, expr2);

            case INEQUALITY:
                match(TokenKind.INEQUALITY);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new InEqBoolExprAstNode(expr1, expr2);

            case LESS_THAN:
                match(TokenKind.LESS_THAN);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new LtBoolExprAstNode(expr1, expr2);

            case GREATER_THAN:
                match(TokenKind.GREATER_THAN);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new GtBoolExprAstNode(expr1, expr2);

            case LESS_THAN_EQUAL:
                match(TokenKind.LESS_THAN_EQUAL);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new LeBoolExprAstNode(expr1, expr2);

            case GREATER_THAN_EQUAL:
                match(TokenKind.GREATER_THAN_EQUAL);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new GeBoolExprAstNode(expr1, expr2);

            case LOGICAL_AND:
                match(TokenKind.LOGICAL_AND);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new AndBoolExprAstNode(expr1, expr2);

            case LOGICAL_OR:
                match(TokenKind.LOGICAL_OR);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new OrBoolExprAstNode(expr1, expr2);

            case LOGICAL_NOT:
                match(TokenKind.LOGICAL_NOT);
                expr1 = parseExpr();
                return new NotBoolExprAstNode(expr1);

            default:
                throwIOException("Unexpected token in boolean expression '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private ExprAstNode parseExpr() throws IOException {
        ExprListAstNode exprList;
        ExprAstNode expr1;
        ExprAstNode expr2;

        switch (lookahead.getKind()) {
            case PLUS:
                match(TokenKind.PLUS);
                match(TokenKind.LEFT_PARENTHESIS);
                exprList = parseExprList();
                match(TokenKind.RIGHT_PARENTHESIS);
                return new PlusExprAstNode(exprList);

            case MINUS:
                match(TokenKind.MINUS);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new MinusExprAstNode(expr1, expr2);

            case ASTERISK:
                match(TokenKind.ASTERISK);
                match(TokenKind.LEFT_PARENTHESIS);
                exprList = parseExprList();
                match(TokenKind.RIGHT_PARENTHESIS);
                return new MultiplyExprAstNode(exprList);

            case SLASH:
                match(TokenKind.SLASH);
                expr1 = parseExpr();
                expr2 = parseExpr();
                return new DivExprAstNode(expr1, expr2);

            case NUMBER:
                NumberToken number = (NumberToken) lookahead;
                match(TokenKind.NUMBER);
                return new NumberExprAstNode(number);

            case IDENTIFIER:
                IdentifierToken identifier = (IdentifierToken) lookahead;
                match(TokenKind.IDENTIFIER);
                return new IdentifierExprAstNode(identifier);

            default:
                throwIOException("Unexpected token in expression '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private ExprListAstNode parseExprList() throws IOException {
        switch (lookahead.getKind()) {
            case PLUS:
            case MINUS:
            case ASTERISK:
            case SLASH:
            case IDENTIFIER:
            case NUMBER:
                ExprAstNode expr = parseExpr();
                ExprListExAstNode exprListEx = parseExprListEx();
                return new ExprListAstNode(expr, exprListEx);

            default:
                throwIOException("Unexpected token in expression list '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    private ExprListExAstNode parseExprListEx() throws IOException {
        switch (lookahead.getKind()) {
            case COMMA:
                match(TokenKind.COMMA);
                ExprAstNode expr = parseExpr();
                ExprListExAstNode exprListEx = parseExprListEx();
                return new DefaultExprListExAstNode(expr, exprListEx);

            case RIGHT_PARENTHESIS:
                return new EmptyExprListExAstNode();

            default:
                throwIOException("Unexpected token in expression list '" + lookahead.getKind() + "'");
                break;
        }

        return null;
    }

    /**
     * Throws an IO exception with extended info about the current position in the source file and a custom message.
     *
     * @param message A message that describes the cause of the exception.
     * @throws IOException Always.
     */
    private void throwIOException(String message) throws IOException {
        throw new IOException("<" + lexer.getFileName() + "[" + lexer.getLastLine() + ":" + lexer.getLastColumn() + "]> " + message);
    }
}
