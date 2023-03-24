# EXREGAN

## Gramática Libre De Contexto
```java
ini -> '{' instructions '%%' analysis '}'

instructions -> instruction instructions |
                instruction

instruction -> set |
               regex

set -> 'CONJ' ':' ID '->' elements ';'

elements -> CHAR '~' CHAR |
            specific

specific -> CHAR ',' specific |
            CHAR

regex -> ID '->' operations ';'

operations -> operations operation |
              operation

operation -> '.' operation operation |
             '|' operation operation |
             '*' operation |
             '+' operation |
             node

node -> '{' ID '}' | STRING

analysis -> expression analysis | expression

expression -> ID ':' STRING ';'
```