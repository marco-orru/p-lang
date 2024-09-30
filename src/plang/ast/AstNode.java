package plang.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an AST node.
 */
public abstract class AstNode {
    /**
     * The list of child nodes
     */
    protected final List<AstNode> childNodes = new ArrayList<>();

    /**
     * Initializes a new {@link AstNode} with the specified list of child nodes.
     *
     * @param childNodes The child nodes of the AST node.
     */
    protected AstNode(AstNode... childNodes) {
        Collections.addAll(this.childNodes, childNodes);
    }

    /**
     * Gets the type of AST node.
     *
     * @return The type of AST node.
     */
    public abstract AstNodeType getType();

    /**
     * Gets the id of the production in the grammar.
     *
     * @return The id of the production in the grammar.
     */
    public abstract int getProductionId();
}
