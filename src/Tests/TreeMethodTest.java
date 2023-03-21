package Tests;
import java.util.Map;
import java.util.TreeMap;
import Colors.Token;
import Colors.Type;
import Controller.Set;
import Controller.Regex;
import TreeMethod.TreeMethod;
public class TreeMethodTest {
    static Regex regex;
    static TreeMethod tree;
    public static void main(String[] args) {
        Map<String,Set> sets = new TreeMap<>();
        Set set = new Set();
        set.id = "mayuscula";
        //A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
        {set.specifics.add('A');
        set.specifics.add('B');
        set.specifics.add('C');
        set.specifics.add('D');
        set.specifics.add('E');
        set.specifics.add('F');
        set.specifics.add('G');
        set.specifics.add('H');
        set.specifics.add('I');
        set.specifics.add('J');
        set.specifics.add('K');
        set.specifics.add('L');
        set.specifics.add('M');
        set.specifics.add('N');
        set.specifics.add('O');
        set.specifics.add('P');
        set.specifics.add('Q');
        set.specifics.add('R');
        set.specifics.add('S');
        set.specifics.add('T');
        set.specifics.add('U');
        set.specifics.add('V');
        set.specifics.add('W');
        set.specifics.add('X');
        set.specifics.add('Y');
        set.specifics.add('Z');}
        sets.put(set.id,set);
        System.out.println(set);
        set = new Set();
        set.id = "minuscula";
        //a~z
        set.startChar = 'a';
        set.endChar = 'z';
        sets.put(set.id,set);
        System.out.println(set);
        set = new Set();
        set.id = "numero";
        //0,1,2,3,4,5,6,7,8,9
        {set.specifics.add('0');
        set.specifics.add('1');
        set.specifics.add('2');
        set.specifics.add('3');
        set.specifics.add('4');
        set.specifics.add('5');
        set.specifics.add('6');
        set.specifics.add('7');
        set.specifics.add('8');
        set.specifics.add('9');}
        sets.put(set.id,set);
        System.out.println(set);
        set = new Set();
        set.id = "simbolos";
        //-,_
        {set.specifics.add('-');
        set.specifics.add('_');}
        sets.put(set.id,set);
        System.out.println(set);
        tree = new TreeMethod(sets);
        System.out.println("-----------------------------------------------------");
        // . | {minuscula} {mayuscula} . + | | | {simbolos} {minuscula} {mayuscula} {numero} . "@" . + | {minuscula} {mayuscula} . ".". "c" . "o" "m";
        // (minuscula|mayuscula)+(simbolos|minuscula|mayuscula|numero)+@(minuscula|mayuscula)+.com
        {
            System.out.println("(minuscula|mayuscula)(simbolos|minuscula|mayuscula|numero)+@(minuscula|mayuscula)+.com");
            regex = new Regex();
            regex.id = "correoElectronico";
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("|",Type.OR));
            regex.expression.push(new Token("minuscula",Type.ID));
            regex.expression.push(new Token("mayuscula",Type.ID));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("+",Type.POSITIVE));
            regex.expression.push(new Token("|",Type.OR));
            regex.expression.push(new Token("|",Type.OR));
            regex.expression.push(new Token("|",Type.OR));
            regex.expression.push(new Token("simbolos",Type.ID));
            regex.expression.push(new Token("minuscula",Type.ID));
            regex.expression.push(new Token("mayuscula",Type.ID));
            regex.expression.push(new Token("numero",Type.ID));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"@\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("+",Type.POSITIVE));
            regex.expression.push(new Token("|",Type.OR));
            regex.expression.push(new Token("minuscula",Type.ID));
            regex.expression.push(new Token("mayuscula",Type.ID));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\".\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"c\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"o\"",Type.STRING));
            regex.expression.push(new Token("\"m\"",Type.STRING));

            tree.setRegex(0,regex);
            tree.build();
            tree.buildNextsTable();
            tree.buildTransitionsTable();
            tree.buildAFD();
        }
        System.out.println(tree.validateString("lachalana666@ingusac.com"));
        System.out.println("-----------------------------------------------------");
        // . "h" . "t" . "t" . "p" . ? "s" . ":" . "/" . "/" . + {minuscula} . "." + {minuscula}
        // http(s)?://(minuscula)+.(minuscula)+
        {
            System.out.println("http(s)?://(minuscula)+.(minuscula)+");
            regex = new Regex();
            regex.id = "url";
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"h\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"t\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"t\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"p\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("?",Type.OPTIONAL));
            regex.expression.push(new Token("\"s\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\":\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"/\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\"/\"",Type.STRING));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("+",Type.POSITIVE));
            regex.expression.push(new Token("minuscula",Type.ID));
            regex.expression.push(new Token(".",Type.CONCAT));
            regex.expression.push(new Token("\".\"",Type.STRING));
            regex.expression.push(new Token("+",Type.POSITIVE));
            regex.expression.push(new Token("minuscula",Type.ID));

            tree.setRegex(0,regex);
            tree.build();
            tree.buildNextsTable();
            tree.buildTransitionsTable();
            tree.buildAFD();
        }
        System.out.println(tree.validateString("https://facebook.com"));
        System.out.println("\n\n-----------------------------------------------------");
    }
}