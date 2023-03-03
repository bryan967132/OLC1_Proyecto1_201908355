package BackEnd;
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
    public Node(int id,String value,Type type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }
    public String toString() {
        return i + " │ " + value + " -> " + (nexts != null ? nexts.stream().map(Object::toString).collect(Collectors.joining(", ")):"-");
    }
}