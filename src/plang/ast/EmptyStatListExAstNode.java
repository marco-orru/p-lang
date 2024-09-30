package plang.ast;

/**
 * Represents an empty {@code <statlistex>} AST node.
 */
public final class EmptyStatListExAstNode extends StatListExAstNode {
    /** The ID of this production */
    public final int ID = 1;

    /**
     * Initializes a new {@link EmptyStatListExAstNode}.
     */
    public EmptyStatListExAstNode() {
        super();
    }

    @Override
    public int getProductionId() {
        return ID;
    }
}
