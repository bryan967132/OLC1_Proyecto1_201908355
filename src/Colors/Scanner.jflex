/* 1. Package e importaciones */
package Colors;
%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */

%{
    WordPainter painter;
    public Scanner(java.io.Reader in,WordPainter painter) {
        this.zzReader = in;
        this.painter = painter;
    }
%}

//Directivas
%class Scanner
%public
%type Token
%char
%column
%full
%line
%unicode

//Expresiones regulares
UNUSED=[ \r\t]+
CHARACTER = [a-zA-Z0-9]
ID = [a-zA-Z0-9\_]+
STRING = \"(([^\"\\]?|\\.)*)\"

COMMENTS = "//"([^\r\n]*)?
COMMENTM = "<!"([^<!>]*)?"!>"
%%

/* 3. Reglas Semanticas */

"CONJ"              {return new Token(yytext(),yychar,yylength(),Tokens.RW_CONJ);}
{CHARACTER}         {return new Token(yytext(),yychar,yylength(),Tokens.CHAR);}
"!"                 {return new Token(yytext(),yychar,yylength(),Tokens.CHAR);}
"&"                 {return new Token(yytext(),yychar,yylength(),Tokens.CHAR);}
{ID}                {return new Token(yytext(),yychar,yylength(),Tokens.ID);}
{STRING}            {return new Token(yytext(),yychar,yylength(),Tokens.STRING);}
"{"                 {return new Token(yytext(),yychar,yylength(),Tokens.LBRACKET);}
"}"                 {return new Token(yytext(),yychar,yylength(),Tokens.RBRACKET);}
";"                 {return new Token(yytext(),yychar,yylength(),Tokens.SEMICOLON);}
":"                 {return new Token(yytext(),yychar,yylength(),Tokens.COLON);}
","                 {return new Token(yytext(),yychar,yylength(),Tokens.COMMA);}
"->"                {return new Token(yytext(),yychar,yylength(),Tokens.PROMPT);}
"|"                 {return new Token(yytext(),yychar,yylength(),Tokens.OR);}
"+"                 {return new Token(yytext(),yychar,yylength(),Tokens.POSITIVE);}
"*"                 {return new Token(yytext(),yychar,yylength(),Tokens.KLEENE);}
"."                 {return new Token(yytext(),yychar,yylength(),Tokens.CONCAT);}
"~"                 {return new Token(yytext(),yychar,yylength(),Tokens.TILDE);}
"%%"                {return new Token(yytext(),yychar,yylength(),Tokens.LIMIT);}
\n                  {}
{UNUSED}            {}
{COMMENTS}          {painter.COMMENT(yychar,yylength());}
{COMMENTM}          {painter.COMMENT(yychar,yylength());}
.                   {painter.ERROR(yychar,yylength());}