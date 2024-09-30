package plang.gen;

import java.util.Objects;

/**
 * Represents an IJVM instruction, made of an opcode and an optional operand.
 *
 * @param <T> The type of the operand of the instruction.
 */
public final class Instruction<T> implements CodeGenEntity {
    /**
     * The ID of the {@code read} method to be used with {@link OpCode#INVOKE_STATIC}.
     */
    public static final int READ_METHOD_ID = 0;
    /**
     * The ID of the {@code print} method to be used with {@link OpCode#INVOKE_STATIC}.
     */
    public static final int PRINT_METHOD_ID = 1;

    /**
     * The opcode of the instruction
     */
    private final OpCode opCode;

    /**
     * The (optional) operand of the instruction
     */
    private final T operand;

    /**
     * Initializes a new {@link Instruction} with an opcode.
     *
     * @param opCode The opcode associated with the instruction.
     */
    public Instruction(OpCode opCode) {
        this.opCode = opCode;
        this.operand = null;
    }

    /**
     * Initializes a new {@link Instruction} with an opcode and an operand.
     *
     * @param opCode  The opcode associated with the instruction.
     * @param operand The operand of the opcode.
     */
    public Instruction(OpCode opCode, T operand) {
        this.opCode = opCode;
        this.operand = Objects.requireNonNull(operand);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public String toString() {
        return switch (opCode) {
            case LOAD_CONST -> "\tldc" + " " + operand;
            case INVOKE_STATIC -> {
                if ((Integer) operand == READ_METHOD_ID)
                    yield "\tinvokestatic" + " " + "Output/read()I";
                if ((Integer) operand == PRINT_METHOD_ID)
                    yield "\tinvokestatic" + " " + "Output/print(I)V";

                throw new IllegalStateException("The instruction 'invokestatic' has an invalid operand: '" + operand + "'");
            }
            case ADD -> "\tiadd";
            case MULTIPLY -> "\timul";
            case SUBTRACT -> "\tisub";
            case DIVIDE -> "\tidiv";
            case NEGATE -> "\tineg";
            case STORE_VAR -> "\tistore" + " " + operand;
            case OR -> "\tior";
            case AND -> "\tiand";
            case LOAD_VAR -> "\tiload" + " " + operand;
            case IF_EQ -> "\tif_icmpeq" + " " + operand;
            case IF_NE -> "\tif_icmpne" + " " + operand;
            case IF_LT -> "\tif_icmplt" + " " + operand;
            case IF_GT -> "\tif_icmpgt" + " " + operand;
            case IF_LE -> "\tif_icmple" + " " + operand;
            case IF_GE -> "\tif_icmpge" + " " + operand;
            case IF_NZ -> "\tifne" + " " + operand;
            case GOTO -> "\tgoto" + " " + operand;
        };
    }
}
