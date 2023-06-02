# Proyecto 1 OLC1 1S 2023

## Sintaxis Del Lenguaje

```java
{
//Conjuntos
CONJ: lower -> a~z;
CONJ: upper -> A~Z;
CONJ: digit -> 0~9;
<!
--------------COMENTARIO MULTILÍNEA--------------
!>
//Expresiones Regulares
REGEX1 -> ("1"?."2"*|"3"+."2"*)|("2"?."3"+."1"|"3"."1"*);
REGEX2 -> ("a"."b"."c")+|"a"?."x"."y"."z"|("0"|"1")+;
numero -> {digit}+.(".".{digit}+)?;

%%
//Validación de Cadenas
REGEX1: "311111";
REGEX1: "233331";
numero: "31.001";
numero: "0";
REGEX2: "abcabc";
REGEX2: "axyz";
REGEX2: "111111";
}
```

## Gramática Libre De Contexto
```java
INI ->
    '{' CODE '}' |
    '{' '}'

CODE -> 
    DECLARATIONS '%%' EVALUATIONS |
    DECLARATIONS '%%'             |
    DECLARATIONS                  |
    '%%' EVALUATIONS              |
    '%%'

DECLARATIONS ->
    DECLARATIONS DECLARATION |
    DECLARATION

DECLARATION ->
    'CONJ' ':' TK_id '->' ELEMENTS ';' |
    TK_id '->' OPERATION ';'

ELEMENTS ->
    TK_char '~' TK_char |
    SPECIFIC

SPECIFIC ->
    SPECIFIC ',' TK_char |
    TK_char

OPERATION ->
    OPERATION '.' OPERATION |
    OPERATION '|' OPERATION |
    OPERATION '*' |
    OPERATION '+' |
    OPERAND

OPERAND ->
    '{' TK_id '}' |
    TK_str

EVALUATIONS ->
    EVALUATIONS EVALUATION |
    EVALUATION

EVALUATION -> TK_id ':' TK_str ';'
```