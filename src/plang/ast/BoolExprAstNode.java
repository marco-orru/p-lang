package plang.ast;

import plang.tokens.Token;
import plang.tokens.TokenKind;

/**
 * Represents a {@code <boolexpr>} AST node.
 */
public final class BoolExprAstNode extends AstNode {
    /** The ID of this production */
    public final int ID = 0;

    /** The boolean operator contained in this node. */
    private final Token booleanOperator;

    /**
     * Initializes a new {@link BoolExprAstNode}.
     * @param booleanOperator The boolean operator contained in this node.
     * @param expr1 The first {@code <expr>} child node.
     * @param expr2 The second {@code <expr>} child node.
     */
    public BoolExprAstNode(Token booleanOperator, ExprAstNode expr1, ExprAstNode expr2) {
        super(expr1, expr2);

        switch (booleanOperator.getKind()) {
            case EQUALITY:
            case INEQUALITY:
            case GREATER_THAN:
            case GREATER_THAN_EQUAL:
            case LESS_THAN:
            case LESS_THAN_EQUAL:
                this.booleanOperator = booleanOperator;
                break;
            default:
                throw new IllegalArgumentException("The specified token is not a boolean operator");
        }
    }

    /**
     * Gets the boolean operator contained in this node
     * @return The boolean operator contained in this node.
     */
    public Token getBooleanOperator() {
        return booleanOperator;
    }

    /**
     * Gets the first {@link ExprAstNode} child node.
     * @return The first {@code <expr>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public ExprAstNode getExpr1() {
        return (ExprAstNode) childNodes.get(0);
    }

    /**
     * Gets the second {@link ExprAstNode} child node.
     * @return The second {@code <expr>} child node.
     */
    public ExprAstNode getExpr2() {
        return (ExprAstNode) childNodes.get(1);
    }

    @Override
    public AstNodeType getType() {
        return AstNodeType.BOOL_EXPR;
    }

    @Override
    public int getProductionId() {
        return ID;
    }
}
