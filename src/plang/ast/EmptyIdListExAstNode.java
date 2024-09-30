package plang.ast;

/**
 * Represents an empty {@code <idlistex>} AST node.
 */
public final class EmptyIdListExAstNode extends IdListExAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 1;

    /**
     * Initializes a new {@link EmptyIdListExAstNode}.
     */
    public EmptyIdListExAstNode() {
        super();
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "";
    }
}
