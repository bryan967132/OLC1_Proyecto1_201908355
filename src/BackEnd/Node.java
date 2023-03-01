package BackEnd;
import java.util.ArrayList;
public class Node {
    int id;
    Node center;
    Node left;
    Node right;
    String value;
    ArrayList<Integer> first;
    
    public Node(int id,String value) {
        this.id = id;
        this.value = value;
        this.center = null;
        this.left = null;
        this.right = null;
    }
}