package TreeMethod;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import Colors.Token;
import Colors.Type;
import Controller.Regex;
public class Tree {
    private ArrayList<Transition> transitions;
    private int i;
    private int id;
    private NextsTable nexts;
    private Node node;
    private Node root;
    private Stack<Token> expression;
    private Stack<Node> stack;
    private Token token;
    private TransitionTable table;
    public Tree(Regex regex) {
        this.i = 1;
        this.id = 0;
        this.expression = clone(regex.expression);
        this.stack = new Stack<>();
        this.transitions = new ArrayList<>();
    }
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
    public void calculateNexts() {
        nexts = new NextsTable(root);
    }
    public NextsTable getNexts() {
        return nexts;
    }
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
    public TransitionTable getTransitionsTable() {
        return table;
    }
    public String getDotAFD(String name) {
        return "digraph AFD {\n\tgraph[fontname=\"Arial\" labelloc=\"t\"];\n\tnode[shape=circle fontname=\"Arial\"];\n\tedge[fontname=\"Arial\"];\n\trankdir = LR;\n\tlabel=\"Expresion Regular: " + name + "\";" + getStates() + "\n}";
    }
    private String getStates() {
        String nodes = "";
        for(Transition transition : table.transitions) {
            for(Map.Entry<String,Change> entry : transition.changes.entrySet()) {
                Change chng = entry.getValue();
                nodes += "\n\tS" + transition.state + " -> S" + chng.toState + "[label = \"" + terminals(chng.terminal) + "\"];";
            }
            if(transition.accept) {
                nodes += "\n\tS" + transition.state + "[peripheries = 2];";
            }
        }
        return nodes;
    }
    public String getDot(String name) {
        return "digraph Tree {\n\tgraph[fontname=\"Arial\" labelloc=t];\n\tnode[shape = plaintext fontname=\"Arial\"];\n\tedge[dir = none];\n\t" + description(name) + getDotNodes(root,Align.CENTER) + "\n}";
    }
    private String description(String name) {
        return "label=<EXPRESIÓN REGULAR: " + name + "<br/><font color=\"#0C7CBA\">IDENTIFICADORES</font><br align=\"left\"/><font color=\"#CC0000\">ANULABLES</font><br align=\"left\"/><font color=\"#CC6600\">PRIMEROS</font><br align=\"left\"/><font color=\"#009900\">ÚLTIMOS</font><br align=\"left\"/>>;";
    }
    private String terminals(String terminal) {
        return (terminal.equals(" ") ? "&#92;&#92;s" : (terminal.equals("\n") ? "&#92;&#92;n" : terminal));
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
        return "node" + node.id + "[label=<<table border=\"0\" cellspacing=\"0\" cellpadding=\"3\"><tr>" + getAnulable(node,align) + "</tr><tr><td><font color=\"#CC6600\">" + getFirsts(node) + "</font></td><td border=\"1\" style=\"rounded\" port=\"p" + node.id + "\" width=\"25\">" + terminals(node.value) + "</td><td><font color=\"#009900\">" + getLasts(node) + "</font></td></tr><tr><td></td><td>" + (node.i > 0 ? "<font color=\"#0C7CBA\">" + node.i + "</font>" : "") + "</td><td></td></tr></table>>];";
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
    private Token popTokenStack() {
        return expression.pop();
    }
    private boolean isEmptyStack() {
        return expression.isEmpty();
    }
    private Stack<Token> clone(Stack<Token> expression) {
        Stack<Token> expressionClone = new Stack<>();
        expressionClone.push(new Token(".",Type.CONCAT));
        for(Token token : expression) {
            expressionClone.push(token);
        }
        expressionClone.push(new Token("#",Type.END));
        return expressionClone;
    }
    private enum Align {
        LEFT,
        CENTER,
        RIGHT
    }
}