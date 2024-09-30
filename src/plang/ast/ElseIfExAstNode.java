package plang.ast;

/**
 * Represents a else {@code <ifex>} AST node.
 */
public final class ElseIfExAstNode extends IfExAstNode {
    /** The ID of this production */
    public final int ID = 1;

    /**
     * Initializes a new {@link ElseIfExAstNode}.
     * @param stat The {@code <stat>} child node.
     */
    public ElseIfExAstNode(StatAstNode stat) {
        super(stat);
    }

    /**
     * Gets the {@link StatAstNode} child node.
     * @return The {@code <stat>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public StatAstNode getStat() {
        return (StatAstNode) childNodes.get(0);
    }

    @Override
    public int getProductionId() {
        return ID;
    }
}
