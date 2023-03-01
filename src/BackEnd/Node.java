package BackEnd;
import java.util.ArrayList;
import Colors.Tokens;
public class Node {
    ArrayList<Integer> firsts;
    ArrayList<Integer> lasts;
    ArrayList<Integer> nexts;
    boolean anulable;
    int i;
    int id;
    Node left;
    Node right;
    String value;
    Tokens type;
    public Node(int id,String value,Tokens type) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.firsts = new ArrayList<>();
        this.lasts = new ArrayList<>();
        this.nexts = new ArrayList<>();
    }
}