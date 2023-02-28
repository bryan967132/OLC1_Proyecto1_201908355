package BackEnd;
import java.util.Stack;
public class Regex {
    public String id;
    public Stack<String> expression = new Stack<>();
    public String toString() {
        return  "EXPRESIÓN REGULAR ------ ID: " + id + " ".repeat(19 - id.length()) + "EXPRESSION: " + String.join(" ",expression);
    }
}