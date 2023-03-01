package BackEnd;
import java.util.Stack;
public class Regex {
    public String id;
    public Stack<String> expression = new Stack<>();
    public String toString() {
        return  "ID: " + id + " ".repeat(20 - id.length()) + "Expresión: " + String.join(" ",expression);
    }
}