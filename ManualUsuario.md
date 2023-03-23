# EXREGAN
## Manual de Usuario

## Índice

1. [Opciones Para Archivos](#1-opciones-para-archivos)
    1. [Abrir Archivo](#abrir-archivo)
    2. [Nuevo Archivo](#nuevo-archivo)
    3. [Guardar Como](#guardar-como)
2. [Opciones Para Entradas](#2-opciones-para-entradas)
    1. [Analizar Entrada](#analizar-entrada)
    2. [Validar Cadenas](#validar-cadenas)
    3. [Guardar](#guardar)
3. [Grafos Y Tablas](#3-grafos-y-tablas)
    1. [Árbol de Expresión](#árbol-de-expresión)
    2. [Tabla de Siguientes](#tabla-de-siguientes)
    3. [Tabla de Transiciones](#tabla-de-transiciones)
    4. [AFD](#afd)
    5. [AFND](#afnd)
4. [Reporte De Errores](#4-reporte-de-errores)
5. [Expresion Regular: Notación Prefija O Polaca](#5-expresión-regular)
6. [Sintaxis del Lenguaje](#6-sintaxis-del-lenguaje)

## 1. Opciones Para Archivos
* ### Abrir Archivo
    * Buscar el directorio donde se encuentra el archivo.
    <img title="Abrir" alt="Abrir" src="Images/ManualUsuario/Abrir.png">

    * Seleccionar el archivo para abrirlo y se mostrará en el editor de código.
    <img title="Abierto" alt="Abierto" src="Images/ManualUsuario/Abierto.png">

* ### Nuevo Archivo
    * Seleccionar el directorio.
    <img title="Nuevo" alt="Nuevo" src="Images/ManualUsuario/Nuevo.png">

    * Nombrar el archivo y aceptar.
    <img title="Nombrando Archivo" alt="Nombrando Archivo" src="Images/ManualUsuario/NuevoNombrar.png">

    * Al crearlo se abrirá el archivo en blanco en el editor de código.
    <img title="Archivo Creado" alt="Archivo Creado" src="Images/ManualUsuario/NuevoCreado.png">

* ### Guardar Como
    * Seleccionar el directorio en el que se guardará el nuevo archivo.
    <img title="Guardar Como" alt="Guardar Como" src="Images/ManualUsuario/GuardarComo.png">

    * Nombrar el archivo y aceptar.
    <img title="Nombrando Archivo" alt="Nombrando Archivo" src="Images/ManualUsuario/GuardarComoNombrar.png">

    * Al crearlo se abrirá el archivo con código en el editor de código.
    <img title="Archivo Creado" alt="Archivo Creado" src="Images/ManualUsuario/GuardadoComo.png">

[Subir](#exregan)

## 2. Opciones Para Entradas
* ### Analizar Entrada
    Mostrará el resultado en consola y generará automáticamente los autómatas y los grafos.
    [Ver Grafos y Tablas](#3-grafos-y-tablas).
    <img title="Analizar" alt="Analizar" src="Images/ManualUsuario/Analizar.png">

* ### Validar Cadenas
    Mostrará el resultado en consola y generará un archivo de salida en formato JSON.
    <img title="Validar" alt="Validar" src="Images/ManualUsuario/Validar.png">

    * Salida.<br>
    <img title="Salida JSON" alt="Salida JSON" src="Images/ManualUsuario/Salida.png">

* ### Guardar
    Actualiza el archivo con el código modificado en el editor de código.
    <img title="Guardar" alt="Guardar" src="Images/ManualUsuario/Guardar.png">

[Subir](#exregan)

## 3. Grafos Y Tablas
* ### Árbol de Expresión
    Árbol construido mediante Método del Árbol. Cálculos realizados:
    1. Anulables.
    2. Primeras posiciones.
    3. Últimas posiciones.

    <img title="Árbol" alt="Árbol" src="Images/ManualUsuario/Arbol.png">

* ### Tabla de Siguientes
    <img title="Siguientes" alt="Siguientes" src="Images/ManualUsuario/Siguientes.png">

* ### Tabla de Transiciones
    <img title="Transiciones" alt="Transiciones" src="Images/ManualUsuario/Transiciones.png">

* ### AFD
    <img title="AFD" alt="AFD" src="Images/ManualUsuario/AFD.png">

* ### AFND
    <img title="AFND" alt="AFND" src="Images/ManualUsuario/AFND.png">

[Subir](#exregan)

## 4. Reporte De Errores
El reporte se genera y abre automáticamente al momento del análisis si se detecta errores léxicos o sintácticos o ambos.
<img title="Reporte de Errores" alt="Reporte de Errores" src="Images/ManualUsuario/ReporteErrorL.png">

[Subir](#exregan)

## 5. Expresión Regular
* ### Expresion Regular en Notación Polaca
    Los operadores se escriben antes que los operandos.
    <br>Ejemplo:<br>
    * | operando operando
    * . operando operando
    * \+ operando
    * \* operando
    * ? operando
* ### Ejemplos de  Expresiones Regulares en Notación Prefija o Polaca
    |Notación Prefija|Notación Infija|
    |----------------|---------------|
    |+ \| * operando operando|((operando)* \| operando)+|
    |. + operando \| operando operando|(operando)+(operando \| operando)|

[Subir](#exregan)

## 6. Sintaxis Del Lenguaje

```java
{
    CONJ:minuscula->a~z;
    CONJ:digito->0~9;
    CONJ:mayuscula->A~Z;
    CONJ:digito_par->0,2,4,6,8;
    CONJ:letra->A,b,C,d;
    CONJ:simbolo->!~&;
    
    ExprReg1->.{letra}*|"_"|{letra}{digito};
    ExpresionReg2->.{digito}."."+{digito};
    RegEx3->.{digito}*|"_"|{letra}{digito};
    
    %%
    
    ExpReg1:"primerLexemaCokoa";
    ExpresionReg2:"34.44";
}
```

[Subir](#exregan)