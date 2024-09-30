package plang.ast;

/**
 * Represents a {@code <exprlist>} AST node.
 */
public final class ExprListAstNode extends AstNode {
    /** The ID of this production */
    public final int ID = 0;

    /**
     * Initializes a new {@link ExprListAstNode}
     * @param expr The {@code <expr>} child node.
     * @param exprListEx The {@code <exprlistex>} child node.
     */
    public ExprListAstNode(ExprAstNode expr, ExprListExAstNode exprListEx) {
        super(expr, exprListEx);
    }

    /**
     * Gets the {@link ExprAstNode} child node.
     * @return The {@code <expr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public ExprAstNode getExpr() {
        return (ExprAstNode) childNodes.get(0);
    }

    /**
     * Gets the {@link ExprListExAstNode} child node.
     * @return The {@code <exprlistex>} child node.
     */
    public ExprListExAstNode getExprList() {
        return (ExprListExAstNode) childNodes.get(1);
    }

    @Override
    public AstNodeType getType() {
        return AstNodeType.EXPR_LIST;
    }

    @Override
    public int getProductionId() {
        return ID;
    }
}
