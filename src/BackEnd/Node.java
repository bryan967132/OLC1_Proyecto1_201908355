package BackEnd;
import java.util.ArrayList;
import java.util.stream.Collectors;
import Colors.Type;
public class Node {
    ArrayList<Integer> firsts;
    ArrayList<Integer> lasts;
    ArrayList<Integer> nexts;
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
        this.firsts = new ArrayList<>();
        this.lasts = new ArrayList<>();
        this.nexts = new ArrayList<>();
    }
    public String toString() {
        return i + " │ " + value + " -> " + (nexts != null ? nexts.stream().map(Object::toString).collect(Collectors.joining(", ")):"-");
    }
}