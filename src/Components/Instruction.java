package Components;
import Controller.Expression;
import Controller.Regex;
import Controller.Set;
public class Instruction {
    Expression expression;
    Regex regex;
    Set set;
    String execution;
    ErrorS error;
    public Instruction(Expression expression) {
        this.expression = expression;
    }
    public Instruction(Regex regex) {
        this.regex = regex;
    }
    public Instruction(Set set) {
        this.set = set;
    }
    public Instruction(String execution) {
        this.execution = execution;
    }
    public Instruction(ErrorS error) {
        this.error = error;
    }
    public String getHTML() {
        return (error != null ? error.getHTML() : "\t\t\t<td>\n\t\t\t\t" + execution + "\n\t\t\t</td>\n");
    }
    public String toString() {
        return (expression != null ? expression : (regex != null ? regex : (set != null ? set : (execution != null ? execution : error)))) + "";
    }
}