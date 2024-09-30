package plang.tokens;

/**
 * Defines the kinds of {@link Token}.
 */
public enum TokenKind {
    /**
     * The token identifies the end of the source file
     */
    END_OF_FILE,

    /**
     * The token is an integral number.
     */
    NUMBER,

    /**
     * The token is an identifier.
     */
    IDENTIFIER,

    /**
     * The token is the logical not operator ({@code !}).
     */
    LOGICAL_NOT,

    /**
     * The token is a left parenthesis ({@code (}).
     */
    LEFT_PARENTHESIS,

    /**
     * The token is a right parenthesis ({@code )}).
     */
    RIGHT_PARENTHESIS,

    /**
     * The token is a left brace (<code>{</code>).
     */
    LEFT_BRACE,

    /**
     * The token is a right brace (<code>}</code>).
     */
    RIGHT_BRACE,

    /**
     * The token a plus sign ({@code +}).
     */
    PLUS,

    /**
     * The token is a minus sign ({@code -}).
     */
    MINUS,

    /**
     * The token is an asterisk ({@code *}).
     */
    ASTERISK,

    /**
     * The token is a slash ({@code /})
     */
    SLASH,

    /**
     * The token is a semicolon ({@code ;}).
     */
    SEMICOLON,

    /**
     * The token is a comme ({@code ,}).
     */
    COMMA,

    /**
     * The token is a less than symbol ({@code <}).
     */
    LESS_THAN,

    /**
     * The token is a greater than symbol ({@code >}).
     */
    GREATER_THAN,

    /**
     * The token is a less than or equal symbol ({@code <=}).
     */
    LESS_THAN_EQUAL,

    /**
     * The token is a greater than or equal symbol ({@code >=}).
     */
    GREATER_THAN_EQUAL,

    /**
     * The token is an equality symbol ({@code ==}).
     */
    EQUALITY,

    /**
     * The token is an inequality symbol ({@code <>}).
     */
    INEQUALITY,

    /**
     * The token is a logical and symbol ({@code &&}).
     */
    LOGICAL_AND,

    /**
     * The token is a logical or symbol ({@code ||}).
     */
    LOGICAL_OR,

    /**
     * The token is the {@code assign} keyword.
     */
    KWD_ASSIGN,

    /**
     * The token is the {@code to} keyword.
     */
    KWD_TO,

    /**
     * The token is the {@code if} keyword.
     */
    KWD_IF,

    /**
     * The token is the {@code else} keyword.
     */
    KWD_ELSE,

    /**
     * The token is the {@code while} keyword.
     */
    KWD_WHILE,

    /**
     * The token is the {@code end} keyword.
     */
    KWD_END,

    /**
     * The token is the {@code print} keyword.
     */
    KWD_PRINT,

    /**
     * The token is the {@code read} keyword.
     */
    KWD_READ
}
