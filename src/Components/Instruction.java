package Components;
import BackEnd.Expression;
import BackEnd.Regex;
import BackEnd.Set;
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
    public String toString() {
        return (expression != null ? expression : (regex != null ? regex : (set != null ? set : (execution != null ? execution : error)))) + "";
    }
}