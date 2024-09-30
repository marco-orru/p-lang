package plang.ast;

/**
 * Represents a multiply {@code <expr>} AST node.
 */
public final class MultiplyExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 2;

    /**
     * Initializes a new {@link MultiplyExprAstNode}.
     *
     * @param exprList The {@code <exprlist>} child node.
     */
    public MultiplyExprAstNode(ExprListAstNode exprList) {
        super(exprList);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    /**
     * Gets the {@link ExprListAstNode} child node.
     *
     * @return The {@code <exprlist>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public ExprListAstNode getExprList() {
        return (ExprListAstNode) childNodes.get(0);
    }

    @Override
    public String toString() {
        return "*" + " " + "(" + " " + getExprList() + " " + ")";
    }
}
