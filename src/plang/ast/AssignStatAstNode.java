package plang.ast;

/**
 * Represents an assignment {@code <stat>} AST node.
 */
public final class AssignStatAstNode extends StatAstNode {
    /** The ID of this production */
    public final int ID = 0;

    /**
     * Initializes a new {@link AssignStatAstNode}.
     * @param expr The {@code <expr>} child node.
     * @param idList The {@code <idlist>} child node.
     */
    public AssignStatAstNode(ExprAstNode expr, IdListAstNode idList) {
        super(expr, idList);
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
     * Gets the {@link IdListAstNode} child node.
     * @return The {@code <idlist>} child node.
     */
    public IdListAstNode getIdList() {
        return (IdListAstNode) childNodes.get(1);
    }

    @Override
    public int getProductionId() {
        return ID;
    }
}
