package BackEnd;
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
        tree = new Tree(regex);
    }
    public void build() {
        tree.build();
        System.out.println(tree.getDot());
    }
}