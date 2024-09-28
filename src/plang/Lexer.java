package plang;

import plang.tokens.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implements a lexer, which lazily tokenize the content of a P source file ( with {@code .p} extension).
 */
@SuppressWarnings("SwitchStatementWithTooFewBranches")
public final class Lexer {
    /** The name of the source file */
    private final String fileName;
    /** The source code to be tokenized */
    private final String source;
    /** The position of the cursor into the source string */
    private int position;
    /** The current line number */
    private int line = 1;
    /** The current column number */
    private int column = 1;

    /**
     * Initializes a new {@code Lexer} for the specified source file.
     * @param filePath The path of the P source code file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public Lexer(String filePath) throws IOException {
        var path = Paths.get(filePath);
        this.fileName = path.getFileName().toString();
        this.source = Files.readString(path);
    }

    /**
     * Advances the position by one.
     * If the source code has already been consumed, this method does not have any effect.
     */
    private void advance() {
        if (position >= source.length())
            return;

        var c = source.charAt(position++);

        if (c == '\n') {
            line++;
            column = 1;
        }
        else column++;
    }

    /**
     * Gets the current character from the source code.
     * @return The current character, or {@code -1} if the source code has already been consumed.
     */
    private char current() {
        if (position >= source.length())
            return (char) -1;

        return source.charAt(position);
    }

    /**
     * Consumes the next token from the source code.
     * @return The next token
     * @throws IOException If an invalid character is encountered.
     */
    public Token nextToken() throws IOException {
        while (current() == ' ' || current() == '\n' || current() == '\t' || current() == '\r')
            advance();

        switch (current()) {
            case (char) -1:
                advance();
                return CommonTokens.get(TokenKind.END_OF_FILE);

            case '!':
                advance();
                return CommonTokens.get(TokenKind.LOGICAL_NOT);

            case '(':
                advance();
                return CommonTokens.get(TokenKind.LEFT_PARENTHESIS);

            case ')':
                advance();
                return CommonTokens.get(TokenKind.RIGHT_PARENTHESIS);

            case '{':
                advance();
                return CommonTokens.get(TokenKind.LEFT_BRACE);

            case '}':
                advance();
                return CommonTokens.get(TokenKind.RIGHT_BRACE);

            case '+':
                advance();
                return CommonTokens.get(TokenKind.PLUS);

            case '-':
                advance();
                return CommonTokens.get(TokenKind.MINUS);

            case '*':
                advance();
                return CommonTokens.get(TokenKind.ASTERISK);

            case '/':
                advance();
                return switch (current()) {
                    case '/':
                        do advance();
                        while (current() != '\n' && current() != (char) -1);
                        yield nextToken();

                    case '*':
                        var state = 0; // 0 -> in comment; 1 -> found asterisk; 2 -> end
                        var currentLine = line;
                        var currentColumn = column;

                        do {
                            advance();
                            state = switch (current()) {
                                case '*' -> 1;
                                case '/' -> state == 1 ? 2 : 0;
                                case (char) -1 -> state;
                                default -> 0;
                            };
                        } while (state < 2 && current() != (char) -1);

                        if (state < 2)
                            throwIOException(currentLine, currentColumn, "Unterminated block comment");

                        yield nextToken();

                    default:
                        yield CommonTokens.get(TokenKind.SLASH);
                };

            case ';':
                advance();
                return CommonTokens.get(TokenKind.SEMICOLON);

            case ',':
                advance();
                return CommonTokens.get(TokenKind.COMMA);

            case '&':
                advance();
                return switch (current()) {
                    case '&' -> {
                        advance();
                        yield CommonTokens.get(TokenKind.LOGICAL_AND);
                    }
                    default -> {
                        throwIOException("Invalid character after '&': '" + current() + "'");
                        yield null;
                    }
                };

            case '|':
                advance();
                return switch (current()) {
                    case '|' -> {
                        advance();
                        yield CommonTokens.get(TokenKind.LOGICAL_OR);
                    }
                    default -> {
                        throwIOException("Invalid character after '|': '" + current() + "'");
                        yield null;
                    }
                };

            case '=':
                advance();
                return switch (current()) {
                    case '=' -> {
                        advance();
                        yield CommonTokens.get(TokenKind.EQUALITY);
                    }
                    default -> {
                        throwIOException("Invalid character after '=': '" + current() + "'");
                        yield null;
                    }
                };

            case '<':
                advance();
                return switch (current()) {
                    case '>' -> {
                        advance();
                        yield CommonTokens.get(TokenKind.INEQUALITY);
                    }
                    case '=' -> {
                        advance();
                        yield CommonTokens.get(TokenKind.LESS_THAN_EQUAL);
                    }
                    default -> CommonTokens.get(TokenKind.LESS_THAN);
                };

            case '>':
                advance();
                return switch (current()) {
                    case '=' -> {
                        advance();
                        yield CommonTokens.get(TokenKind.GREATER_THAN_EQUAL);
                    }
                    default -> CommonTokens.get(TokenKind.GREATER_THAN);
                };

            default:
                var sb = new StringBuilder();

                if (Character.isLetter(current()) || current() == '_') {
                    var currentLine = line;
                    var currentColumn = column;

                    do {
                        sb.append(current());
                        advance();
                    } while (Character.isLetterOrDigit(current()) || current() == '_');

                    var content = sb.toString();

                    if (content.chars().allMatch(value -> value == '_'))
                        throwIOException(currentLine, currentColumn, "An identifier cannot consist solely of the character '_'");

                    return switch (content) {
                        case "assign" -> CommonTokens.get(TokenKind.KWD_ASSIGN);
                        case "to" -> CommonTokens.get(TokenKind.KWD_TO);
                        case "if" -> CommonTokens.get(TokenKind.KWD_IF);
                        case "else" -> CommonTokens.get(TokenKind.KWD_ELSE);
                        case "while" -> CommonTokens.get(TokenKind.KWD_WHILE);
                        case "begin" -> CommonTokens.get(TokenKind.KWD_BEGIN);
                        case "end" -> CommonTokens.get(TokenKind.KWD_END);
                        case "print" -> CommonTokens.get(TokenKind.KWD_PRINT);
                        case "read" -> CommonTokens.get(TokenKind.KWD_READ);
                        default -> new IdentifierToken(content);
                    };
                }
                else if (Character.isDigit(current())) {
                    do {
                        sb.append(current());
                        advance();
                    } while (Character.isDigit(current()));

                    if (current() == '_' || Character.isLetter(current()))
                        throwIOException("Invalid suffix to digit sequence");

                    return new NumberToken(Integer.parseInt(sb.toString()));
                }

                break;
        }

        throwIOException("Invalid character: '" + current() + "'");
        return null;
    }

    private void throwIOException(int line, int column, String message) throws IOException {
        throw new IOException("<" + fileName + "[" + line + ":" + column + "]> " + message);
    }

    private void throwIOException(String message) throws IOException {
        throwIOException(line, column, message);
    }
}
