package plang.tokens;

/**
 * Represents a numerical token.
 * <p>
 * P supports only integral numbers and does not provide facilities to work with floating-point
 * arithmetics. Numbers are stored in 32 bits (a Java integer) and are represented as non-empty
 * sequences of digits.
 */
public class NumberToken extends Token {
    /** The integral value */
    private final int value;

    /**
     * Initializes a new {@link NumberToken} with the specified integral value.
     * @param value The integral value.
     */
    public NumberToken(int value) {
        super(TokenKind.NUMBER);
        this.value = value;
    }

    /**
     * Gets the value of this numerical token.
     * @return The integral value.
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + this.value;
    }
}
