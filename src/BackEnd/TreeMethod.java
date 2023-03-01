package BackEnd;
import java.io.FileWriter;
import java.io.IOException;
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
        exportGraph(regex.id,tree.getDot());
    }
    public void exportGraph(String id,String content) {
        try {
            FileWriter file = new FileWriter("Dot/" + id + ".dot");
            file.write(content);
            file.close();
        }
        catch(IOException e) {}
    }
}