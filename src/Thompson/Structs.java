package Thompson;

import Tree.Node;

public class Structs {
    int number = 0;
    public State OR(String id,State frst,State scnd) {
        State state = new State(id + "_start","&epsilon;");
        State exit = new State(id + "_exit",state.value);
        //or1
        state.next1 = frst;
        state.last = state.next1.last.exit = exit;
        //or2
        state.next2 = scnd;
        state.next2.last.next1 = exit;
        return state;
    }
    public State CONCAT(String id,State frst,State scnd) {
        State state = new State(id + "_start","&epsilon;");
        //and1
        state = frst;
        //and2
        state.last.next1 = scnd.next1;
        state.last.next2 = scnd.next2;
        if(scnd.jmps != null) {
            state.last.jmps = scnd.jmps;
        }
        state.last = scnd.last;
        return state;
    }
    public State POSITIVE(String id,State frst) {
        State state = new State(id + "_start","&epsilon;");
        state.next1 = frst;
        state.next1.last.jmps = state.next1;
        state.last = state.next1.last.next1 = new State(id + "_exit",state.value);
        return state;
    }
    public State KLEENE(String id,State frst) {
        State state = new State(id + "_start","&epsilon;");
        state.next1 = frst;
        state.next1.last.jmps = state.next1;
        state.jmps = state.last = state.next1.last.next1 = new State(id + "_exit",state.value);
        return state;
    }
    public State OPTIONAL(String id,State frst) {
        State state = new State(id + "_start","&epsilon;");
        state.next1 = frst;
        state.jmps = state.last = state.next1.last.next1 = new State(id + "_exit",state.value);
        return state;
    }
    public State SIMPLE(String id,Node frst) {
        State start = new State(id,"&epsilon;");
        start.last = start.next1 = new State(id + "_next1",frst.value);
        return start;
    }
    public State EPSILON(String id) {
        State start = new State(id,"&epsilon;");
        start.last = start.next1 = new State(id + "_next1",start.value);
        return start;
    }
    public String getDot(State state) {
        asignIDNodes(state);
        return getDotGraph(state);
    }
    private void asignIDNodes(State node) {
        if(node != null && !node.enumered) {
            node.number = number;
            node.enumered = true;
            if(node.next1 != null) {
                number ++;
                asignIDNodes(node.next1);
            }
            if(node.next2 != null) {
                number ++;
                asignIDNodes(node.next2);
            }
        }
    }
    private String getDotGraph(State state) {
        String dot = "";
        if(state.accept) {
            dot += "\n\tN_" + state.id + "[label = \"" + state.number+ "\" peripheries = 2];";
        }
        else {
            dot += "\n\tN_" + state.id + "[label = \"" + state.number+ "\"];";
        }
        if(state.next1 != null) {
            dot += getDot(state.next1);
            dot += "\n\tN_" + state.id + " -> N_" + state.next1.id + "[label = \"" + getValue(state.next1.value) + "\"];";
        }
        if(state.next2 != null) {
            dot += getDot(state.next2);
            dot += "\n\tN_" + state.id + " -> N_" + state.next2.id + "[label = \"" + getValue(state.next2.value) + "\"];";
        }
        if(state.exit != null) {
            dot += "\n\tN_" + state.id + " -> N_" + state.exit.id + "[label = \"" + getValue(state.exit.value) + "\"];";
        }
        if(state.jmps != null) {
            dot += "\n\tN_" + state.id + " -> N_" + state.jmps.id + "[label = \"" + getValue(state.jmps.value) + "\"];";
        }
        return dot;
    }
    private String getValue(String value) {
        return (value == null ? "&epsilon;" : (value.equals(" ") ? "\\\\s" : (value.equals("\\n") ? "\\\\n" : value)));
    }
}