package Thompson;
import java.util.Stack;
import Colors.Token;
import Controller.Regex;
public class Thompson {
    private int id;
    private Node node;
    private Node start;
    private Stack<Node> stack;
    private Stack<Token> expression;
    private Structs structs;
    private Token token;
    public Thompson() {}
    public Thompson(Regex regex) {
        this.id = 0;
        this.expression = clone(regex.expression);
        this.stack = new Stack<>();
        this.structs = new Structs();
    }
    public void build() {
        while(!isEmptyStack()) {
            token = popTokenStack();
            switch(token.type) {
                case ID:
                    node = new Node(String.valueOf(id),token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case ENTER:
                    node = new Node(String.valueOf(id),token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case DOUBLEQUOTE:
                    node = new Node(String.valueOf(id),token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case SINGLEQUOTE:
                    node = new Node(String.valueOf(id),token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case STRING:
                    node = new Node(String.valueOf(id),token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case END:
                    node = new Node(String.valueOf(id),token.lexeme);
                    stack.push(node);
                    id ++;   
                    break;
                case OR:
                    node = structs.OR(String.valueOf(id),stack.pop(),stack.pop());
                    stack.push(node);
                    id ++;
                    break;
                case CONCAT:
                    node = structs.CONCAT(String.valueOf(id),stack.pop(),stack.pop());
                    stack.push(node);
                    id ++;
                    break;
                case POSITIVE:
                    node = structs.POSITIVE(String.valueOf(id),stack.pop());
                    stack.push(node);
                    id ++;
                    break;
                case KLEENE:
                    node = structs.KLEENE(String.valueOf(id),stack.pop());
                    stack.push(node);
                    id ++;
                    break;
                case OPTIONAL:
                    node = structs.OPTIONAL(String.valueOf(id),stack.pop());
                    stack.push(node);
                    id ++;
                    break;
                default:
                    break;
            }
        }
        start = stack.pop();
        start.last.accept = true;
    }
    public String getDot() {
        return "digraph AFN {\n\tgraph[fontname=\"Consolas\" labelloc=t];\n\tnode[shape=circle];\n\trankdir = LR;" + structs.getDot(start) + "\n}";
    }
    private Token popTokenStack() {
        return expression.pop();
    }
    private boolean isEmptyStack() {
        return expression.isEmpty();
    }
    private Stack<Token> clone(Stack<Token> expression) {
        Stack<Token> expressionClone = new Stack<>();
        for(Token token : expression) {
            expressionClone.push(token);
        }
        return expressionClone;
    }
}