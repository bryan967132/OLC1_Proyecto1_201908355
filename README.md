# Proyecto 1 OLC1 1S 2023

## Sintaxis Del Lenguaje

```java
{
    //Este es un comentario en nuestro programa@
    
    CONJ:minuscula->a~z;
    CONJ:digito->0~9;
    CONJ:mayuscula->A~Z;
    CONJ:digito_par->0,2,4,6,8;
    CONJ:letra->A,b,C,d;
    CONJ:simbolo->!~&;

    <!Este es un comentario
      en nuestro programa@
    !>
    
    ExprReg1->.{letra}*|"_"|{letra}{digito};
    ExpresionReg2->.{digito}."."+{digito};
    RegEx3->.{digito}*|"_"|{letra}{digito};
    
    %%
    
    ExpReg1:"primerLexemaCokoa";
    ExpresionReg2:"34.44";
}
```

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

analysis -> expresion analysis | expresion

expresion -> ID ':' STRING ';'
```