package plang.ast;

/**
 * Defines the types of {@link AstNode}.
 */
public enum AstNodeType {
    /**
     * The AST node is a {@code <prog>} AST node.
     */
    PROG,

    /**
     * The AST node is a {@code <statlist>} AST node.
     */
    STAT_LIST,

    /**
     * The AST node is a {@code <statlistex>} AST node.
     */
    STAT_LIST_EX,

    /**
     * The AST node is a {@code <stat>} AST node.
     */
    STAT,

    /**
     * The AST node is a {@code <ifex>} AST node.
     */
    IF_EX,

    /**
     * The AST node is a {@link <idlist>} AST node.
     */
    ID_LIST,

    /**
     * The AST node is a {@code <idlistex>} AST node.
     */
    ID_LIST_EX,

    /**
     * The AST node is a {@code <boolexpr>} AST node.
     */
    BOOL_EXPR,

    /**
     * The AST node is a {@code <expr>} AST node.
     */
    EXPR,

    /**
     * The AST node is a {@code <exprlist>} AST node.
     */
    EXPR_LIST,

    /**
     * The AST node is a {@code <exprlistex>} AST node.
     */
    EXPR_LIST_EX
}
