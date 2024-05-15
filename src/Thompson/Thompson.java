package Thompson;
import Tree.Node;
public class Thompson {
    private int id;
    private State start;
    private Structs structs;
    private Node tree;
    public Thompson() {}
    public Thompson(Node tree) {
        this.id = 0;
        this.structs = new Structs();
        this.tree = tree;
    }
    public void build() {
        start = build1(tree.left);
        start.last.accept = true;
    }
    public State build1(Node node) {
        id ++;
        switch(node.type) {
            case OR:
                return structs.OR(String.valueOf(id), build1(node.left), build1(node.right));               
            case CONCAT:
                return structs.CONCAT(String.valueOf(id), build1(node.left), build1(node.right));
            case POSITIVE:
                return structs.CONCAT(String.valueOf(id), build1(node.left), structs.KLEENE(String.valueOf(id) + "_c", build1(node.left)));
            case KLEENE:
                return structs.KLEENE(String.valueOf(id), build1(node.left));
            case OPTIONAL:
                return structs.OR(String.valueOf(id), build1(node.left), structs.EPSILON(String.valueOf(id) + "_epsilon"));
            default:
                return structs.SIMPLE(String.valueOf(id), node);
        }
    }
    public State build2(Node node) {
        id ++;
        switch(node.type) {
            case OR:
                return structs.OR(String.valueOf(id), build2(node.left), build2(node.right));               
            case CONCAT:
                return structs.CONCAT(String.valueOf(id), build2(node.left), build2(node.right));
            case POSITIVE:
                return structs.POSITIVE(String.valueOf(id), build2(node.left));
            case KLEENE:
                return structs.KLEENE(String.valueOf(id), build2(node.left));
            case OPTIONAL:
                return structs.OPTIONAL(String.valueOf(id), build2(node.left));
            default:
                return structs.SIMPLE(String.valueOf(id), node);
        }
    }
    public String getDot(String name) {
        return "digraph AFN {\n\tgraph[fontname=\"Arial\" labelloc=t];\n\tnode[shape=circle fontname=\"Arial\"];\n\tedge[fontname=\"Arial\"];\n\trankdir = LR;\n\tlabel=\"Expresión Regular: " + name + "\";" + structs.getDot(start) + "\n}";
    }
}