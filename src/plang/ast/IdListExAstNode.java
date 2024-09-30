package plang.ast;

/**
 * Represents a {@code <idlistex>} AST node.
 */
public abstract class IdListExAstNode extends AstNode {
    /**
     * Initializes a new {@link IdListExAstNode} with the specified list of child nodes.
     *
     * @param childNodes The child nodes of the AST node.
     */
    protected IdListExAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return null;
    }
}
