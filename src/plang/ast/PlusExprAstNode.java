package plang.ast;

/**
 * Represents a plus {@code <expr>} AST node.
 */
public final class PlusExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 0;

    /**
     * Initializes a new {@link PlusExprAstNode}.
     *
     * @param exprList The {@code <exprlist>} child node.
     */
    public PlusExprAstNode(ExprListAstNode exprList) {
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
        return "+" + " " + "(" + " " + getExprList() + " " + ")";
    }
}
