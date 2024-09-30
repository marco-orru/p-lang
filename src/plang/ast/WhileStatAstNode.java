package plang.ast;

/**
 * Represents a while {@code <stat>} AST node.
 */
public final class WhileStatAstNode extends StatAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 3;

    /**
     * Initializes a new {@link WhileStatAstNode}.
     *
     * @param boolExpr The {@code <boolexpr>} child node.
     * @param stat     The {@code <stat>} child node.
     */
    public WhileStatAstNode(BoolExprAstNode boolExpr, StatAstNode stat) {
        super(boolExpr, stat);
    }

    /**
     * Gets the {@link BoolExprAstNode} child node.
     *
     * @return The {@code <boolexpr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public BoolExprAstNode getBoolExpr() {
        return (BoolExprAstNode) childNodes.get(0);
    }

    /**
     * Gets the {@link StatAstNode} child node.
     *
     * @return The {@code <stat>} child node.
     */
    public StatAstNode getStat() {
        return (StatAstNode) childNodes.get(1);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "while" + " " + "(" + getBoolExpr() + " " + ")" + " " + getStat();
    }
}
