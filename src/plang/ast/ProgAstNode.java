package plang.ast;

/**
 * Represents a {@code <prog>} AST node.
 */
public final class ProgAstNode extends AstNode {
    /**
     * The ID of this production
     */
    public final int ID = 0;

    /**
     * Initializes a new {@link ProgAstNode}.
     *
     * @param statList The {@code <statlist>} child node.
     */
    public ProgAstNode(StatListAstNode statList) {
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
    public AstNodeType getType() {
        return AstNodeType.PROG;
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return getStatList() + "$";
    }
}
