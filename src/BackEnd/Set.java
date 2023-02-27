package BackEnd;
import java.util.ArrayList;
public class Set {
    public String id = null;
    public String startChar = null;
    public String endChar = null;
    public ArrayList<String> specifics = null;
    public String toString() {
        return "\nID: " + id + (specifics != null ? "\t\tSpecifics: " + specifics : "\t\tStartChar: " + startChar + "\t\t\tEndChar: " + endChar);
    }
}