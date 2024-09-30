package plang.ast;

public abstract class ExprListExAstNode extends AstNode {
    /**
     * Initializes a new {@link ExprListExAstNode} with the specified list of child nodes.
     *
     * @param childNodes The child nodes of the AST node.
     */
    protected ExprListExAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return AstNodeType.EXPR_LIST_EX;
    }
}
