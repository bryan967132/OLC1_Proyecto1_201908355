package Colors;
public class Token {
    public String lexeme;
    public int yychar;
    public int yylength;
    public Types type;
    public Token(String lexeme,Types type) {
        this.lexeme = lexeme;
        this.type = type;
    }
    public Token(String lexeme,int yychar,int yylength,Types type) {
        this.lexeme = lexeme;
        this.yychar = yychar;
        this.yylength = yylength;
        this.type = type;
    }
    public String toString() {
        return lexeme;
    }
}