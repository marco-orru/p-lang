package plang.ast;

/**
 * Represents a print {@code <stat>} AST node.
 */
public final class PrintStatAstNode extends StatAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 1;

    /**
     * Initializes a new {@link PrintStatAstNode}.
     *
     * @param exprList The {@code <exprlist>} child node.
     */
    public PrintStatAstNode(ExprListAstNode exprList) {
        super(exprList);
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
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "print" + " " + "(" + " " + getExprList() + " " + ")";
    }
}
