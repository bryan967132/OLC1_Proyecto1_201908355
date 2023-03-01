package BackEnd;
import java.util.Stack;
import java.util.stream.Collectors;
import Colors.Token;
import Colors.Tokens;
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
                    node = new Node(id,token.lexeme,token.type);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case STRING:
                    node = new Node(id,token.lexeme,token.type);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case END:
                    node = new Node(id,token.lexeme,token.type);
                    node.anulable = false;
                    stack.push(node);
                    id ++;
                    break;
                case OR:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.right = stack.pop();
                    node.anulable = node.left.anulable || node.right.anulable;
                    stack.push(node);
                    id ++;
                    break;
                case CONCAT:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.right = stack.pop();
                    node.anulable = node.left.anulable && node.right.anulable;
                    stack.push(node);
                    id ++;
                    break;
                case POSITIVE:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
                    node.anulable = node.left.anulable;
                    stack.push(node);
                    id ++;
                    break;
                case KLEENE:
                    node = new Node(id,token.lexeme,token.type);
                    node.left = stack.pop();
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
    public void createIDNodes() {
        createIDNodes(root);
    }
    private void createIDNodes(Node node) {
        if(node != null) {
            if(node.left == null && node.right == null) {
                node.i = i;
                i ++;
                return;
            }
            if(node.left != null) {
                createIDNodes(node.left);
            }
            if(node.right != null) {
                createIDNodes(node.right);
            }
        }
    }
    public void calculateFirsts() {
        calculateFirsts(root);
    }
    private void calculateFirsts(Node node) {
        if(node != null) {
            if(node.left == null && node.right == null) {
                node.firsts.add(node.i);
                return;
            }
            calculateFirsts(node.left);
            calculateFirsts(node.right);
            node.firsts.addAll(node.left.firsts);
            if(node.type == Tokens.OR) {
                node.firsts.addAll(node.right.firsts);
            }
            else if(node.type == Tokens.CONCAT) {
                if(node.left.anulable) {
                    node.firsts.addAll(node.right.firsts);
                }
            }
        }
    }
    public void calculateLasts() {
        calculateLasts(root);
    }
    private void calculateLasts(Node node) {
        if(node != null) {
            if(node.left == null && node.right == null) {
                node.lasts.add(node.i);
                return;
            }
            calculateLasts(node.left);
            calculateLasts(node.right);
            if(node.type == Tokens.OR || node.type == Tokens.POSITIVE || node.type == Tokens.KLEENE) {
                node.lasts.addAll(node.left.lasts);
                if(node.type == Tokens.OR) {
                    node.lasts.addAll(node.right.lasts);
                }
            }
            else if(node.type == Tokens.CONCAT) {
                if(node.right.anulable) {
                    node.lasts.addAll(node.left.lasts);
                }
                node.lasts.addAll(node.right.lasts);
            }
        }
    }
    public String getDot() {
        return "digraph Tree {\n\tnode[shape = plaintext fontname=\"Arial\"];\n\tedge[dir = none];" + getDotNodes(root,Align.CENTER) + "\n}";
    }
    private String getDotNodes(Node node,Align align) {
        String dot = "";
        if(node != null) {
            dot += "\n\t" + getStructN(node,align);
            if(node.left != null) {
                dot += getDotNodes(node.left,Align.LEFT);
                dot += "\n\tnode" + node.id + ":p" + node.id + " -> node" + node.left.id + ":p" + node.left.id + ";";
            }
            if(node.right != null) {
                dot += getDotNodes(node.right,Align.RIGHT);
                dot += "\n\tnode" + node.id + ":p" + node.id + " -> node" + node.right.id + ":p" + node.right.id + ";";
            }
        }
        return dot;
    }
    private String getStructN(Node node,Align align) {
        return "node" + node.id + "[label=<<table border=\"0\" cellspacing=\"0\" cellpadding=\"3\"><tr>" + getAnulable(node,align) + "</tr><tr><td><font color=\"#CC6600\">" + getFirsts(node) + "</font></td><td border=\"1\" style=\"rounded\" port=\"p" + node.id + "\" width=\"25\">" + node.value + "</td><td><font color=\"#009900\">" + getLasts(node) + "</font></td></tr><tr><td></td><td>" + (node.i > 0 ? "<font color=\"#0C7CBA\">" + node.i + "</font>" : "") + "</td><td></td></tr></table>>];";
    }
    private String getAnulable(Node node,Align align) {
        return "<td>" + (align == Align.LEFT ? (node.anulable ? "<font color=\"#CC0000\">A</font>" : "<font color=\"#CC0000\">N</font>") : "") + "</td><td>" + (align == Align.CENTER ? (node.anulable ? "<font color=\"#CC0000\">A</font>" : "<font color=\"#CC0000\">N</font>") : "") + "</td><td>" + (align == Align.RIGHT ? (node.anulable ? "<font color=\"#CC0000\">A</font>" : "<font color=\"#CC0000\">N</font>") : "") + "</td>";
    }
    private String getFirsts(Node node) {
        return node.firsts.size() > 0 ? String.join(",",node.firsts.stream().map(Object::toString).collect(Collectors.joining(","))) :  "";
    }
    private String getLasts(Node node) {
        return node.lasts.size() > 0 ? String.join(",",node.lasts.stream().map(Object::toString).collect(Collectors.joining(","))) :  "";
    }
    Token popTokenStack() {
        return regex.expression.pop();
    }
    boolean isEmptyStack() {
        return regex.expression.isEmpty();
    }
    enum Align {
        LEFT,
        CENTER,
        RIGHT
    }
}