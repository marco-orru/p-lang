package plang;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the common tokens of the language.
 */
public final class CommonTokens {
    /** Cache for common allocated tokens. */
    private final static Map<TokenKind, Token> commonTokens = new HashMap<>();

    /**
     * Gets the common token associated with the specified kind.
     * @param kind The kind of token.
     * @return The token of the specified kind.
     * @throws IllegalArgumentException If the specified kind determines a dynamic token
     * (e.g., {@link NumberToken} or {@link IdentifierToken}).
     */
    public Token get(TokenKind kind) {
        if (kind == TokenKind.NUMBER || kind == TokenKind.IDENTIFIER)
            throw new IllegalArgumentException("A token of the specified kind is dynamic and cannot be cached");

        if (commonTokens.containsKey(kind)) {
            return commonTokens.get(kind);
        }

        var token = new Token(kind);
        commonTokens.put(kind, token);
        return token;
    }
}
