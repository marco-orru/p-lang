package plang.ast;

/**
 * Represents a default {@code <exprlistex>} AST node.
 */
public final class DefaultExprListExAstNode extends ExprListExAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 0;

    /**
     * Initializes a new {@link DefaultExprListExAstNode}.
     *
     * @param expr       The {@link ExprAstNode} child node.
     * @param exprListEx The {@link ExprListExAstNode} child node.
     */
    public DefaultExprListExAstNode(ExprAstNode expr, ExprListExAstNode exprListEx) {
        super(expr, exprListEx);
    }

    /**
     * Gets the {@link ExprAstNode} child node.
     *
     * @return The {@code <expr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public ExprAstNode getExpr() {
        return (ExprAstNode) childNodes.get(0);
    }

    /**
     * Gets the {@link ExprListExAstNode} child node.
     *
     * @return The {@code <exprlistex>} child node.
     */
    public ExprListExAstNode getExprListEx() {
        return (ExprListExAstNode) childNodes.get(1);
    }

    @Override
    public int getProductionId() {
        return 0;
    }

    @Override
    public String toString() {
        return "," + " " + getExpr() + " " + getExprListEx();
    }
}
