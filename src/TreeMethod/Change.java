package TreeMethod;
import Colors.Type;
public class Change {
    int toState;
    String terminal;
    Type type;
    public Change(int toState,String terminal,Type type) {
        this.toState = toState;
        this.terminal = terminal;
        this.type = type;
    }
    public String toString() {
        return "S" + toState + ":" + terminal + "/" + type;
    }
}