package Controller;
import java.util.ArrayList;
import java.util.Arrays;
public class Characters {
    public static ArrayList<Character> uppercase = new ArrayList<Character>(
        Arrays.asList(
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
        )
    );
    public static ArrayList<Character> lowercase = new ArrayList<Character>(
        Arrays.asList(
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
        )
    );
    public static ArrayList<Character> numbers = new ArrayList<Character>(
        Arrays.asList(
            '0','1','2','3','4','5','6','7','8','9'
        )
    );
    public static ArrayList<Character> specialChars = new ArrayList<Character>(
        Arrays.asList(
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
            ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}'
        )
    );
}