package Controller;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class Set {
    public String id;
    public char startChar;
    public char endChar;
    public ArrayList<Character> specifics = new ArrayList<>();
    public boolean validate(char character) {
        if(specifics.size() > 0) {
            if(specifics.contains(character)) {
                return true;
            }
            return false;
        }
        if(character >= startChar && character <= endChar) {
            if(existLowerCase(character)) {
                return true;
            }
            if(existUpperCase(character)) {
                return true;
            }
            if(existNumbers(character)) {
                return true;
            }
            if(existSpecialChars(character)) {
                return true;
            }
        }
        return false;
    }
    public boolean existLowerCase(char character) {
        return
            Characters.lowercase.contains(startChar)     && Characters.lowercase.contains(startChar)     && Characters.lowercase.contains(character) &&
            !Characters.uppercase.contains(startChar)    && !Characters.uppercase.contains(startChar)    && !Characters.uppercase.contains(character) &&
            !Characters.numbers.contains(startChar)      && !Characters.numbers.contains(startChar)      && !Characters.numbers.contains(character) &&
            !Characters.specialChars.contains(startChar) && !Characters.specialChars.contains(startChar) && !Characters.specialChars.contains(character);
    }
    public boolean existUpperCase(char character) {
        return
            !Characters.lowercase.contains(startChar)    && !Characters.lowercase.contains(startChar)    && !Characters.lowercase.contains(character) &&
            Characters.uppercase.contains(startChar)     && Characters.uppercase.contains(startChar)     &&  Characters.uppercase.contains(character) &&
            !Characters.numbers.contains(startChar)      && !Characters.numbers.contains(startChar)      && !Characters.numbers.contains(character) &&
            !Characters.specialChars.contains(startChar) && !Characters.specialChars.contains(startChar) && !Characters.specialChars.contains(character);
    }
    public boolean existNumbers(char character) {
        return
            !Characters.lowercase.contains(startChar)    && !Characters.lowercase.contains(startChar)    && !Characters.lowercase.contains(character) &&
            !Characters.uppercase.contains(startChar)    && !Characters.uppercase.contains(startChar)    && !Characters.uppercase.contains(character) &&
            Characters.numbers.contains(startChar)       && Characters.numbers.contains(startChar)       && Characters.numbers.contains(character) &&
            !Characters.specialChars.contains(startChar) && !Characters.specialChars.contains(startChar) && !Characters.specialChars.contains(character);
    }
    public boolean existSpecialChars(char character) {
        return
            !Characters.lowercase.contains(startChar)    && !Characters.lowercase.contains(startChar)    && !Characters.lowercase.contains(character) &&
            !Characters.uppercase.contains(startChar)    && !Characters.uppercase.contains(startChar)    && !Characters.uppercase.contains(character) &&
            !Characters.numbers.contains(startChar)      && !Characters.numbers.contains(startChar)      && !Characters.numbers.contains(character) &&
            Characters.specialChars.contains(startChar)  && Characters.specialChars.contains(startChar)  && Characters.specialChars.contains(character);
    }
    public String toString() {
        return "CONJUNTO --------------- ID: " + id + (specifics.size() > 0 ? " ".repeat(20 - id.length()) + "SPECIFICS: " + specifics.stream().map(Object::toString).collect(Collectors.joining(", ")) : " ".repeat(20 - id.length()) + "STARTCHAR: " + startChar + " ".repeat(20 - id.length()) + "ENDCHAR: " + endChar);
    }
}