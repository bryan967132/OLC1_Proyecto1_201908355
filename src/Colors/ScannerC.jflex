/* 1. Package e importaciones */
package Colors;
%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */

%{
    WordPainter painter;
    public ScannerC(java.io.Reader in,WordPainter painter) {
        this.zzReader = in;
        this.painter = painter;
    }
%}

//Directivas
%class ScannerC
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
ASCII = [!-\/:-@\[-`{-\}]
COMMENTS = "//"([^\r\n]*)?
COMMENTM = "<!"([^<!>]*)?"!>"
%%

/* 3. Reglas Semanticas */

"CONJ"              {return new Token(yytext(),yychar,yylength(),Type.RW_CONJ);}
{CHARACTER}         {return new Token(yytext(),yychar,yylength(),Type.CHAR);}
{ID}                {return new Token(yytext(),yychar,yylength(),Type.ID);}
{STRING}            {return new Token(yytext(),yychar,yylength(),Type.STRING);}
"{"                 {return new Token(yytext(),yychar,yylength(),Type.LBRACKET);}
"}"                 {return new Token(yytext(),yychar,yylength(),Type.RBRACKET);}
";"                 {return new Token(yytext(),yychar,yylength(),Type.SEMICOLON);}
":"                 {return new Token(yytext(),yychar,yylength(),Type.COLON);}
","                 {return new Token(yytext(),yychar,yylength(),Type.COMMA);}
"|"                 {return new Token(yytext(),yychar,yylength(),Type.OR);}
"+"                 {return new Token(yytext(),yychar,yylength(),Type.POSITIVE);}
"*"                 {return new Token(yytext(),yychar,yylength(),Type.KLEENE);}
"?"                 {return new Token(yytext(),yychar,yylength(),Type.OPTIONAL);}
"."                 {return new Token(yytext(),yychar,yylength(),Type.CONCAT);}
"~"                 {return new Token(yytext(),yychar,yylength(),Type.TILDE);}
"\\\""              {return new Token(yytext(),yychar,yylength(),Type.DOUBLEQUOTE);}
"\\\'"              {return new Token(yytext(),yychar,yylength(),Type.SINGLEQUOTE);}
"\\n"               {return new Token(yytext(),yychar,yylength(),Type.ENTER);}
"%%"                {return new Token(yytext(),yychar,yylength(),Type.LIMIT);}
(\-[\s]*\>)         {return new Token(yytext(),yychar,yylength(),Type.PROMPT);}
{ASCII}             {return new Token(yytext(),yychar,yylength(),Type.CHAR);}
\n                  {}
{UNUSED}            {}
{COMMENTS}          {painter.COMMENT(yychar,yylength());}
{COMMENTM}          {painter.COMMENT(yychar,yylength());}
.                   {painter.ERROR(yychar,yylength());}