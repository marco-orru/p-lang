package plang.ast;

/**
 * Represents an end {@code <ifex>} AST node.
 */
public final class EndIfExAstNode extends IfExAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 0;

    /**
     * Initializes a new {@link EndIfExAstNode}.
     */
    public EndIfExAstNode() {
        super();
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "end";
    }
}
