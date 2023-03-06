package BackEnd;
import java.util.ArrayList;
import java.util.HashSet;
public class TransitionTable {
    ArrayList<Node> nexts = new ArrayList<>();
    ArrayList<Transition> transitions = new ArrayList<>();
    ArrayList<Transition> tmpTrnst = new ArrayList<>();
    HashSet<String> terminals = new HashSet<>();
    int nSt;
    public TransitionTable(ArrayList<Transition> transitions,ArrayList<Node> nexts,int nSt) {
        this.transitions = transitions;
        this.nexts = nexts;
        this.nSt = nSt;
    }
    public void build() {
        addTerminals();
        build(0);
        //changes();
    }
    private void build(int i) {
        if(i < nSt) {
            int position;
            Node next;
            Transition newTrnst;
            Transition transition = transitions.get(i);
            for(String terminal : terminals) {
                newTrnst = new Transition(transitions.size(),terminal);
                for(int nxt : transition.nexts) {
                    next = nexts.get(nxt - 1);
                    if(next.value.equals(terminal)) {
                        newTrnst.nexts.addAll(next.nexts);
                    }
                }
                position = existTransition(newTrnst);
                if(position == -1) {
                    if(newTrnst.nexts.size() > 0) {
                        transition.changes.put(terminal,transitions.size());
                        transitions.add(newTrnst);
                    }
                }
                else {
                    transition.changes.put(terminal,position);
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
                terminals.add(next.value);
            }
        }
    }
    public String toString() {
        String cadena = "";
        for(Transition transition : transitions) {
            cadena += transition + "\n";
        }
        return cadena;
    }
}
/*private void changes() {
    String terminal;
    int toState;
    for(Transition transition : transitions) {
        for(Integer next : transition.nexts) {
            terminal = getTerminal(next);
            if(!terminal.equals("#")) {
                toState = getToState(terminal);
                if(toState != -1) {
                    transition.changes.put(terminal,toState);
                }
            }
        }
    }
}
private String getTerminal(int next) {
    for(Node node : nexts) {
        if(next == node.i) {
            return node.value;
        }
    }
    return null;
}
private int getToState(String terminal) {
    for(Transition transition : transitions) {
        if(transition.value == terminal) {
            return transition.state;
        }
    }
    return -1;
}*/