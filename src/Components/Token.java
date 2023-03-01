package Components;
public class Token {
    String lexeme;
    int line;
    int column;
    String type;
    public Token(String lexeme,int line,int column,String type) {
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
        this.type = type;
    }
    public void print() {
        System.out.printf("%-25s%-6s%-8s%-10s\n",
            lexeme,
            line,
            column,
            type
	);
    }
    public String toString() {
        return lexeme + " ".repeat(25 - lexeme.length()) + line + " ".repeat(6 - String.valueOf(line).length()) + column + " ".repeat(8 - String.valueOf(column).length()) + type;
    }
}