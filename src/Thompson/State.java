package Thompson;
public class State {
    boolean accept;
    boolean enumered;
    int number;
    State next1;
    State next2;
    State exit;
    State last;
    State jmps;
    String id;
    String value;
    public State() {}
    public State(String id,String value) {
        this.id = id;
        this.value = value;
    }
    public String toString() {
        return id + " - " + value;
    }
}