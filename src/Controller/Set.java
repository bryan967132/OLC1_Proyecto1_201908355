package Controller;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class Set {
    public String id;
    public char startChar;
    public char endChar;
    public ArrayList<Character> specifics = new ArrayList<>();
    public String toString() {
        return "CONJUNTO --------------- ID: " + id + (specifics.size() > 0 ? " ".repeat(20 - id.length()) + "SPECIFICS: " + specifics.stream().map(Object::toString).collect(Collectors.joining(", ")) : " ".repeat(20 - id.length()) + "STARTCHAR: " + startChar + "\t\t\tENDCHAR: " + endChar);
    }
}