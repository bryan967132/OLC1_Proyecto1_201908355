package BackEnd;
import java.util.ArrayList;
public class Node {
    ArrayList<Integer> firsts;
    ArrayList<Integer> nexts;
    boolean anulable;
    int i;
    int id;
    Node left;
    Node right;
    String value;
    public Node(int id,String value) {
        this.id = id;
        this.value = value;
        this.firsts = new ArrayList<>();
        this.nexts = new ArrayList<>();
    }
}