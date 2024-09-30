package plang.tokens;

/**
 * Represents an identifier.
 * <p>
 * Identifiers are represented as a non-empty sequence of letters, digits and the underscore symbol ({@code _}),
 * that cannot start with a digit and cannot be composed of only the underscore symbol.
 */
public class IdentifierToken extends Token {
    /**
     * The identifier lexeme
     */
    private final String identifier;

    /**
     * Initializes a new {@link IdentifierToken} with the specified identifier lexeme.
     *
     * @param identifier The identifier lexeme.
     */
    public IdentifierToken(String identifier) {
        super(TokenKind.IDENTIFIER);
        this.identifier = identifier;
    }

    /**
     * Gets the identifier lexeme.
     *
     * @return The identifier lexeme.
     */
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + this.identifier;
    }
}
