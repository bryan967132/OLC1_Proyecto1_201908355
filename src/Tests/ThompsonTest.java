package Tests;
import Colors.Token;
import Colors.Type;
import Controller.Regex;
import Thompson.ThompsonMethod;
public class ThompsonTest {
    static Regex regex;
    public static void main(String[] args) {
        ThompsonMethod thompsonMethod = new ThompsonMethod();
        System.out.println("-----------------------------------------------------\n\n");
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

            thompsonMethod.setRegex(regex);
            thompsonMethod.build();
            thompsonMethod.buildAFND();
        }
        System.out.println("\n\n-----------------------------------------------------");
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

            thompsonMethod.setRegex(regex);
            thompsonMethod.build();
            thompsonMethod.buildAFND();
        }
        System.out.println("\n\n-----------------------------------------------------");
    }
}