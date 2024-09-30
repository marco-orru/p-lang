package plang.ast;

/**
 * Represents an empty {@code <exprlistex>} AST node.
 */
public final class EmptyExprListExAstNode extends ExprListExAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 1;

    /**
     * Initializes a new {@link EmptyExprListExAstNode}.
     */
    public EmptyExprListExAstNode() {
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
