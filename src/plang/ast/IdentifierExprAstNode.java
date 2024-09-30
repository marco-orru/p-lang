package plang.ast;

import plang.tokens.IdentifierToken;

public final class IdentifierExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 5;

    /**
     * The identifier token contained in this node
     */
    private final IdentifierToken identifier;

    /**
     * Initializes a new {@link IdentifierExprAstNode}.
     *
     * @param identifier The identifier token contained in this node.
     */
    public IdentifierExprAstNode(IdentifierToken identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the {@link IdentifierToken} contained in this node.
     *
     * @return The identifier token contained in this node.
     */
    public IdentifierToken getIdentifier() {
        return this.identifier;
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return identifier.getLexeme();
    }
}
