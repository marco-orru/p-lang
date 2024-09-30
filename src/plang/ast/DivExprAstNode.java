package plang.ast;

/**
 * Represents a divide {@code <expr>} AST node.
 */
public final class DivExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 3;

    /**
     * Initializes a new {@link DivExprAstNode}.
     *
     * @param expr1 The first {@code <expr>} child node.
     * @param expr2 The second {@code <expr>} child node.
     */
    public DivExprAstNode(ExprAstNode expr1, ExprAstNode expr2) {
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
        return "/" + " " + getExpr1() + " / " + getExpr2();
    }
}
