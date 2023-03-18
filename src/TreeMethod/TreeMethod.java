package TreeMethod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import Colors.Token;
import Colors.Type;
import Controller.Set;
import Controller.Regex;
public class TreeMethod {
    ArrayList<Boolean> validator;
    int id;
    Map<String,Set> sets;
    Regex regex;
    Stack<Token> stack;
    Token token;
    Token token1;
    Token token2;
    Tree tree;
    TransitionTable transitionsTable;
    public TreeMethod(Regex regex) {
        this.regex = regex;
        tree = new Tree(regex);
    }
    public TreeMethod(Map<String,Set> sets) {
        this.sets = sets;
    }
    public void setRegex(int id,Regex regex) {
        this.id = id;
        this.regex = regex;
        tree = new Tree(regex);
    }
    public void build() {
        tree.build();
        tree.createIDNodes();
        tree.calculateFirsts();
        tree.calculateLasts();
        exportGraph(id + "_" + regex.id,tree.getDot(),"Tree");
    }
    public void buildNextsTable() {
        tree.calculateNexts();
        System.out.println("SIGUIENTES");
        NextsTable nextsTable = tree.getNexts();
        exportGraph(id + "_" + regex.id,nextsTable.getDot(regex.id),"Nexts");
    }
    public void buildTransitionsTable() {
        tree.calculateTransitions();
        System.out.println("TRANSICIONES");
        transitionsTable = tree.getTransitionsTable();
        System.out.println(transitionsTable);
    }
    public void buildAFD() {
        exportGraph(id + "_" + regex.id,tree.getDotAFD(),"AFD");
    }
    public String validateString(String string) {
        return "La Cadena: " + (string.contains("\n") ? string.replace("\n","\\n") : string) + (!validate(string) ? " no" : "") + " es Válida";
    }
    public boolean validate(String string) {
        validator = new ArrayList<>();
        Transition transition = null;
        Change chng;
        int state = 0;
        Set set;
        for(int i = 0; i < string.length(); i ++) {
            transition = transitionsTable.transitions.get(state);
            for(Map.Entry<String,Change> change : transition.changes.entrySet()) {
                chng = change.getValue();
                if(chng.type == Type.ID) {
                    set = sets.get(chng.terminal);
                    if(set.validate(string.charAt(i))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
                else if(chng.type == Type.STRING) {
                    if(chng.terminal.equals(String.valueOf(string.charAt(i)))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
                else if(chng.type == Type.SINGLEQUOTE) {
                    if(chng.terminal.equals(String.valueOf(string.charAt(i)))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
                else if(chng.type == Type.DOUBLEQUOTE) {
                    if(chng.terminal.equals(String.valueOf(string.charAt(i)))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
                else if(chng.type == Type.ENTER) {
                    if(chng.terminal.equals(String.valueOf(string.charAt(i)))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
            }
        }
        transition = transitionsTable.transitions.get(state);
        if(transition.accept && validator.size() == string.length()) {
            return true;
        }
        return false;
    }
    public void exportGraph(String id,String content,String file) {
        try {
            FileOutputStream outputStream = new FileOutputStream("Dot/" + file + "/" + id + ".dot");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(IOException e) {}
    }
}