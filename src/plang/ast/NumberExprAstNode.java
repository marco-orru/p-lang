package plang.ast;

import plang.tokens.NumberToken;

/**
 * Represents a number {@code <expr>} AST node.
 */
public final class NumberExprAstNode extends ExprAstNode {
    /** The ID of this production */
    public final int ID = 4;

    @Override
    public int getProductionId() {
        return ID;
    }

    /** The number token contained in this node. */
    private final NumberToken number;

    /**
     * Initializes a new {@link NumberExprAstNode}
     * @param number The number token contained in this node.
     */
    public NumberExprAstNode(NumberToken number) {
        this.number = number;
    }

    /**
     * Gets the {@link NumberToken} contained in this node.
     * @return The number token contained in this node.
     */
    public NumberToken getNumber() {
        return this.number;
    }
}
