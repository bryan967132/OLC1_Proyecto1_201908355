package Thompson;
public class Node {
    boolean accept;
    Node exit;
    Node frst;
    Node scnd;
    Node last;
    Node jmps;
    String id;
    String value;
    public Node() {}
    public Node(String id,String value) {
        this.id = id;
        this.value = value;
    }
    public String toString() {
        return id + " - " + value;
    }
}