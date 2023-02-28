package BackEnd;
public class Expression {
    public String id;
    public String string;
    public String toString() {
        return "EXPRESIÓN -------------- ID: " + id + " ".repeat(19 - id.length()) + "STRING: " + string;
    }
}