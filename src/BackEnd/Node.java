package BackEnd;
import java.util.ArrayList;
public class Node {
    ArrayList<Integer> first;
    ArrayList<Integer> next;
    Boolean anulable;
    int i;
    int id;
    Node left;
    Node right;
    String value;
    public Node(int id,String value) {
        this.id = id;
        this.value = value;
    }
}