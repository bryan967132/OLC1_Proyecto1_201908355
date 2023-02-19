/* 1. Package e importaciones */
package BackEnd;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
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
    void addToken(String lexeme,int line,int column,String type) {
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
CHARACTER = [a-zA-Z0-9]
VALUE = \"(([^\"\\]?|\\.)*)\"

INPUTCHARACTER = [^\r\n]

COMMENTS = "//"{INPUTCHARACTER}*
COMMENTM = "<!"[\s\S]*?"!>"
%%

/* 3. Reglas Semanticas */

"CONJ"              {addToken(yytext(),yyline,yychar,"RW_CONJ");        return new Symbol(Sym.RW_CONJ,yyline,yychar,yytext());}
{CHARACTER}         {addToken(yytext(),yyline,yychar,"CHAR");           return new Symbol(Sym.CHAR,yyline,yychar,yytext());}
{CHARACTER}+        {addToken(yytext(),yyline,yychar,"ID");             return new Symbol(Sym.ID,yyline,yychar,yytext());}
{VALUE}             {addToken(yytext(),yyline,yychar,"VALUE");          return new Symbol(Sym.VALUE,yyline,yychar,yytext());}
"{"                 {addToken(yytext(),yyline,yychar,"LBRACKET");       return new Symbol(Sym.LBRACKET,yyline,yychar,yytext());}
"}"                 {addToken(yytext(),yyline,yychar,"RBRACKET");       return new Symbol(Sym.RBRACKET,yyline,yychar,yytext());}
";"                 {addToken(yytext(),yyline,yychar,"SEMICOLON");      return new Symbol(Sym.SEMICOLON,yyline,yychar,yytext());}
":"                 {addToken(yytext(),yyline,yychar,"COLON");          return new Symbol(Sym.COLON,yyline,yychar,yytext());}
","                 {addToken(yytext(),yyline,yychar,"COMMA");          return new Symbol(Sym.COMMA,yyline,yychar,yytext());}
"->"                {addToken(yytext(),yyline,yychar,"PROMPT");         return new Symbol(Sym.PROMPT,yyline,yychar,yytext());}
"|"                 {addToken(yytext(),yyline,yychar,"OR");             return new Symbol(Sym.OR,yyline,yychar,yytext());}
"+"                 {addToken(yytext(),yyline,yychar,"POSITIVE");       return new Symbol(Sym.POSITIVE,yyline,yychar,yytext());}
"*"                 {addToken(yytext(),yyline,yychar,"KLEENE");         return new Symbol(Sym.KLEENE,yyline,yychar,yytext());}
"."                 {addToken(yytext(),yyline,yychar,"CONCAT");         return new Symbol(Sym.CONCAT,yyline,yychar,yytext());}
"~"                 {addToken(yytext(),yyline,yychar,"TILDE");          return new Symbol(Sym.TILDE,yyline,yychar,yytext());}
"%%"                {addToken(yytext(),yyline,yychar,"LIMIT");          return new Symbol(Sym.LIMIT,yyline,yychar,yytext());}
\n                  {yychar = 1;}
{UNUSED}            {}
{COMMENTS}          {}
{COMMENTM}          {}
.                   {addError(yyline,yychar,yytext());}