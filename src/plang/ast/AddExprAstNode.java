package plang.ast;

/**
 * Represents a add {@code <expr>} AST node.
 */
public final class AddExprAstNode extends ExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 0;

    /**
     * Initializes a new {@link AddExprAstNode}.
     *
     * @param exprList The {@code <exprlist>} child node.
     */
    public AddExprAstNode(ExprListAstNode exprList) {
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
