package plang.ast;

/**
 * Represents a and {@code <boolexpr>} AST node.
 */
public final class AndBoolExprAstNode extends BoolExprAstNode {
    /**
     * The ID of this production
     */
    public static final int ID = 6;

    /**
     * Initializes a new {@link AndBoolExprAstNode}.
     *
     * @param boolExpr1 The first {@code <boolexpr>} child node.
     * @param boolExpr2 The second {@code <boolexpr>} child node.
     */
    public AndBoolExprAstNode(BoolExprAstNode boolExpr1, BoolExprAstNode boolExpr2) {
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
        return "&&" + " " + getBoolExpr1() + " " + getBoolExpr2();
    }
}

