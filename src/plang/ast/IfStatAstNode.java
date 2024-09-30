package plang.ast;

/**
 * Represents a if {@code <stat>} AST node.
 */
public final class IfStatAstNode extends StatAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 4;

    /**
     * Initializes a new {@link IfStatAstNode}
     *
     * @param boolExpr The {@code <boolexpr>} child node.
     * @param stat     The {@code <stat>} child node.
     * @param ifEx     The {@code <ifex>} child node.
     */
    public IfStatAstNode(BoolExprAstNode boolExpr, StatAstNode stat, IfExAstNode ifEx) {
        super(boolExpr, stat, ifEx);
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

    /**
     * Gets the {@link IfExAstNode} child node.
     *
     * @return The {@code <ifex>} child node.
     */
    public IfExAstNode getIfEx() {
        return (IfExAstNode) childNodes.get(2);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "if" + " " + "(" + " " + getBoolExpr() + " " + ")" + " " + getStat() + " " + getIfEx();
    }
}
