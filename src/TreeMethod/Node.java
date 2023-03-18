package TreeMethod;
import java.util.ArrayList;
import java.util.stream.Collectors;
import Colors.Type;
public class Node {
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
    private String terminals(String terminal) {
        return (terminal.equals(" ") ? "\\s" : (terminal.equals("\n") ? "\\n" : terminal));
    }
    public String getDot() {
        return "<tr><td width=\"20\">" + i + "</td><td width=\"100\">" + terminals(value) + "</td><td width=\"100\">" + (nexts != null ? nexts.stream().map(Object::toString).collect(Collectors.joining(", ")):"-") + "</td></tr>";
    }
    public String toString() {
        return i + " │ " + terminals(value) + " -> " + (nexts != null ? nexts.stream().map(Object::toString).collect(Collectors.joining(", ")):"-");
    }
}