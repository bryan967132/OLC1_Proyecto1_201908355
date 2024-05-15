package TreeMethod;
import java.util.Map;
import java.util.TreeMap;
import Colors.Type;
import Tree.Node;
public class NextsTable {
    Map<Integer, Node> leafs;
    Node root;
    public NextsTable(Node root) {
        this.root = root;
        this.leafs = new TreeMap<>();
    }
    public void calculateNexts() {
        fillLeafs(root);
        calculateNexts(root);
    }
    private void calculateNexts(Node node) {
        if(node != null) {
            if(node.type == Type.LEAF) return;
            if(node.type == Type.CONCAT) {
                for(int last : node.left.lasts) {
                    leafs.get(last).nexts.addAll(node.right.firsts);
                    leafs.get(last).nexts.sort(null);
                }
            }
            else if(node.type == Type.KLEENE || node.type == Type.POSITIVE) {
                for(int last : node.left.lasts) {
                    leafs.get(last).nexts.addAll(node.left.firsts);
                    leafs.get(last).nexts.sort(null);
                }
            }
            calculateNexts(node.left);
            calculateNexts(node.right);
        }
    }
    private void fillLeafs(Node node) {
        if(node != null) {
            if(node.type == Type.LEAF) {
                leafs.put(node.i, node);
                return;
            }
            fillLeafs(node.left);
            fillLeafs(node.right);
        }
    }
    public String getDot(String name) {
        String dot = "digraph Nexts {\n\tgraph[fontname=\"Arial\" labelloc=\"t\"];\n\tnode[shape=none fontname=\"Arial\"];\n\tlabel=\"Expresion Regular: " + name + "\";\n\ttable[label=<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" cellpadding=\"3\">\n\t\t<tr>\n\t\t\t<td bgcolor=\"#009900\" width=\"30\"><font color=\"#FFFFFF\">No</font></td>\n\t\t\t<td bgcolor=\"#009900\" width=\"100\"><font color=\"#FFFFFF\">Hoja</font></td>\n\t\t\t<td bgcolor=\"#009900\" width=\"100\"><font color=\"#FFFFFF\">Siguientes</font></td>\n\t\t</tr>";
        for(Map.Entry<Integer, Node> node : leafs.entrySet()) {
            dot += node.getValue().getDot();
        }
        dot += "\n\t</table>>];\n}";
        return dot;
    }
    public String toString() {
        String string = "";
        for(Map.Entry<Integer, Node> node : leafs.entrySet()) {
            string += node.getValue() + "\n";
        }
        return string;
    }
}