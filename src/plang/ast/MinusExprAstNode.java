package plang.ast;

/**
 * Represents a minus {@code <expr>} AST node.
 */
public final class MinusExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 1;

    /**
     * Initializes a new {@link MinusExprAstNode}.
     *
     * @param expr1 The first {@code <expr>} child node.
     * @param expr2 The second {@code <expr>} child node.
     */
    public MinusExprAstNode(ExprAstNode expr1, ExprAstNode expr2) {
        super(expr1, expr2);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    /**
     * Gets the first {@link ExprAstNode} child node.
     *
     * @return The first {@code <expr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public ExprAstNode getExpr1() {
        return (ExprAstNode) childNodes.get(0);
    }

    /**
     * Gets the second {@link ExprAstNode} child node.
     *
     * @return The second {@code <expr>} child node.
     */
    public ExprAstNode getExpr2() {
        return (ExprAstNode) childNodes.get(1);
    }

    @Override
    public String toString() {
        return "-" + " " + getExpr1() + " " + getExpr2();
    }
}
