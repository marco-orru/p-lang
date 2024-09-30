package plang.ast;

/**
 * Represents a {@code <stat>} AST node.
 */
public abstract class StatAstNode extends AstNode {
    /**
     * Initializes a new {@link StatAstNode} with the specified list of child nodes.
     * @param childNodes The child nodes of the AST node.
     */
    protected StatAstNode(AstNode... childNodes) {
        super(childNodes);
    }

    @Override
    public final AstNodeType getType() {
        return AstNodeType.STAT;
    }
}
