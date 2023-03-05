package BackEnd;
import java.util.ArrayList;
public class TransitionTable {
    ArrayList<String> terminals = new ArrayList<>();
    ArrayList<Transition> transitions = new ArrayList<>();
    public TransitionTable(ArrayList<Transition> transitions) {
        this.transitions = transitions;
    }
    public String toString() {
        String cadena = "";
        for(Transition transition : transitions) {
            cadena += transition + "\n";
        }
        return cadena;
    }
}