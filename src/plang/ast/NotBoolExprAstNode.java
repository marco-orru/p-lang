package plang.ast;

/**
 * Represents a not {@code <boolexpr>} AST node.
 */
public final class NotBoolExprAstNode extends BoolExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 8;

    /**
     * Initializes a new {@link OrBoolExprAstNode}.
     *
     * @param boolExpr The {@code <boolexpr>} child node.
     */
    public NotBoolExprAstNode(BoolExprAstNode boolExpr) {
        super(boolExpr);
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

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "!" + " " + getBoolExpr();
    }
}
