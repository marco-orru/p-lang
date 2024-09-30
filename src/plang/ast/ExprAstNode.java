package plang.ast;

/**
 * Represents a {@code <expr>} AST node.
 */
public abstract class ExprAstNode extends AstNode {
    /**
     * Initializes a new {@link ExprAstNode} with the specified list of child nodes.
     * @param childNodes The child nodes of the AST node.
     */
    protected ExprAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return AstNodeType.EXPR;
    }
}
