package BackEnd;
import java.util.Stack;
import java.util.stream.Collectors;
import Colors.Token;
public class Regex {
    public String id;
    public Stack<Token> expression = new Stack<>();
    public String toString() {
        return  "EXPRESIÓN REGULAR ------ ID: " + id + " ".repeat(20 - id.length()) + "EXPRESSION: " + expression.stream().map(Object::toString).collect(Collectors.joining(" "));
    }
}