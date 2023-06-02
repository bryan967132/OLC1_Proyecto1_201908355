package Tree;
import java.util.ArrayList;
import java.util.stream.Collectors;
import Colors.Type;
public class Node {
    public ArrayList<Integer> firsts = new ArrayList<>();
    public ArrayList<Integer> lasts = new ArrayList<>();
    public ArrayList<Integer> nexts = new ArrayList<>();
    public boolean anulable;
    public int i;
    public int id;
    public Node left;
    public Node right;
    public String value;
    public Type type;
    public Type type1;
    public Node(int id,String value,Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }
    public Node(int id,String value,Type type,Type type1) {
        this.id = id;
        this.value = type1 == Type.STRING ? value.substring(1,value.length() - 1) : value;
        this.type = type;
        this.type1 = type1;
    }
    private String terminals(String terminal) {
        return (terminal.equals(" ") ? "\\s" : (terminal.equals("\n") ? "\\n" : terminal));
    }
    public String getDot() {
        return "\n\t\t<tr>\n\t\t\t<td>" + i + "</td>\n\t\t\t<td>" + terminals(value) + "</td>\n\t\t\t<td>" + (nexts != null ? nexts.stream().map(Object::toString).collect(Collectors.joining(", ")):"-") + "</td>\n\t\t</tr>";
    }
    public String toString() {
        return toString(left);
    }
    private String toString(Node node) {
        String dot = "";
        if(node != null) {
            if((node.type == Type.KLEENE || node.type == Type.POSITIVE || node.type == Type.OPTIONAL) && (node.left != null && node.left.type1 != Type.ID && node.left.type1 != Type.STRING)) {
                dot += "(";
            }
            dot += toString(node.left);
            if((node.type == Type.KLEENE || node.type == Type.POSITIVE || node.type == Type.OPTIONAL) && (node.left != null && node.left.type1 != Type.ID && node.left.type1 != Type.STRING)) {
                dot += ")";
            }
            if(node.type != Type.CONCAT) {
                if(node.type1 == Type.STRING) {
                    dot += "\"";
                }
                else if(node.type1 == Type.ID) {
                    dot += "{";
                }
                dot += node.value;
                if(node.type1 == Type.STRING) {
                    dot += "\"";
                }
                else if(node.type1 == Type.ID) {
                    dot += "}";
                }
            }
            dot += toString(node.right);
        }
        return dot;
    }
    public String transition() {
        return i + " │ " + terminals(value) + " -> " + (nexts != null ? nexts.stream().map(Object::toString).collect(Collectors.joining(", ")):"-");
    }
    public String getGraphBasic() {
        return "digraph Tree {" + getNodes(this) + "\n}";
    }
    private String getNodes(Node node) {
        String dot = "";
        if(node != null) {
            dot += "\n\tnode_" + node.id + "[label = \"" + node.value + "\\n" + (node.anulable) + (node.i > 0 ? "\\n" + node.i : "") + "\"];";
            if(node.left != null) {
                dot += getNodes(node.left);
                dot += "\n\tnode_" + node.id + " -> node_" + node.left.id + ";";
            }
            if(node.right != null) {
                dot += getNodes(node.right);
                dot += "\n\tnode_" + node.id + " -> node_" + node.right.id + ";";
            }
        }
        return dot;
    }
}