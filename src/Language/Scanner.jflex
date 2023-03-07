/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Colors.Type;
import Components.*;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */

%{
    ArrayList<Token> tokens = new ArrayList<>();
    ArrayList<ErrorL> errors = new ArrayList<>();
    ArrayList<String> operations = new ArrayList<>();
    String operation = "";
    public void printTokens() {
        System.out.println("TOKENS");
        System.out.printf("%-25s%-6s%-8s%-10s\n",
            "Lexeme",
            "Line",
            "Column",
            "Type"
        );
        tokens.forEach(
            token -> {
                token.print();
            }
        );
    }
    public void printErrors() {
        System.out.println("ERRORS");
        System.out.printf("%-6s%-8s%-10s\n",
            "Line",
            "Column",
            "Description"
        );
        errors.forEach(
            error -> {
                error.print();
            }
        );
    }
    public String getTokens() {
        String tokensTab = "TOKENS\n";
        tokensTab += "Lexeme                   Line  Column  Type\n";
        if(tokens.size() > 0) {
            for(int i = 0; i < tokens.size(); i ++) {
                tokensTab += tokens.get(i) + "\n";
            }
        }
        else tokensTab += "No Tokens\n";
        return tokensTab + "\n";
    }
    public String getErrors() {
        String errorsTab = "ERRORS\n";
        errorsTab += "Line  Column  Description\n";
        if(errors.size() > 0) {
            for(int i = 0; i < errors.size(); i ++) {
                errorsTab += errors.get(i) + "\n";
            }
        }
        else errorsTab += "No Lexical Errors\n";
        return errorsTab + "\n";
    }
    void concat(String token) {
        operation += token;
    }
    void addOperation() {
        if(!operation.equals("")) {
            if(operation.charAt(0) == ' ') {
                operation = new StringBuilder(operation).deleteCharAt(0).toString();
            }
            operations.add(operation);
            operation = "";
        }
    }
    void addToken(String lexeme,int line,int column,Type type) {
        tokens.add(new Token(lexeme,line,column,type));
    }
    void addError(int line,int column,String character) {
        errors.add(new ErrorL(line,column,character));
    }
%}

//Directivas
%class Scanner
%public
%cupsym Sym
%cup
%char
%column
%full
%line
%unicode

%init{
    yyline = 1;
    yychar = 1;
%init}

//Expresiones regulares
UNUSED=[ \r\t]+
CHARALPHA = [a-zA-Z]
CHARNUM = [0-9]
ID = [a-zA-Z][a-zA-Z0-9\_]*
STRING = \"(([^\"\\]?|\\.)*)\"
ASCII = [!-\/:-@\[-`{-\}]
COMMENTS = "//"([^\r\n]*)?
COMMENTM = "<!"([^<!>]*)?"!>"
%%

/* 3. Reglas Semanticas */

"CONJ"              {addToken(yytext(),yyline,yychar,Type.RW_CONJ);         return new Symbol(Sym.RW_CONJ,yyline,yychar,yytext());}
{CHARALPHA}         {addToken(yytext(),yyline,yychar,Type.CHAR);            return new Symbol(Sym.CHAR,yyline,yychar,yytext());}
{CHARNUM}           {addToken(yytext(),yyline,yychar,Type.CHARNUM);         return new Symbol(Sym.CHARNUM,yyline,yychar,yytext());}
{ID}                {addToken(yytext(),yyline,yychar,Type.ID);              return new Symbol(Sym.ID,yyline,yychar,yytext());}
{STRING}            {addToken(yytext(),yyline,yychar,Type.STRING);          return new Symbol(Sym.STRING,yyline,yychar,yytext());}
"{"                 {addToken(yytext(),yyline,yychar,Type.LBRACKET);        return new Symbol(Sym.LBRACKET,yyline,yychar,yytext());}
"}"                 {addToken(yytext(),yyline,yychar,Type.RBRACKET);        return new Symbol(Sym.RBRACKET,yyline,yychar,yytext());}
";"                 {addToken(yytext(),yyline,yychar,Type.SEMICOLON);       return new Symbol(Sym.SEMICOLON,yyline,yychar,yytext());}
":"                 {addToken(yytext(),yyline,yychar,Type.COLON);           return new Symbol(Sym.COLON,yyline,yychar,yytext());}
","                 {addToken(yytext(),yyline,yychar,Type.COMMA);           return new Symbol(Sym.COMMA,yyline,yychar,yytext());}
"|"                 {addToken(yytext(),yyline,yychar,Type.OR);              return new Symbol(Sym.OR,yyline,yychar,yytext());}
"+"                 {addToken(yytext(),yyline,yychar,Type.POSITIVE);        return new Symbol(Sym.POSITIVE,yyline,yychar,yytext());}
"*"                 {addToken(yytext(),yyline,yychar,Type.KLEENE);          return new Symbol(Sym.KLEENE,yyline,yychar,yytext());}
"?"                 {addToken(yytext(),yyline,yychar,Type.OPTIONAL);        return new Symbol(Sym.OPTIONAL,yyline,yychar,yytext());}
"."                 {addToken(yytext(),yyline,yychar,Type.CONCAT);          return new Symbol(Sym.CONCAT,yyline,yychar,yytext());}
"~"                 {addToken(yytext(),yyline,yychar,Type.TILDE);           return new Symbol(Sym.TILDE,yyline,yychar,yytext());}
"\\\""              {addToken(yytext(),yyline,yychar,Type.DOUBLEQUOTE);     return new Symbol(Sym.DOUBLEQUOTE,yyline,yychar,yytext());}
"\\\'"              {addToken(yytext(),yyline,yychar,Type.SINGLEQUOTE);     return new Symbol(Sym.SINGLEQUOTE,yyline,yychar,yytext());}
"\\n"               {addToken(yytext(),yyline,yychar,Type.ENTER);           return new Symbol(Sym.ENTER,yyline,yychar,yytext());}
"%%"                {addToken(yytext(),yyline,yychar,Type.LIMIT);           return new Symbol(Sym.LIMIT,yyline,yychar,yytext());}
(\-[\s]*\>)         {addToken(yytext(),yyline,yychar,Type.PROMPT);          return new Symbol(Sym.PROMPT,yyline,yychar,yytext());}
{ASCII}             {addToken(yytext(),yyline,yychar,Type.CHAR);            return new Symbol(Sym.CHAR,yyline,yychar,yytext());}
\n                  {yychar = 1;}
{UNUSED}            {}
{COMMENTS}          {}
{COMMENTM}          {}
.                   {addError(yyline,yychar,yytext());}