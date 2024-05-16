/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Components.ErrorL;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    ArrayList<ErrorL> errors = new ArrayList<>();
    public ArrayList<ErrorL> getErrors() {
        return errors;
    }
    void addError(int line, int column, String character) {
        errors.add(new ErrorL(line, column, character));
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

//Constructor
%init{
    yyline = 1;
    yychar = 1;
%init}

//Expresiones regulares
UNUSED=[ \r\t]+
CHARALPHA = [a-zA-Z]
CHARNUM = [0-9]
ID = [a-zA-Z][a-zA-Z0-9\_]*
STRING = \"(([^\"\\]|\\.)*)\"
ASCII = [!-\/:-@\[-`{-\}]
COMMENTS = "//"([^\r\n]*)?
COMMENTM = [<][!][^!]*[!]+([^>!][^!]*[!]+)*[>]

%%

/* 3. Reglas Semanticas */

"CONJ"              {return new Symbol(Sym.RW_CONJ,      yyline, yychar, yytext());}
{CHARALPHA}         {return new Symbol(Sym.TK_char,      yyline, yychar, yytext());}
{CHARNUM}           {return new Symbol(Sym.TK_charnum,   yyline, yychar, yytext());}
{ID}                {return new Symbol(Sym.TK_id,        yyline, yychar, yytext());}
{STRING}            {return new Symbol(Sym.TK_str,       yyline, yychar, yytext());}
"{"                 {return new Symbol(Sym.TK_lbr,       yyline, yychar, yytext());}
"}"                 {return new Symbol(Sym.TK_rbr,       yyline, yychar, yytext());}
"("                 {return new Symbol(Sym.TK_lpar,      yyline, yychar, yytext());}
")"                 {return new Symbol(Sym.TK_rpar,      yyline, yychar, yytext());}
";"                 {return new Symbol(Sym.TK_semicolon, yyline, yychar, yytext());}
":"                 {return new Symbol(Sym.TK_colon,     yyline, yychar, yytext());}
","                 {return new Symbol(Sym.TK_comma,     yyline, yychar, yytext());}
"|"                 {return new Symbol(Sym.TK_or,        yyline, yychar, yytext());}
"."                 {return new Symbol(Sym.TK_concat,    yyline, yychar, yytext());}
"+"                 {return new Symbol(Sym.TK_positive,  yyline, yychar, yytext());}
"*"                 {return new Symbol(Sym.TK_kleene,    yyline, yychar, yytext());}
"?"                 {return new Symbol(Sym.TK_optional,  yyline, yychar, yytext());}
"~"                 {return new Symbol(Sym.TK_tilde,     yyline, yychar, yytext());}
"\\\""              {return new Symbol(Sym.TK_doublequ,  yyline, yychar, yytext());}
"\\\'"              {return new Symbol(Sym.TK_singlequ,  yyline, yychar, yytext());}
"\\n"               {return new Symbol(Sym.TK_newline,   yyline, yychar, yytext());}
"%%"                {return new Symbol(Sym.TK_limit,     yyline, yychar, yytext());}
(\-[\s]*\>)         {return new Symbol(Sym.TK_prompt,    yyline, yychar, yytext());}
{ASCII}             {return new Symbol(Sym.TK_charascii, yyline, yychar, yytext());}
\n                  {yychar = 1;}
{UNUSED}            {}
{COMMENTS}          {}
{COMMENTM}          {}
.                   {addError(yyline, yychar, yytext());}