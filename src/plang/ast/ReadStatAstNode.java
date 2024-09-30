package plang.ast;

public final class ReadStatAstNode extends StatAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 2;

    /**
     * Initializes a new {@link StatAstNode}.
     *
     * @param idList The {@code <idlist>} child node.
     */
    public ReadStatAstNode(IdListAstNode idList) {
        super(idList);
    }

    /**
     * Gets the {@link IdListAstNode} child node.
     *
     * @return The {@code <idlist>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public IdListAstNode getIdList() {
        return (IdListAstNode) childNodes.get(0);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "read" + " " + "(" + " " + getIdList() + " " + ")";
    }
}
