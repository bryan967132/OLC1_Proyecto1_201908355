# EXREGAN

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
    '.' OPERATION OPERATION |
    '|' OPERATION OPERATION |
    '*' OPERATION           |
    '+' OPERATION           |
    '?' OPERATION           |
    OPERAND

OPERAND ->
    '{' TK_id '}' |
    TK_str

EVALUATIONS ->
    EVALUATIONS EVALUATION |
    EVALUATION

EVALUATION -> TK_id ':' TK_str ';'
```