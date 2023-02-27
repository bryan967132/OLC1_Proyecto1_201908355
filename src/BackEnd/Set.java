package BackEnd;
import java.util.ArrayList;
public class Set {
    public String id;
    public String startChar;
    public String endChar;
    public ArrayList<String> specifics;
    public String toString() {
        return "ID: " + id + (specifics != null ? "\t\tSpecifics: " + specifics : "\t\tStartChar: " + startChar + "\t\t\tEndChar: " + endChar);
    }
}