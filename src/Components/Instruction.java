package Components;
import BackEnd.Regex;
import BackEnd.Set;
public class Instruction {
    Regex regex;
    Set set;
    String execution;
    ErrorS error;
    public Instruction(String execution) {
        this.execution = execution;
    }
    public Instruction(ErrorS error) {
        this.error = error;
    }
    public Instruction(Set set) {
        this.set = set;
    }
    public Instruction(Regex regex) {
        this.regex = regex;
    }
    public String toString() {
        return (execution != null ? execution : (set != null ? set : (regex != null ? regex : error))) + "";
    }
}