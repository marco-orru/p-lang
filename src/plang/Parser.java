package plang;

import plang.ast.*;
import plang.tokens.Token;

import java.io.IOException;

/**
 * Implements a recursive descent parser, which parses a sequence of
 * tokens into an AST (Abstract Syntax Tree) following the P language grammar.
 */
public final class Parser {
    /** The lexer that tokenize the source code. */
    private final Lexer lexer;
    /** The lookahead token (e.g., the last read token). */
    private Token lookahead;

    /**
     * Initializes a new {@link Parser} for the specified source file.
     * @param filePath The path of the P source code file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public Parser(String filePath) throws IOException {
        this.lexer = new Lexer(filePath);
    }

    /**
     * Updates the token lookahead with the next token from the lexer.
     * @throws IOException If the lexer throws a {@link IOException}.
     */
    private void move() throws IOException {
        this.lookahead = lexer.nextToken();
    }

    /**
     * Parses the input and returns the root of the Abstract Syntax Tree.
     * @return The root node of the AST.
     * @throws IOException If the lexer throws a {@link IOException} or an invalid token is found.
     */
    public AstNode parse() throws IOException {
        move();
        return parseProg();
    }

    private AstNode parseProg() throws IOException {
        return null;
    }

    private AstNode parseStatList() throws IOException {
        return null;
    }

    private AstNode parseStatListEx() throws IOException {
        return null;
    }

    private AstNode parseStat() throws IOException {
        return null;
    }

    private AstNode parseIfEx() throws IOException {
        return null;
    }

    private AstNode parseIdList() throws IOException {
        return null;
    }

    private AstNode parseIdListEx() throws IOException {
        return null;
    }

    private AstNode parseBoolExpr() throws IOException {
        return null;
    }

    private AstNode parseExpr() throws IOException {
        return null;
    }

    private AstNode parseExprList() throws IOException {
        return null;
    }

    private AstNode parseExprListEx() throws IOException {
        return null;
    }

    /**
     * Throws an IO exception with extended info about the current position in the source file and a custom message.
     * @param message A message that describes the cause of the exception.
     * @throws IOException Always.
     */
    private void throwIOException(String message) throws IOException {
        throw new IOException("<" + lexer.getFileName() + "[" + lexer.getLastLine() + ":" + lexer.getLastColumn() + "]> " + message);
    }
}
