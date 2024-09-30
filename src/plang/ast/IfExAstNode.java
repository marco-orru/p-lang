package plang.ast;

/**
 * Represents a {@code <ifex>} AST node.
 */
public abstract class IfExAstNode extends AstNode {
    /**
     * Initializes a new {@link IfExAstNode} with the specified list of child nodes.
     * @param childNodes The child nodes of the AST node.
     */
    protected IfExAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return AstNodeType.IF_EX;
    }
}
