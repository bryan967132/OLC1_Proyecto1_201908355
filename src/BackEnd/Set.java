package BackEnd;
import java.util.ArrayList;
public class Set {
    String id = null;
    String startChar = null;
    String endChar = null;
    ArrayList<String> specifics = null;
    public Set(String id,String startChar,String endChar) {
        this.id = id;
        this.startChar = startChar;
        this.endChar = endChar;
    }
    public Set(String id) {
        this.id = id;
        this.specifics = new ArrayList<>();
    }
}