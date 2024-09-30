package plang.ast;

public final class StatListAstNode extends AstNode {
    /** The ID of this production */
    public final int ID = 0;

    /**
     * Initializes a new {@link StatListAstNode}.
     * @param stat The {@code <stat>} child node.
     * @param statListEx The {@code <statlistex>} child node.
     */
    public StatListAstNode(StatAstNode stat, StatListExAstNode statListEx) {
        super(stat, statListEx);
    }

    /**
     * Gets the {@link StatAstNode} child node.
     * @return The {@code <stat>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public StatAstNode getStat() {
        return (StatAstNode) childNodes.get(0);
    }

    /**
     * Gets the {@link StatListExAstNode} child node.
     * @return The {@code <statlistex>} child node.
     */
    public StatListExAstNode getStatListEx() {
        return (StatListExAstNode) childNodes.get(1);
    }

    @Override
    public AstNodeType getType() {
        return AstNodeType.STAT_LIST;
    }

    @Override
    public int getProductionId() {
        return ID;
    }
}
