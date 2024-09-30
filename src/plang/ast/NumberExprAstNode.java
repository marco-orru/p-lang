package plang.ast;

import plang.tokens.NumberToken;

/**
 * Represents a number {@code <expr>} AST node.
 */
public final class NumberExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 4;
    /**
     * The number token contained in this node.
     */
    private final NumberToken number;

    /**
     * Initializes a new {@link NumberExprAstNode}
     *
     * @param number The number token contained in this node.
     */
    public NumberExprAstNode(NumberToken number) {
        this.number = number;
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    /**
     * Gets the {@link NumberToken} contained in this node.
     *
     * @return The number token contained in this node.
     */
    public NumberToken getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number.getValue());
    }
}
