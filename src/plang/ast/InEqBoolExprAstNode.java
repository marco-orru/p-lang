package plang.ast;

/**
 * Represents an inequality {@code <boolexpr>} AST node.
 */
public final class InEqBoolExprAstNode extends BoolExprAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 4;

    /**
     * Initializes a new {@link InEqBoolExprAstNode}.
     *
     * @param expr1 The first {@code <expr>} child node.
     * @param expr2 The second {@code <expr>} child node.
     */
    public InEqBoolExprAstNode(ExprAstNode expr1, ExprAstNode expr2) {
        super(expr1, expr2);
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
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "<>" + " " + getExpr1() + " " + getExpr2();
    }
}

