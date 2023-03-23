# EXREGAN
## Manual Técnico

## Índice

1. [Análisis Léxico](#1-análisis-lexico)
2. [Análisis Sintáctico](#2-análisis-sintáctico)
3. [Método Del Árbol](#3-método-del-árbol)
4. [Cálculo De Siguientes](#4-cálculo-de-siguientes)
5. [Cálculo De Transiciones](#5-cálculo-de-transiciones)

## 1. Análisis Léxico
* ### Tabla de Tokens
    |Descripción|Patrón|Expresión Regular|Ejemplo|Nombre de Token|
    |-----------|------|-----------------|-------|---------------|
    |Reservada CONJ|Palabra CONJ|CONJ|CONJ|RW_CONJ|
    |Caracteres Alfabéticos|Caracter a, b, c, ..., x, y, z, A, B, C, ..., X, Y, Z|[a-zA-Z]|a, c, D, E|CHAR|
    |Caracteres Numéricos|Caracter 0, 1, 2, 3, 4, 5, 6, 7, 8, 9|[0-9]|0, 4, 6, 3|CHARNUM|
    |Identificadores|Secuencia de Caracteres Alfanuméricos|[a-zA-Z][a-zA-Z0-9\_]*|cadena, num_pares, expresion_5|ID|
    |Cadenas entre Comillas|Secuencia de Caracteres Alfanuméricos entre Comillas|\\"(([^\\"\\\\]?\|\\\\.)*)\\"|"Proyecto1 OLC1", "H", "c"|STRING|
    |Llave de Apertura|Caracter \{|\{|\{|LBRACKET|
    |Llave de Cierre|Caracter \}|\}|\}|RBRACKET|
    |Punto y Coma|Caracter ;|;|;|SEMICOLON|
    |Dos Puntos|Caracter :|:|:|COLON|
    |Coma|Caracter ,|,|,|COMMA|
    |Operador Or|Caracter \||\||\||OR|
    |Cerradura Positiva|Caracter +|+|+|POSITIVE|
    |Cerradura de Kleene|Caracter *|*|*|KLEENE|
    |Opcional|Caracter ?|?|?|OPTIONAL|
    |Concatenación|Caracter .|.|.|CONCAT|
    |Virgulilla|Caracter ~|~|~|TILDE|
    |Caracter de Escape Comilla|Caracter \\"|\\\\\\"|\\"|DOUBLEQUOTE|
    |Caracter de Escape Comilla Simple|Caracter \\'|\\\\\\'|\\'|SINGLEUOTE|
    |Caracter de Escape Salto de Línea|Caracter \\n|\\\\n|\\n|ENTER|
    |Delimitador|Secuencia de caracteres %%|%%|%%|LIMIT|
    |Prompt (Flecha)|Secuencia de Caracteres ->|(\\-[\s]*\\>)|->, - >, -  >|PROMPT|
    |Caracteres Especiales ASCII 33 al 125 excluyendo Caracteres Alfanuméricos|Caracteres !, ", #, ..., {, \|, }|[!-\\/:-@\\[-`{-\\}]|@, [, ], -, \|, +|CHAR|
    |Comentarios Simples|Secuencia de Caracteres precedida de //|\\/\\/([^\r\n]*)?|// comentario simple
    |Comentarios Multilíneas|Secuencia de Caracteres entre <! y !>|\\<\\!([^<!>]*)?\\!\\>|<! comentario multilínea !>|

## 2. Análisis Sintáctico
* ### Expresion Regular en Notación Polaca
    Los operadores se escriben antes que los operandos.
    <br>Ejemplo:<br>
    * | operando operando
    * . operando operando
    * \+ operando
    * \* operando
    * ? operando

* ### Gramática Libre del Contexto
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
## 3. Método Del Árbol
1. ### Construcción del Árbol
    Implementación en Java: Se implementó haciendo uso de pilas ya que las expresiones regulares en notación prefija se leen de derecha a izquierda para su interpretación y un árbol binario para formar la estructura del árbol.<br><br>
    Se hace uso de la clase Node para la construcción del árbol:
    ```java
    ArrayList<Integer> firsts = new ArrayList<>();
    ArrayList<Integer> lasts = new ArrayList<>();
    ArrayList<Integer> nexts = new ArrayList<>();
    boolean anulable;
    int i;
    int id;
    Node left;
    Node parent;
    Node right;
    String value;
    Type type;
    Type type1;
    public Node(int id,String value,Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }
    public Node(int id,String value,Type type,Type type1) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.type1 = type1;
    }
    ```

    Condiciones para cálculo de anulables:
    |Terminal|Anulabilidad|
    |--------|-----------|
    |C<sub>1</sub> *|Anulable|
    |C<sub>1</sub> +|(Anulable(C<sub>1</sub>)) ? Anulable : No Anulable|
    |C<sub>1</sub> ?|Anulable|
    |C<sub>1</sub> \| C<sub>2</sub>|(Anulable(C<sub>1</sub>) \|\| Anulable(C<sub>2</sub>)) ? Anulable : No Anulable|
    |C<sub>1</sub> C<sub>2</sub>|(Anulable(C<sub>1</sub>) && Anulable(C<sub>2</sub>)) ? Anulable : No Anulable|

    Se implementó el método build de la Clase Tree para construir la estructura y en el que se calculan los nodos anulables:

    ```java
    public void build() {
        while(!isEmptyStack()) {
            token = popTokenStack();
            switch(token.type) {
                case ID:
                    node = new Node(id,token.lexeme,Type.LEAF,Type.ID);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case ENTER:
                    node = new Node(id,token.lexeme,Type.LEAF,Type.ENTER);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case DOUBLEQUOTE:
                    node = new Node(id,token.lexeme,Type.LEAF,Type.DOUBLEQUOTE);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case SINGLEQUOTE:
                    node = new Node(id,token.lexeme,Type.LEAF,Type.SINGLEQUOTE);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case STRING:
                    node = new Node(id,token.lexeme.replace("\"",""),Type.LEAF,Type.STRING);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case END:
                    node = new Node(id,token.lexeme,Type.LEAF);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case OR:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.left.parent = node;
                    node.right = stack.pop();
                    node.right.parent = node;
                    node.anulable = node.left.anulable || node.right.anulable;
                    stack.push(node);
                    id ++;
                    break;
                case CONCAT:
                    node = new Node(id,".",token.type);
                    node.left = stack.pop();
                    node.left.parent = node;
                    node.right = stack.pop();
                    node.right.parent = node;
                    node.anulable = node.left.anulable && node.right.anulable;
                    stack.push(node);
                    id ++;
                    break;
                case POSITIVE:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.left.parent = node;
                    node.anulable = node.left.anulable;
                    stack.push(node);
                    id ++;
                    break;
                case KLEENE:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.left.parent = node;
                    node.anulable = true;
                    stack.push(node);
                    id ++;
                    break;
                case OPTIONAL:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.left.parent = node;
                    node.anulable = true;
                    stack.push(node);
                    id ++;
                    break;
                default:
                    break;
            }
        }
        root = stack.pop();
    }
    ```
2. ### Asignación de Etiquetas a los Nodos Hoja
    Asignación de etiquetas de las hojas nodo. Se utilizan los siguientes métodos:
    ```java
    public void createIDNodes() {
        createIDNodes(root);
    }
    private void createIDNodes(Node node) {
        if(node != null) {
            if(node.type == Type.LEAF) {
                node.i = i;
                i ++;
                return;
            }
            createIDNodes(node.left);
            createIDNodes(node.right);
        }
    }
    ```
3. ### Cálculo de Primeras Posiciones
    Conciciones para los cálculos:
    |Terminal|Primeras Posiciones|
    |--------|-----------|
    |C<sub>1</sub> *|Primeros(C<sub>1</sub>)|
    |C<sub>1</sub> +|Primeros(C<sub>1</sub>)|
    |C<sub>1</sub> ?|Primeros(C<sub>1</sub>)|
    |C<sub>1</sub> \| C<sub>2</sub>|Primeros(C<sub>1</sub>) U Primeros(C<sub>2</sub>)|
    |C<sub>1</sub> C<sub>2</sub>|(Anulable(C<sub>1</sub>)) ? Primeros(C<sub>1</sub>) U Primeros(C<sub>2</sub>) : Primeros(C<sub>1</sub>)|

    Métodos implementados para el cálculo:
    ```java
    public void calculateFirsts() {
        calculateFirsts(root);
    }
    private void calculateFirsts(Node node) {
        if(node != null) {
            if(node.type == Type.LEAF) {
                node.firsts.add(node.i);
                return;
            }
            calculateFirsts(node.left);
            calculateFirsts(node.right);
            node.firsts.addAll(node.left.firsts);
            if(node.type == Type.OR) {
                node.firsts.addAll(node.right.firsts);
            }
            else if(node.type == Type.CONCAT) {
                if(node.left.anulable) {
                    node.firsts.addAll(node.right.firsts);
                }
            }
        }
    }
    ```
4. ### Cálculo de Últimas Posiciones
    Conciciones para los cálculos:
    |Terminal|Últimas Posiciones|
    |--------|-----------|
    |C<sub>1</sub> *|Últimos(C<sub>1</sub>)|
    |C<sub>1</sub> +|Últimos(C<sub>1</sub>)|
    |C<sub>1</sub> ?|Últimos(C<sub>1</sub>)|
    |C<sub>1</sub> \| C<sub>2</sub>|Últimos(C<sub>1</sub>) U Últimos(C<sub>2</sub>)|
    |C<sub>1</sub> C<sub>2</sub>|(Anulable(C<sub>2</sub>)) ? Últimos(C<sub>1</sub>) U Últimos(C<sub>2</sub>) : Últimos(C<sub>2</sub>)|
    Métodos implementados para el cálculo:
    ```java
    public void calculateLasts() {
        calculateLasts(root);
    }
    private void calculateLasts(Node node) {
        if(node != null) {
            if(node.type == Type.LEAF) {
                node.lasts.add(node.i);
                return;
            }
            calculateLasts(node.left);
            calculateLasts(node.right);
            if(node.type == Type.OR || node.type == Type.POSITIVE || node.type == Type.KLEENE || node.type == Type.OPTIONAL) {
                node.lasts.addAll(node.left.lasts);
                if(node.type == Type.OR) {
                    node.lasts.addAll(node.right.lasts);
                }
            }
            else if(node.type == Type.CONCAT) {
                if(node.right.anulable) {
                    node.lasts.addAll(node.left.lasts);
                }
                node.lasts.addAll(node.right.lasts);
            }
        }
    }
    ```

## 4. Cálculo De Siguientes
Conciciones para los cálculos:<br>
|Terminal|Siguientes Posiciones C<sub>1</sub>|
|--------|-----------|
|C<sub>1</sub> *|Primeros(C<sub>1</sub>)|
|C<sub>1</sub> +|Primeros(C<sub>1</sub>)|
|C<sub>1</sub> ?||
|C<sub>1</sub> \| C<sub>2</sub>||
|C<sub>1</sub> C<sub>2</sub>|Primeros(C<sub>2</sub>)|

Método implementado desde la clase Tree:
```java
public void calculateNexts() {
    nexts = new NextsTable(root);
}
```
Clase NextTable.
```java
ArrayList<Node> leafs;
Node root;
public NextsTable(Node root) {
    this.root = root;
    this.leafs = new ArrayList<>();
    calculateNexts();
}
public void calculateNexts() {
    fillLeafs(root);
    for(int i = 0; i < leafs.size(); i ++) {
        calculateNexts(leafs.get(i),leafs.get(i));
    }
}
private void calculateNexts(Node node1,Node node2) {
    if(node1.type == Type.LEAF && node1.value.equals("#")) {
        node1.nexts = null;
        return;
    }
    if(node2 != null) {
        if(node2.parent.type == Type.CONCAT) {
            if(node2.parent.right.id == node2.id) {
                calculateNexts(node1,node2.parent);
                return;
            }
            node1.nexts.addAll(node2.parent.right.firsts);
            if(node2.parent.right.anulable) {
                calculateNexts(node1,node2.parent);
            }
            return;
        }
        if(node2.parent.type == Type.OR || node2.parent.type == Type.OPTIONAL) {
            calculateNexts(node1,node2.parent);
            return;
        }
        if(node2.parent.type == Type.KLEENE || node2.parent.type == Type.POSITIVE) {
            node1.nexts.addAll(node2.parent.firsts);
            calculateNexts(node1,node2.parent);
        }
    }
}
private void fillLeafs(Node node) {
    if(node != null) {
        if(node.type == Type.LEAF) {
            leafs.add(node);
            return;
        }
        fillLeafs(node.left);
        fillLeafs(node.right);
    }
}
```

## 5. Cálculo De Transiciones
Método implementado desde la clase Tree:
```java
public void calculateTransitions() {
    transitions.add(new Transition(0,"",new HashSet<Integer>(root.firsts)));
    table = new TransitionTable(transitions,nexts.leafs);
    table.build();
    for(Transition transition : table.transitions) {
        if(transition.nexts.contains(root.right.i)) {
            transition.accept = true;
        }
    }
}
```
Clase Transition Table.
```java
ArrayList<Node> nexts = new ArrayList<>();
ArrayList<Transition> transitions = new ArrayList<>();
ArrayList<Transition> tmpTrnst = new ArrayList<>();
ArrayList<Terminal> terminals = new ArrayList<>();
public TransitionTable(ArrayList<Transition> transitions,ArrayList<Node> nexts) {
    this.transitions = transitions;
    this.nexts = nexts;
}
public void build() {
    addTerminals();
    build(0);
}
private void build(int i) {
    if(i < transitions.size()) {
        int position;
        Node next;
        Transition newTrnst;
        Transition transition = transitions.get(i);
        for(Terminal terminal : terminals) {
            newTrnst = new Transition(transitions.size(),terminal.value);
            for(int nxt : transition.nexts) {
                next = nexts.get(nxt - 1);
                if(next.value.equals(terminal.value)) {
                    newTrnst.nexts.addAll(next.nexts);
                }
            }
            position = existTransition(newTrnst);
            if(position == -1) {
                if(newTrnst.nexts.size() > 0) {
                    transition.changes.put(terminal.value,new Change(transitions.size(),terminal.value,terminal.type));
                    transitions.add(newTrnst);
                }
            }
            else {
                transition.changes.put(terminal.value,new Change(position,terminal.value,terminal.type));
            }
        }
        build(i + 1);
    }
}
private int existTransition(Transition transition) {
    for(int i = 0; i < transitions.size(); i ++) {
        if(transitions.get(i).nexts.equals(transition.nexts)) {
            return i;
        }
    }
    return -1;
}
private void addTerminals() {
    Terminal newTerminal;
    for(Node next : nexts) {
        if(!next.value.equals("#")) {
            newTerminal = new Terminal(next.value,next.type1);
            if(!verifyTerminal(newTerminal)) {
                terminals.add(newTerminal);
            }
        }
    }
}
private boolean verifyTerminal(Terminal newTerminal) {
    for(Terminal terminal : terminals) {
        if(terminal.value.equals(newTerminal.value)) {
            return true;
        }
    }
    return false;
}
```