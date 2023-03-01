package BackEnd;
import java.util.Stack;
import Colors.Token;
import Controller.Regex;
public class Tree {
    private int id;
    private Regex regex;
    private Stack<Node> stack;
    private Token token;
    private Node node;
    public Tree(Regex regex) {
        this.id = 0;
        this.regex = regex;
        this.stack = new Stack<>();
    }
    public void build() {
        while(!isEmptyStack()) {
            token = popTokenStack();
            switch(token.type) {
                case ID:
                    stack.push(new Node(id,token.lexeme));
                    id ++;
                    break;
                case STRING:
                    stack.push(new Node(id,token.lexeme));
                    id ++;
                    break;
                case END:
                    stack.push(new Node(id,token.lexeme));
                    id ++;
                    break;
                case OR:
                    node = new Node(id,token.lexeme);
                    id ++;
                    node.left = stack.pop();
                    node.right = stack.pop();
                    stack.push(node);
                    break;
                case CONCAT:
                    node = new Node(id,token.lexeme);
                    id ++;
                    node.left = stack.pop();
                    node.right = stack.pop();
                    stack.push(node);
                    break;
                case POSITIVE:
                    node = new Node(id,token.lexeme);
                    id ++;
                    node.center = stack.pop();
                    stack.push(node);
                    break;
                case KLEENE:
                    node = new Node(id,token.lexeme);
                    id ++;
                    node.center = stack.pop();
                    stack.push(node);
                    break;
                default:
                    break;
            }
        }
    }
    Token popTokenStack() {
        return regex.expression.pop();
    }
    boolean isEmptyStack() {
        return regex.expression.isEmpty();
    }
    private String getDotNodes(Node node) {
        String dot = "";
        if(node != null) {
            dot += "\n\tnodo" + node.id + "[label = \"" + node.value + "\"];";
            if(node.left != null) {
                dot += getDotNodes(node.left);
                dot += "\n\tnodo" + node.id + " -> nodo" + node.left.id + ";";
            }
            if(node.right != null) {
                dot += getDotNodes(node.right);
                dot += "\n\tnodo" + node.id + " -> nodo" + node.right.id + ";";
            }
            if(node.center != null) {
                dot += getDotNodes(node.center);
                dot += "\n\tnodo" + node.id + " -> nodo" + node.center.id + ";";
            }
        }
        return dot;
    }
    public String getDot() {
        return "digraph Tree {\n\tnode[shape = circle];" + getDotNodes(stack.pop()) + "\n}";
    }
}