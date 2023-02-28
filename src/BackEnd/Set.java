package BackEnd;
import java.util.ArrayList;
public class Set {
    public String id;
    public String startChar;
    public String endChar;
    public ArrayList<String> specifics = new ArrayList<>();
    public String toString() {
        return "CONJUNTO --------------- ID: " + id + (specifics.size() > 0 ? "\t\tSPECIFICS: " + String.join(", ",specifics) : "\t\tSTARTCHAR: " + startChar + "\t\t\tENDCHAR: " + endChar);
    }
}