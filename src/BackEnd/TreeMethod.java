package BackEnd;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Stack;
import Colors.Token;
import Controller.Regex;
public class TreeMethod {
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
    public void build() {
        tree.build();
        tree.createIDNodes();
        tree.calculateFirsts();
        tree.calculateLasts();
        tree.calculateNexts();
        ArrayList<Node> nexts = tree.getNexts();
        for(int i = 0; i < nexts.size(); i ++) {
            System.out.println(nexts.get(i));
        }
        
        exportGraph(regex.id,tree.getDot());
    }
    public void exportGraph(String id,String content) {
        try {
            FileOutputStream outputStream = new FileOutputStream("Dot/" + id + ".dot");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(IOException e) {}
    }
}