package plang.ast;

/**
 * Represents a block {@code <stat>} AST node.
 */
public final class BlockStatAstNode extends StatAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 5;

    /**
     * Initializes a new {@link BlockStatAstNode}.
     *
     * @param statList The {@code <statlist>} child node.
     */
    public BlockStatAstNode(StatListAstNode statList) {
        super(statList);
    }

    /**
     * Gets the {@link StatListAstNode} child node.
     *
     * @return The {@code <statlist>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public StatListAstNode getStatList() {
        return (StatListAstNode) childNodes.get(0);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "{" + " " + getStatList() + " " + "}";
    }
}
