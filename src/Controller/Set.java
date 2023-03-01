package Controller;
import java.util.ArrayList;
public class Set {
    public String id;
    public String startChar;
    public String endChar;
    public ArrayList<String> specifics = new ArrayList<>();
    public String toString() {
        return "CONJUNTO --------------- ID: " + id + (specifics.size() > 0 ? " ".repeat(20 - id.length()) + "SPECIFICS: " + String.join(", ",specifics) : " ".repeat(20 - id.length()) + "STARTCHAR: " + startChar + "\t\t\tENDCHAR: " + endChar);
    }
}