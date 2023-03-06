package BackEnd;

import java.util.Objects;

public class Change {
    String terminal;
    int toState;
    public Change(int toState,String terminal) {
        this.terminal = terminal;
        this.toState = toState;
    }
    public String toString() {
        return "S" + toState + ":" + terminal;
    }
}