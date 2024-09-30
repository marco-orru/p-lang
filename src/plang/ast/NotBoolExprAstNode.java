package plang.ast;

/**
 * Represents a not {@code <boolexpr>} AST node.
 */
public final class NotBoolExprAstNode extends BoolExprAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 3;

    /**
     * Initializes a new {@link OrBoolExprAstNode}.
     *
     * @param expr The {@code <expr>} child node.
     */
    public NotBoolExprAstNode(ExprAstNode expr) {
        super(expr);
    }

    /**
     * Gets the {@link ExprAstNode} child node.
     *
     * @return The {@code <expr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public ExprAstNode getExpr() {
        return (ExprAstNode) childNodes.get(0);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "!" + " " + getExpr();
    }
}
