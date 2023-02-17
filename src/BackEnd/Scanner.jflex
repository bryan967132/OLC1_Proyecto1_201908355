/* 1. Package e importaciones */
package BackEnd;
//import Components.*;
import java_cup.runtime.Symbol;
//import java.util.ArrayList;
%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */

%{
    //ArrayList<Token> tokens = new ArrayList<>();
    //ArrayList<ErrorL> errors = new ArrayList<>();
    public void addToken(String lexeme,int line,int column,String type) {
        //tokens.add(new Token(lexeme,line,column,type));
    }
    public void addError(int line,int column,String character) {
        //errors.add(new ErrorL(line,column,character));
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
%ignorecase
%line
%unicode

%init{
    yyline = 1;
    yychar = 1;
%init}

//Expresiones regulares

UNUSED = [ \r\t]+
NUMBER = [0-9]+("."[0-9]+)?
ALPHA = [a-zA-Z]+
ALPHANUMERIC = [a-zA-Z0-9]+

LINETERMINATOR = \r|\n|\r\n
INPUTCHARACTER = [^\r\n]

COMMENTS = "//"{INPUTCHARACTER}*(LINETERMINATOR)?
COMMENTM = "<!" {INPUTCHARACTER}*(LINETERMINATOR)* "!>"

%%

/* 3. Reglas Semanticas */

"CONJ"      {addToken(yytext(),yyline,yychar,"WR_CONJ");    return new Symbol(Sym.WR_CONJ,yyline,yychar,yytext());}
"{"         {addToken(yytext(),yyline,yychar,"LK");         return new Symbol(Sym.LK,yyline,yychar,yytext());}
"}"         {addToken(yytext(),yyline,yychar,"RK");         return new Symbol(Sym.RK,yyline,yychar,yytext());}
"~"         {addToken(yytext(),yyline,yychar,"TILDE");      return new Symbol(Sym.TILDE,yyline,yychar,yytext());}
";"         {addToken(yytext(),yyline,yychar,"SEMICOLON");  return new Symbol(Sym.SEMICOLON,yyline,yychar,yytext());}
.           {addError(yyline,yychar,yytext());}