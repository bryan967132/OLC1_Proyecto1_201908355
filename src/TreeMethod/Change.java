package TreeMethod;
import Colors.Type;
public class Change {
    int toState;
    String terminal;
    Type type;
    public Change(int toState, String terminal, Type type) {
        this.toState = toState;
        this.terminal = terminal;
        this.type = type;
    }
    private String terminals(String terminal) {
        return (terminal.equals(" ") ? "\\s" : (terminal.equals("\n") ? "\\n" : terminal));
    }
    public String toString() {
        return "S" + toState + ":" + terminals(terminal);
    }
}