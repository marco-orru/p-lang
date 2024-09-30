package plang.ast;

public abstract class BoolExprAstNode extends AstNode {
    /**
     * Initializes a new {@link BoolExprAstNode} with the specified list of child nodes.
     *
     * @param childNodes The child nodes of the AST node.
     */
    protected BoolExprAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return AstNodeType.BOOL_EXPR;
    }
}
