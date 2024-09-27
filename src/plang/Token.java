package plang;

/**
 * Represents a token.
 */
public class Token {
    /** The kind of token. */
    private final TokenKind kind;

    /**
     * Initializes a new {@link Token} of the specified kind.
     * @param kind The kind of token.
     */
    public Token(TokenKind kind) {
        this.kind = kind;
    }

    /**
     * Gets the kind of token.
     * @return The kind of token.
     */
    public TokenKind getKind() {
        return this.kind;
    }
}
