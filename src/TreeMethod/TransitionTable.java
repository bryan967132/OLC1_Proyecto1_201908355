package TreeMethod;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import Colors.Type;
public class TransitionTable {
    ArrayList<Node> nexts = new ArrayList<>();
    ArrayList<Transition> transitions = new ArrayList<>();
    ArrayList<Transition> tmpTrnst = new ArrayList<>();
    Map<String,Type> terminals = new TreeMap<>();
    public TransitionTable(ArrayList<Transition> transitions,ArrayList<Node> nexts) {
        this.transitions = transitions;
        this.nexts = nexts;
    }
    public void build() {
        addTerminals();
        build(0);
    }
    private void build(int i) {
        if(i < transitions.size()) {
            int position;
            Node next;
            Transition newTrnst;
            Transition transition = transitions.get(i);
            for(Map.Entry<String,Type> terminal : terminals.entrySet()) {
                newTrnst = new Transition(transitions.size(),terminal.getKey());
                for(int nxt : transition.nexts) {
                    next = nexts.get(nxt - 1);
                    if(next.value.equals(terminal.getKey())) {
                        newTrnst.nexts.addAll(next.nexts);
                    }
                }
                position = existTransition(newTrnst);
                if(position == -1) {
                    if(newTrnst.nexts.size() > 0) {
                        transition.changes.put(terminal.getKey(),new Change(transitions.size(),terminal.getKey(),terminal.getValue()));
                        transitions.add(newTrnst);
                    }
                }
                else {
                    transition.changes.put(terminal.getKey(),new Change(position,terminal.getKey(),terminal.getValue()));
                }
            }
            build(i + 1);
        }
    }
    private int existTransition(Transition transition) {
        for(int i = 0; i < transitions.size(); i ++) {
            if(transitions.get(i).nexts.equals(transition.nexts)) {
                return i;
            }
        }
        return -1;
    }
    private void addTerminals() {
        for(Node next : nexts) {
            if(!next.value.equals("#")) {
                terminals.put(next.value,next.type1);
            }
        }
    }
    public String toString() {
        String string = "";
        for(Transition transition : transitions) {
            string += transition + "\n";
        }
        return string;
    }
}