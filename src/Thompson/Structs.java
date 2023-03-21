package Thompson;
public class Structs {
    public Node OR(String id,Node frst,Node scnd) {
        Node node = new Node();
        node.id = id + "_start";
        node.value = "&epsilon;";
        if(frst.frst != null) {
            node.frst = frst;
            node.frst.last = node.frst.last.exit = new Node(id + "_exit",node.value);
        }
        else {
            node.frst = new Node(id + "_frst",node.value);
            frst.last = frst.exit = new Node(id + "_exit",node.value);
            node.frst.frst = frst;
        }
        if(scnd.frst != null) {
            node.scnd = scnd;
            node.scnd.last.exit = new Node(id + "_exit",node.value);
        }
        else {
            node.scnd = new Node(id + "_scnd",node.value);
            scnd.exit = new Node(id + "_exit",node.value);
            node.scnd.frst = scnd;
        }
        node.last = frst.last;
        return node;
    }
    public Node CONCAT(String id,Node frst,Node scnd) {
        Node node = new Node();
        node.id = id + "_start";
        if(frst.frst != null) {
            node = frst;
            if(scnd.frst != null) {
                node.last.frst = scnd.frst;
                if(scnd.scnd != null) {
                    node.last.scnd = scnd.scnd;
                }
                if(scnd.jmps != null) {
                    node.last.jmps = scnd.jmps;
                    node.last.jmps.value = "&epsilon;";
                }
                node.last = scnd.last;
            }
            else {
                node.last = node.last.exit = scnd;
            }
        }
        else {
            node.frst = frst;
            if(scnd.frst != null) {
                scnd.value = node.frst.value;
                node.frst = scnd;
                node.last = scnd.last;
            }
            else {
                node.last = frst.frst = scnd;
            }
        }
        return node;
    }
    public Node POSITIVE(String id,Node frst) {
        Node node = new Node();
        node.id = id + "_start";
        node.value = "&epsilon;";
        if(frst.frst != null) {
            node.frst = frst;
            frst.last.jmps = frst;
            node.last = frst.last.exit =  new Node(id + "_exit",node.value);
        }
        else {
            node.frst = new Node(id + "_frst",node.value);
            node.frst.frst = frst;
            node.frst.frst.jmps = node.frst;
            node.last = node.frst.frst.exit = new Node(id + "_exit",node.value);
        }
        return node;
    }
    public Node KLEENE(String id,Node frst) {
        Node node = new Node();
        node.id = id + "_start";
        node.value = "&epsilon;";
        if(frst.frst != null) {
            node.frst = frst;
            frst.last.jmps = frst;
            node.last = frst.last.exit = new Node(id + "_exit",node.value);
        }
        else {
            node.frst = new Node(id + "_frst",node.value);
            node.frst.frst = frst;
            node.frst.frst.jmps = node.frst;
            node.last = node.frst.frst.exit = new Node(id + "_exit",node.value);
        }
        node.jmps = node.last;
        return node;
    }
    public Node OPTIONAL(String id,Node frst) {
        Node node = new Node();
        node.id = id + "_start";
        node.value = "&epsilon;";
        if(frst.frst != null) {
            if(frst.frst.value.equals("&epsilon;") && frst.scnd == null && frst.jmps == null) {
                frst = frst.frst;
            }
            node.last = frst.last.exit = new Node(id + "_exit",node.value);
            node.frst = frst;
        }
        else {
            node.frst = new Node(id + "_frst",node.value);
            node.frst.frst = frst;
            node.last = node.frst.frst.exit = new Node(id + "_exit",node.value);
        }
        node.jmps = node.last;
        return node;
    }
    public String getDot(Node node) {
        String dot = "";
        if(node.accept) {
            dot += "\n\tN_" + node.id + "[label = \"\" peripheries = 2];";
        }
        else {
            dot += "\n\tN_" + node.id + "[label = \"\"];";
        }
        if(node.frst != null) {
            dot += getDot(node.frst);
            dot += "\n\tN_" + node.id + " -> N_" + node.frst.id + "[label = \"" + getValue(node.frst.value) + "\"];";
        }
        if(node.scnd != null) {
            dot += getDot(node.scnd);
            dot += "\n\tN_" + node.id + " -> N_" + node.scnd.id + "[label = \"" + getValue(node.scnd.value) + "\"];";
        }
        if(node.exit != null) {
            dot += getDot(node.exit);
            dot += "\n\tN_" + node.id + " -> N_" + node.exit.id + "[label = \"" + getValue(node.exit.value) + "\"];";
        }
        if(node.jmps != null) {
            dot += "\n\tN_" + node.id + " -> N_" + node.jmps.id + "[label = \"" + getValue(node.jmps.value) + "\"];";
        }
        return dot;
    }
    private String getValue(String value) {
        return (value == null ? "&epsilon;" : (value.equals("\" \"") ? "\\\\s" : (value.equals("\n") ? "\\\\n" : value)));
    }
}