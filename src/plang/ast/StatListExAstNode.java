package plang.ast;

/**
 * Represents a {@code <statlistex>} AST node.
 */
public abstract class StatListExAstNode extends AstNode {
    /**
     * Initializes a new {@link StatListExAstNode} with the specified list of child nodes.
     *
     * @param childNodes The child nodes of the AST node.
     */
    protected StatListExAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return AstNodeType.STAT_LIST;
    }
}
