package BackEnd;
import java.util.ArrayList;
public class Set {
    public String id;
    public String startChar;
    public String endChar;
    public ArrayList<String> specifics = new ArrayList<>();
    public String toString() {
        return "ID: " + id + (specifics.size() > 0 ? "\t\tSpecifics: " + String.join(", ",specifics) : "\t\tStartChar: " + startChar + "\t\t\tEndChar: " + endChar);
    }
}