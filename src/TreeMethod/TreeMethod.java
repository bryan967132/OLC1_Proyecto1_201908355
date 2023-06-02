package TreeMethod;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import Colors.Type;
import Components.Set;
import Tree.Node;
public class TreeMethod {
    ArrayList<Boolean> validator;
    boolean errorSet = false;
    int id;
    Map<String,Set> sets;
    NextsTable nextsTable;
    Node root;
    Set setNotFound;
    String json;
    String name;
    Tree tree;
    TransitionTable transitionsTable;
    public TreeMethod(Map<String,Set> sets,String name) {
        this.sets = sets;
        this.name = name;
    }
    public void setRegex(int id,Node root) {
        this.id = id;
        this.root = root;
        tree = new Tree(root);
    }
    public void build() {
        tree.calculateFirsts();
        tree.calculateLasts();
        exportDot("tree_" + id + "_" + name,tree.getDot(name),"Tree");
        buildPNG("Tree","ARBOLES_201908355","tree_" + id + "_" + name);
    }
    public void buildNextsTable() {
        tree.calculateNexts();
        nextsTable = tree.getNexts();
        exportDot("nexts_" + id + "_" + name,nextsTable.getDot(name),"Nexts");
        buildPNG("Nexts","SIGUIENTES_201908355","nexts_" + id + "_" + name);
    }
    public void buildTransitionsTable() {
        tree.calculateTransitions();
        transitionsTable = tree.getTransitionsTable();
        exportDot("transitions_" + id + "_" + name,transitionsTable.getDot(name),"Transitions");
        buildPNG("Transitions","TRANSICIONES_201908355","transitions_" + id + "_" + name);
    }
    public void buildAFD() {
        exportDot("afd_" + id + "_" + name,tree.getDotAFD(name),"AFD");
        buildPNG("AFD","AFD_201908355","afd_" + id + "_" + name);
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
        json = "";
        if(!isValid && errorSet && setNotFound != null) {
            return "No se declaró el conjunto \"" + setNotFound.id + "\"";
        }
        json = "\n\t{";
        json += "\n\t\t\"Valor\":\"" + (string.contains("\n") ? string.replace("\n","\\n") : string) + "\",";
        json += "\n\t\t\"ExpresionRegular\":\"" + name + "\",";
        json += "\n\t\t\"Resultado\":\"Cadena " + (isValid ? "Válida" : "Inválida") + "\"";
        json += "\n\t}";
        return "La Expresión: \"" + (string.contains("\n") ? string.replace("\n","\\n") : string) + (!isValid ? "\" no" : "\"") + " es Válida con la Expresión Regular \"" + name + "\".";
    }
    public String getJSON() {
        return json;
    }
    private boolean isValid(String string) {
        validator = new ArrayList<>();
        Transition transition = null;
        Change chng;
        int state = 0;
        Set set;
        char character;
        string = string.replace("\\'","\'").replace("\\\"","\"").replace("\\n","\n");
        for(int i = 0; i < string.length(); i ++) {
            character = string.charAt(i);
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
                    if(set.validate(character)) {
                        validator.add(true);
                        state = chng.toState;
                        break;
                    }
                }
                else if(chng.type == Type.STRING) {
                    if(chng.terminal.equals(String.valueOf(character))) {
                        validator.add(true);
                        state = chng.toState;
                        break;
                    }
                }
                else if(chng.type == Type.SINGLEQUOTE) {
                    if(chng.terminal.replace("\\","").equals(String.valueOf(character))) {
                        validator.add(true);
                        state = chng.toState;
                        break;
                    }
                }
                else if(chng.type == Type.DOUBLEQUOTE) {
                    if(chng.terminal.replace("\\","").equals(String.valueOf(character))) {
                        validator.add(true);
                        state = chng.toState;
                        break;
                    }
                }
                else if(chng.type == Type.ENTER) {
                    if(chng.terminal.replace("\\n","\n").equals(String.valueOf(character))) {
                        validator.add(true);
                        state = chng.toState;
                        break;
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
    private void exportDot(String id,String content,String type) {
        try {
            File file = new File("Dot/" + type);
            if(!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream("Dot/" + type + "/" + id + ".dot");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(IOException e) {}
    }
    private void buildPNG(String typeDot,String typePng,String name) {
        try {
            File file = new File("Data/" + typePng);
            if(!file.exists()) {
                file.mkdirs();
            }
            String graphviz_path = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String dot_path = "Dot/" + typeDot + "/" + name + ".dot";
            String png_path =  "Data/" + typePng + "/" + name + ".png" ;
            ProcessBuilder pBuilder = new ProcessBuilder(graphviz_path, "-Tpng", "-o", png_path, dot_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception e) {}
    }
}