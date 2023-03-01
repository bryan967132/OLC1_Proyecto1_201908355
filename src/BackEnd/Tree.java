package BackEnd;
import java.util.Stack;
import Colors.Token;
import Controller.Regex;
public class Tree {
    private int i;
    private int id;
    private Regex regex;
    private Stack<Node> stack;
    private Token token;
    private Node node;
    private Node root;
    public Tree(Regex regex) {
        this.i = 1;
        this.id = 0;
        this.regex = regex;
        this.stack = new Stack<>();
    }
    public void build() {
        while(!isEmptyStack()) {
            token = popTokenStack();
            switch(token.type) {
                case ID:
                    node = new Node(id,token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case STRING:
                    node = new Node(id,token.lexeme);
                    stack.push(node);
                    id ++;
                    break;
                case END:
                    node = new Node(id,token.lexeme);
                    stack.push(node);
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
                    node.left = stack.pop();
                    stack.push(node);
                    break;
                case KLEENE:
                    node = new Node(id,token.lexeme);
                    id ++;
                    node.left = stack.pop();
                    stack.push(node);
                    break;
                default:
                    break;
            }
        }
        root = stack.pop();
    }
    private String getDotNodes(Node node) {
        String dot = "";
        if(node != null) {
            dot += "\n\t" + getStructN(node);
            if(node.left != null) {
                dot += getDotNodes(node.left);
                dot += "\n\tnode" + node.id + ":p" + node.id + " -> node" + node.left.id + ":p" + node.left.id + ";";
            }
            if(node.right != null) {
                dot += getDotNodes(node.right);
                dot += "\n\tnode" + node.id + ":p" + node.id + " -> node" + node.right.id + ":p" + node.right.id + ";";
            }
        }
        return dot;
    }
    private String getStructN(Node node) {
        return "node" + node.id + "[label=<<table border=\"0\" cellspacing=\"0\" cellpadding=\"3\"><tr><td>V</td><td></td><td></td></tr><tr><td>1</td><td border=\"1\" style=\"rounded\" port=\"p" + node.id + "\">" + node.value + "</td><td>1</td></tr><tr><td></td><td>" + (node.i > 0 ? node.i : "") + "</td><td></td></tr></table>>];";
    }
    public String getDot() {
        return "digraph Tree {\n\tnode[shape = plaintext];" + getDotNodes(root) + "\n}";
    }
    Token popTokenStack() {
        return regex.expression.pop();
    }
    boolean isEmptyStack() {
        return regex.expression.isEmpty();
    }
}