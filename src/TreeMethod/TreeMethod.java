package TreeMethod;
import java.io.File;
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
    boolean errorSet = false;
    int id;
    Map<String,Set> sets;
    NextsTable nextsTable;
    Regex regex;
    Stack<Token> stack;
    Set setNotFound;
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
        exportDot("tree_" + id + "_" + regex.id,tree.getDot(regex.id),"Tree");
        buildPNG("Tree","ARBOLES_201908355","tree_" + id + "_" + regex.id);
    }
    public void buildNextsTable() {
        tree.calculateNexts();
        nextsTable = tree.getNexts();
        exportDot("nexts_" + id + "_" + regex.id,nextsTable.getDot(regex.id),"Nexts");
        buildPNG("Nexts","SIGUIENTES_201908355","nexts_" + id + "_" + regex.id);
    }
    public void buildTransitionsTable() {
        tree.calculateTransitions();
        transitionsTable = tree.getTransitionsTable();
        exportDot("transitions_" + id + "_" + regex.id,transitionsTable.getDot(regex.id),"Transitions");
        buildPNG("Transitions","TRANSICIONES_201908355","transitions_" + id + "_" + regex.id);
    }
    public void buildAFD() {
        exportDot("afd_" + id + "_" + regex.id,tree.getDotAFD(regex.id),"AFD");
        buildPNG("AFD","AFD_201908355","afd_" + id + "_" + regex.id);
    }
    public void printMethod() {
        System.out.println("SIGUIENTES");
        System.out.println(nextsTable);
        System.out.println("TRANSICIONES");
        System.out.println(transitionsTable);
        System.out.println(transitionsTable.toStringM());
    }
    public String validateString(String string) {
        errorSet = false;
        setNotFound = null;
        boolean isValid = isValid(string);
        if(!isValid && errorSet && setNotFound != null) {
            return "No se declaró el conjunto \"" + setNotFound.id + "\"";
        }
        return "La Expresión: \"" + (string.contains("\n") ? string.replace("\n","\\n") : string) + (!isValid ? "\" no" : "\"") + " es Válida con la Expresión Regular \"" + regex.id + "\".";
    }
    private boolean isValid(String string) {
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
                    if(set == null) {
                        errorSet = true;
                        setNotFound = new Set();
                        setNotFound.id = chng.terminal;
                        return false;
                    }
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
                    if(chng.terminal.replace("\\","").equals(String.valueOf(string.charAt(i)))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
                else if(chng.type == Type.DOUBLEQUOTE) {
                    if(chng.terminal.replace("\\","").equals(String.valueOf(string.charAt(i)))) {
                        validator.add(true);
                        state = chng.toState;
                    }
                }
                else if(chng.type == Type.ENTER) {
                    if(chng.terminal.replace("\\","").equals(String.valueOf(string.charAt(i)))) {
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
    public void exportDot(String id,String content,String file) {
        try {
            FileOutputStream outputStream = new FileOutputStream("Dot/" + file + "/" + id + ".dot");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(IOException e) {}
    }
    private void buildPNG(String typeDot,String typePng,String name) {
        try {
            File file = new File(typePng);
            if(!file.exists()) {
                file.mkdirs();
            }
            String graphviz_path = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String dot_path = "Dot/" + typeDot + "/" + name + ".dot";
            String png_path =  typePng + "/" + name + ".png" ;
            ProcessBuilder pBuilder = new ProcessBuilder(graphviz_path, "-Tpng", "-o", png_path, dot_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}