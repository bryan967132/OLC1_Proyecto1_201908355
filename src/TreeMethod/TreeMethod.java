package TreeMethod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import Colors.Token;
import Controller.Set;
import Controller.Regex;
public class TreeMethod {
    Map<String,Set> sets;
    Regex regex;
    Stack<Token> stack;
    Token token;
    Token token1;
    Token token2;
    Tree tree;
    public TreeMethod(Regex regex) {
        this.regex = regex;
        tree = new Tree(regex);
    }
    public TreeMethod(Map<String,Set> sets) {
        this.sets = sets;
    }
    public void setRegex(Regex regex) {
        this.regex = regex;
        tree = new Tree(regex);
    }
    public void build() {
        tree.build();
        tree.createIDNodes();
        tree.calculateFirsts();
        tree.calculateLasts();
        exportGraph(regex.id,tree.getDot(),"Tree");
    }
    public void nextsTable() {
        tree.calculateNexts();
        System.out.println("SIGUIENTES");
        ArrayList<Node> nexts = tree.getNexts();
        for(int i = 0; i < nexts.size(); i ++) {
            System.out.println(nexts.get(i));
        }
    }
    public void transitionsTable() {
        tree.calculateTransitions();
        System.out.println("TRANSICIONES");
        TransitionTable transitionsTable = tree.getTransitionsTable();
        System.out.println(transitionsTable);
    }
    public void buildAFD() {
        exportGraph(regex.id,tree.getDotAFD(),"AFD");
    }
    public void validateString() {
        
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