package plang.gen;

/**
 * Represents a IJVM opcode.
 */
public enum OpCode {
    /**
     * The {@code ldc} opcode,
     * which loads the integer value specified by its operand into the stack.
     */
    LOAD_CONST,

    /**
     * The {@code invokestatic} opcode,
     * which invokes the static method at the index specified by its operand.
     */
    INVOKE_STATIC,

    /**
     * The {@code iadd} opcode,
     * which pops two integers from the stack, adds them, and pushes the result onto the stack.
     */
    ADD,

    /**
     * The {@code imul} opcode,
     * which pops two integers from the stack, multiplies them, and pushes the result onto the stack.
     */
    MULTIPLY,

    /**
     * The {@code isub} opcode,
     * which pops two integers from the stack, subtracts the second from the first, and pushes the result onto the stack.
     */
    SUBTRACT,

    /**
     * The {@code idiv} opcode,
     * which pops two integers from the stack, divides the first by the second, and pushes the result onto the stack.
     */
    DIVIDE,

    /**
     * The {@code ineg} opcode,
     * which pops an integer from the stack, negates it, and pushes the result onto the stack.
     */
    NEGATE,

    /**
     * The {@code istore} opcode,
     * which pops an integer from the stack and stores it in the variable at the index specified by its operand.
     */
    STORE_VAR,

    /**
     * The {@code ior} opcode,
     * which pops two integers from the stack, performs the bitwise or between them, and pushes the result onto the stack.
     */
    OR,

    /**
     * The {@code iand} opcode,
     * which pops two integers from the stack, performs the bitwise and between them, and pushes the result onto the stack.
     */
    AND,

    /**
     * The {@code iload} opcode,
     * which loads a variable from the index specified by its operand, and pushes its value onto the stack.
     */
    LOAD_VAR,

    /**
     * The {@code if_icmpeq} opcode,
     * which pops two integers from the stack, and jumps to the label specified by its operand if those values are equal.
     */
    IF_EQ,

    /**
     * The {@code if_icmpne} opcode,
     * which pops two integers from the stack, and jumps to the label specified by its operand if those values are not equal.
     */
    IF_NE,

    /**
     * The {@code if_icmplt} opcode,
     * which pops two integers from the stack, and jumps to the label specified by its operand if the first value is less than the second.
     */
    IF_LT,

    /**
     * The {@code if_icmple} opcode,
     * which pops two integers from the stack, and jumps to the label specified by its operand if the first value is less than or equal to the second.
     */
    IF_LE,

    /**
     * The {@code if_icmpgt} opcode,
     * which pops two integers from the stack, and jumps to the label specified by its operand if the first value is greater than the second.
     */
    IF_GT,

    /**
     * The {@code if_icmpge} opcode,
     * which pops two integers from the stack, and jumps to the label specified by its operand if the first value is greater than or equal to the second.
     */
    IF_GE,

    /**
     * The {@code ifne} opcode,
     * which pops an integer from the stack, and jumps to the label specified by its operand if the value is equal to {@code 0}.
     */
    IF_NZ,

    /**
     * The {@code goto} opcode,
     * which unconditionally jumps to the label specified by its operand.
     */
    GOTO,
}
