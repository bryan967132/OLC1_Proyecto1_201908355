package BackEnd;
import java.util.HashSet;
import java.util.stream.Collectors;
public class Transition {
    HashSet<Integer> nexts = new HashSet<>();
    int state;
    String value;
    boolean accept;
    public Transition(int state,String value) {
        this.state = state;
        this.value = value;
    }
    public String toString() {
        return "S" + state + " {" + nexts.stream().map(Object::toString).collect(Collectors.joining(", ")) + "}" + (accept ? " *" : "");
    }
}