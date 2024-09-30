package plang.gen;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements an IJVM code generator.
 */
public final class CodeGenerator {
    /**
     * The queue of entities to be generated
     */
    private final List<CodeGenEntity> entities = new LinkedList<>();
    /**
     * The symbol table
     */
    private final SymbolTable symbolTable = new SymbolTable();

    /**
     * Adds an instruction to be generated.
     *
     * @param opCode The opcode of the instruction
     */
    public void addInstruction(OpCode opCode) {
        entities.add(new Instruction<>(opCode));
    }

    /**
     * Adds an instruction with a label as an operand.
     *
     * @param opcode The opcode of the instruction.
     * @param label  The label operand of the opcode.
     */
    public void addInstruction(OpCode opcode, Label label) {
        entities.add(new Instruction<>(opcode, Label.LABEL_PREFIX + label.getId()));
    }

    /**
     * Adds an instruction with an integer operand.
     *
     * @param opcode  The opcode of the instruction.
     * @param operand The integer operand of the instruction.
     */
    public void addInstruction(OpCode opcode, int operand) {
        entities.add(new Instruction<>(opcode, operand));
    }

    /**
     * Adds a {@code goto} instruction to be generated.
     *
     * @param label The label to go to.
     */
    public void addGotoInstruction(Label label) {
        entities.add(new Instruction<>(OpCode.GOTO, Label.LABEL_PREFIX + label.getId()));
    }

    /**
     * Adds a {@code read} instruction.
     */
    public void addReadInstruction() {
        entities.add(new Instruction<>(OpCode.INVOKE_STATIC, Instruction.READ_METHOD_ID));
    }

    /**
     * Adds a {@code print} instruction.
     */
    public void addPrintInstruction() {
        entities.add(new Instruction<>(OpCode.INVOKE_STATIC, Instruction.PRINT_METHOD_ID));
    }

    /**
     * Adds a {@code istore} instruction
     *
     * @param varName The name of the variable to store.
     */
    public void addStoreVarInstruction(String varName) {
        entities.add(new Instruction<>(OpCode.STORE_VAR, symbolTable.get(varName)));
    }

    /**
     * Adds a {@code iload} instruction.
     *
     * @param varName The name of the variable to load.
     */
    public void addLoadVarInstruction(String varName) {
        entities.add(new Instruction<>(OpCode.LOAD_VAR, symbolTable.lookup(varName)));
    }

    /**
     * Adds a label to be generated.
     *
     * @param label The label to be generated.
     */
    public void addLabel(Label label) {
        entities.add(label);
    }

    /**
     * Generates the code into the file at the specified path.
     *
     * @param outFilePath The path of the output file.
     * @throws IOException If an I/O exception occurs when generating the code.
     */
    public void generate(String outFilePath) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(outFilePath, false));
        br.write(header);

        for (CodeGenEntity entity : entities)
            br.write(entity + "\n");

        br.write(footer);
        br.close();
    }

    /** The header of an output file */
    private static final String header = """
            .class public Output
            .super java/lang/Object
            
            .method public <init>()V
             aload_0
             invokenonvirtual java/lang/Object/<init>()V
             return
            .end method
            
            .method public static print(I)V
             .limit stack 2
             getstatic java/lang/System/out Ljava/io/PrintStream;
             iload_0
             invokestatic java/lang/Integer/toString(I)Ljava/lang/String;
             invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
             return
            .end method
            
            .method public static read()I
             .limit stack 3
             new java/util/Scanner
             dup
             getstatic java/lang/System/in Ljava/io/InputStream;
             invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
             invokevirtual java/util/Scanner/next()Ljava/lang/String;
             invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I
             ireturn
            .end method
            
            .method public static run()V
             .limit stack 1024
             .limit locals 256
            """;

    /** The footer of an output file */
    private static final String footer = """
             return
            .end method
            
            .method public static main([Ljava/lang/String;)V
             invokestatic Output/run()V
             return
            .end method
            """;
}
