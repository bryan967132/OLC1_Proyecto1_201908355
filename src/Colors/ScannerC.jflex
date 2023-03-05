/* 1. Package e importaciones */
package Colors;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Colors.WordPainter;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */

%{
    WordPainter painter;
    public ScannerC(java.io.Reader in,WordPainter painter) {
        yyline = 0;
        yychar = 0;
        this.zzReader = in;
        this.painter = painter;
    }
%}

//Directivas
%class ScannerC
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
ID = [a-zA-Z][a-zA-Z0-9\_]*
STRING = \"(([^\"\\]?|\\.)*)\"
ASCII = [!-\/:-@\[-`{-\}]
COMMENTS = "//"([^\r\n]*)?
COMMENTM = "<!"([^<!>]*)?"!>"
%%

/* 3. Reglas Semanticas */

"CONJ"              {return new Symbol(Sym.RW_CONJ,yychar,yylength(),yytext());}
{CHARACTER}         {return new Symbol(Sym.CHAR,yychar,yylength(),yytext());}
{ID}                {return new Symbol(Sym.ID,yychar,yylength(),yytext());}
{STRING}            {return new Symbol(Sym.STRING,yychar,yylength(),yytext());}
"{"                 {return new Symbol(Sym.LBRACKET,yychar,yylength(),yytext());}
"}"                 {return new Symbol(Sym.RBRACKET,yychar,yylength(),yytext());}
";"                 {return new Symbol(Sym.SEMICOLON,yychar,yylength(),yytext());}
":"                 {return new Symbol(Sym.COLON,yychar,yylength(),yytext());}
","                 {return new Symbol(Sym.COMMA,yychar,yylength(),yytext());}
"|"                 {return new Symbol(Sym.OR,yychar,yylength(),yytext());}
"+"                 {return new Symbol(Sym.POSITIVE,yychar,yylength(),yytext());}
"*"                 {return new Symbol(Sym.KLEENE,yychar,yylength(),yytext());}
"?"                 {return new Symbol(Sym.OPTIONAL,yychar,yylength(),yytext());}
"."                 {return new Symbol(Sym.CONCAT,yychar,yylength(),yytext());}
"~"                 {return new Symbol(Sym.TILDE,yychar,yylength(),yytext());}
"\\\""              {return new Symbol(Sym.DOUBLEQUOTE,yychar,yylength(),yytext());}
"\\\'"              {return new Symbol(Sym.SINGLEQUOTE,yychar,yylength(),yytext());}
"\\n"               {return new Symbol(Sym.ENTER,yychar,yylength(),yytext());}
"%%"                {return new Symbol(Sym.LIMIT,yychar,yylength(),yytext());}
(\-[\s]*\>)         {return new Symbol(Sym.PROMPT,yychar,yylength(),yytext());}
{ASCII}             {return new Symbol(Sym.CHAR,yychar,yylength(),yytext());}
\n                  {}
{UNUSED}            {}
{COMMENTS}          {painter.COMMENT(yychar,yylength());}
{COMMENTM}          {painter.COMMENT(yychar,yylength());}
.                   {painter.ERROR(yychar,yylength());}