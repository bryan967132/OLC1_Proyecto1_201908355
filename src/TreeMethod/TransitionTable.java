package TreeMethod;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import Tree.Node;
public class TransitionTable {
    Map<Integer, Node> nexts = new TreeMap<>();
    ArrayList<Transition> transitions = new ArrayList<>();
    ArrayList<Transition> tmpTrnst = new ArrayList<>();
    ArrayList<Terminal> terminals = new ArrayList<>();
    public TransitionTable(ArrayList<Transition> transitions, Map<Integer, Node> nexts) {
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
            for(Terminal terminal : terminals) {
                newTrnst = new Transition(transitions.size(), terminal.value);
                for(int nxt : transition.nexts) {
                    next = nexts.get(nxt);
                    if(next.value.equals(terminal.value)) {
                        newTrnst.nexts.addAll(next.nexts);
                    }
                }
                position = existTransition(newTrnst);
                if(position == -1) {
                    if(newTrnst.nexts.size() > 0) {
                        transition.changes.put(terminal.value, new Change(transitions.size(), terminal.value, terminal.type));
                        transitions.add(newTrnst);
                    }
                }
                else {
                    transition.changes.put(terminal.value, new Change(position, terminal.value, terminal.type));
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
        Terminal newTerminal;
        for(Map.Entry<Integer, Node> next : nexts.entrySet()) {
            if(!next.getValue().value.equals("#")) {
                newTerminal = new Terminal(next.getValue().value, next.getValue().type1);
                if(!verifyTerminal(newTerminal)) {
                    terminals.add(newTerminal);
                }
            }
        }
    }
    private boolean verifyTerminal(Terminal newTerminal) {
        for(Terminal terminal : terminals) {
            if(terminal.value.equals(newTerminal.value)) {
                return true;
            }
        }
        return false;
    }
    public String getDot(String name) {
        String dot = "digraph Transitions {\n\tgraph[fontname=\"Arial\" labelloc=\"t\"];\n\tnode[shape=none fontname=\"Arial\"];\n\tlabel=\"Expresion Regular: " + name + "\";\n\ttable[label=<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" cellpadding=\"3\">";
        dot += "\n\t\t<tr>\n\t\t\t<td bgcolor=\"#009900\" width=\"100\" rowspan=\"2\"><font color=\"#FFFFFF\">Estados</font></td>\n\t\t\t<td bgcolor=\"#009900\" width=\"100\" colspan=\"" + terminals.size() + "\"><font color=\"#FFFFFF\">Terminales</font></td>\n\t\t</tr>";
        dot += "\n\t\t<tr>";
        String title;
        for(int i = 0; i < terminals.size(); i ++) {
            title = terminals.get(i).value;
            dot += "\n\t\t\t<td bgcolor=\"#009900\" width=\"100\"><font color=\"#FFFFFF\">" + (title.equals(" ") ? "\\\\s" : title) + "</font></td>";
        }
        dot += "\n\t\t</tr>";
        Map<String, Change> chngs;
        Change aux;
        for(int i = 0; i < transitions.size(); i ++) {
            chngs = transitions.get(i).changes;
            dot += "\n\t\t<tr>";
            dot += "\n\t\t\t<td align=\"left\">" + transitions.get(i).getState() + "</td>";
            for(int j = 0; j < terminals.size(); j ++) {
                aux = chngs.get(terminals.get(j).value);
                dot += "\n\t\t\t<td>" + (aux != null ? "S" + aux.toState  : "-") + "</td>";
            }
            dot += "\n\t\t</tr>";
        }
        dot += "\n\t</table>>];\n}";
        return dot;
    }
    public String toStringM() {
        String string = "";
        Map<String, Change> chngs;
        Change aux;
        String m_ij;
        for(int i = 0; i < transitions.size(); i ++) {
            chngs = transitions.get(i).changes;
            m_ij = "S" + i;
            string += m_ij + " ".repeat(5 - m_ij.length());
            for(int j = 0; j < terminals.size(); j ++) {
                aux = chngs.get(terminals.get(j).value);
                m_ij = aux != null ? "S" + aux.toState  : "-";
                string += m_ij + " ".repeat(4 - m_ij.length());
            }
            string += "\n";
        }
        return string;
    }
    public String toString() {
        String string = "";
        for(Transition transition : transitions) {
            string += transition + "\n";
        }
        return string;
    }
}