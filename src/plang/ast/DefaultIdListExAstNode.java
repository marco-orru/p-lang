package plang.ast;

import plang.tokens.IdentifierToken;

/**
 * Represents a default {@code <idlistex>} AST node.
 */
public final class DefaultIdListExAstNode extends IdListExAstNode {
    /**
     * The ID of this production
     */
    public final int ID = 0;

    /**
     * The identifier token contained in this node
     */
    private final IdentifierToken identifier;

    /**
     * Initializes a new {@link DefaultIdListExAstNode}.
     *
     * @param identifier The identifier token contained in this node
     * @param idListEx   The {@code <idlistex>} child node.
     */
    public DefaultIdListExAstNode(IdentifierToken identifier, IdListExAstNode idListEx) {
        super(idListEx);
        this.identifier = identifier;
    }

    /**
     * Gets the identifier token contained in this node.
     *
     * @return The identifier token contained in this node
     */
    public IdentifierToken getIdentifier() {
        return this.identifier;
    }

    /**
     * Gets the {@link IdListExAstNode} child node.
     *
     * @return The {@code <idlistex>} child node.
     */
    @SuppressWarnings("SequencedCollectionMethodCanBeUsed")
    public IdListExAstNode getIdListEx() {
        return (IdListExAstNode) childNodes.get(0);
    }

    @Override
    public int getProductionId() {
        return ID;
    }

    @Override
    public String toString() {
        return "," + " " + identifier.getIdentifier() + " " + getIdListEx();
    }
}
