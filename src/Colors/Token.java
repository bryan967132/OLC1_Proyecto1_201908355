package Colors;
public class Token {
    public String lexeme;
    public int yychar;
    public int yylength;
    public Tokens type;
    public Token(String lexeme,int yychar,int yylength,Tokens type) {
        this.lexeme = lexeme;
        this.yychar = yychar;
        this.yylength = yylength;
        this.type = type;
    }
    public String toString() {
        return lexeme + " " + type;
    }
}