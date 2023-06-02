/* 1. Package e importaciones */
package Language;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import Colors.WordPainter;
import Components.ErrorL;

%%

/* 2. Configuraciones para el analisis (Operaciones y Declaraciones) */
%{
    WordPainter painter;
    public ScannerF(java.io.Reader in,WordPainter painter) {
        yyline = 0;
        yychar = 0;
        this.zzReader = in;
        this.painter = painter;
    }
    ArrayList<ErrorL> errors = new ArrayList<>();
    public ArrayList<ErrorL> getErrors() {
        return errors;
    }
    void addError(int line,int column,String character) {
        errors.add(new ErrorL(line,column,character));
    }
%}

//Directivas
%class ScannerF
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
STRING = \"(([^\"\\]?|\\.)*)\"
ASCII = [!-\/:-@\[-`{-\}]
COMMENTS = "//"([^\r\n]*)?
COMMENTM = [<][!][^!]*[!]+([^>!][^!]*[!]+)*[>]

%%

/* 3. Reglas Semanticas */

"CONJ"              {return new Symbol(Sym.RW_CONJ,yychar,yylength(),yytext());}
{CHARALPHA}         {return new Symbol(Sym.TK_char,yychar,yylength(),yytext());}
{CHARNUM}           {return new Symbol(Sym.TK_charnum,yychar,yylength(),yytext());}
{ID}                {return new Symbol(Sym.TK_id,yychar,yylength(),yytext());}
{STRING}            {return new Symbol(Sym.TK_str,yychar,yylength(),yytext());}
"{"                 {return new Symbol(Sym.TK_lbr,yychar,yylength(),yytext());}
"}"                 {return new Symbol(Sym.TK_rbr,yychar,yylength(),yytext());}
"("                 {return new Symbol(Sym.TK_lpar,yychar,yylength(),yytext());}
")"                 {return new Symbol(Sym.TK_rpar,yychar,yylength(),yytext());}
";"                 {return new Symbol(Sym.TK_semicolon,yychar,yylength(),yytext());}
":"                 {return new Symbol(Sym.TK_colon,yychar,yylength(),yytext());}
","                 {return new Symbol(Sym.TK_comma,yychar,yylength(),yytext());}
"|"                 {return new Symbol(Sym.TK_or,yychar,yylength(),yytext());}
"."                 {return new Symbol(Sym.TK_concat,yychar,yylength(),yytext());}
"+"                 {return new Symbol(Sym.TK_positive,yychar,yylength(),yytext());}
"*"                 {return new Symbol(Sym.TK_kleene,yychar,yylength(),yytext());}
"?"                 {return new Symbol(Sym.TK_optional,yychar,yylength(),yytext());}
"~"                 {return new Symbol(Sym.TK_tilde,yychar,yylength(),yytext());}
"\\\""              {return new Symbol(Sym.TK_doublequ,yychar,yylength(),yytext());}
"\\\'"              {return new Symbol(Sym.TK_singlequ,yychar,yylength(),yytext());}
"\\n"               {return new Symbol(Sym.TK_newline,yychar,yylength(),yytext());}
"%%"                {return new Symbol(Sym.TK_limit,yychar,yylength(),yytext());}
(\-[\s]*\>)         {return new Symbol(Sym.TK_prompt,yychar,yylength(),yytext());}
{ASCII}             {return new Symbol(Sym.TK_charascii,yychar,yylength(),yytext());}
\n                  {}
{UNUSED}            {}
{COMMENTS}          {painter.COMMENT(yychar,yylength());}
{COMMENTM}          {painter.COMMENT(yychar,yylength());}
.                   {painter.ERROR(yychar,yylength());}