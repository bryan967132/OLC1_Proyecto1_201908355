package BackEnd;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
public class Transition {
    boolean accept;
    HashSet<Integer> nexts = new HashSet<>();
    int state;
    Map<String,Integer> changes = new TreeMap<>();
    String value;
    public Transition(int state,String value) {
        this.state = state;
        this.value = value;
    }
    public Transition(int state,String value,HashSet<Integer> nexts) {
        this.state = state;
        this.nexts = nexts;
        this.value = value;
    }
    public String toString() {
        ArrayList<Change> chngs = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : changes.entrySet()) {
            chngs.add(new Change(entry.getValue(),entry.getKey()));
        }
        String text = "S" + state + " {" + nexts.stream().map(Object::toString).collect(Collectors.joining(", ")) + "}" + (accept ? " *" : "");
        return text + " ".repeat(25 - text.length()) + " -> " + chngs.stream().map(Object::toString).collect(Collectors.joining(" - "));
    }
}