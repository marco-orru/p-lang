package plang.ast;

/**
 * Represents a or {@code <boolexpr>} AST node.
 */
public final class OrBoolExprAstNode extends BoolExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 7;

    /**
     * Initializes a new {@link OrBoolExprAstNode}.
     *
     * @param boolExpr1 The first {@code <boolexpr>} child node.
     * @param boolExpr2 The second {@code <boolexpr>} child node.
     */
    public OrBoolExprAstNode(BoolExprAstNode boolExpr1, BoolExprAstNode boolExpr2) {
        super(boolExpr1, boolExpr2);
    }

    /**
     * Gets the first {@link BoolExprAstNode} child node.
     *
     * @return The first {@code <boolexpr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public BoolExprAstNode getBoolExpr1() {
        return (BoolExprAstNode) childNodes.get(0);
    }

    /**
     * Gets the second {@link BoolExprAstNode} child node.
     *
     * @return The second {@code <boolexpr>} child node.
     */
    public BoolExprAstNode getBoolExpr2() {
        return (BoolExprAstNode) childNodes.get(1);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "||" + " " + getBoolExpr1() + " " + getBoolExpr2();
    }
}
