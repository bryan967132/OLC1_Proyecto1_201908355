package TreeMethod;
import java.util.ArrayList;

import Colors.Type;
public class NextsTable {
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
    public String getDot(String name) {
        String dot = "digraph Nexts {\nnode[shape = none fontname=\"Arial\"];\nlabel=\"Expresion Regular: " + name + "\";\nlabelloc=\"t\";\ntable[label=<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" cellpadding=\"0\">\n<tr><td colspan=\"2\" bgcolor=\"#009900\"><font color=\"#FFFFFF\">Hoja</font></td><td  bgcolor=\"#009900\"><font color=\"#FFFFFF\">Siguientes</font></td></tr>";
        for(Node node : leafs) {
            dot += "\n" + node.getDot();
        }
        dot += "\n</table>>];\n}";
        return dot;
    }
    public String toString() {
        String string = "";
        for(Node node : leafs) {
            string += node + "\n";
        }
        return string;
    }
}