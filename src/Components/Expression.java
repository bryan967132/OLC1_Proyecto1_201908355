package Components;
public class Expression {
    public String id;
    public String string;
    public Expression(String id,String string) {
        this.id = id;
        this.string = string;
    }
    public String toString() {
        return id + "=" + string;
    }
}