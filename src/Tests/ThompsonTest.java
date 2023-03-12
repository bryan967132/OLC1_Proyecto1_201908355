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
        // ."C"."O"."M"."P"."I"."1" ? + | | {letra} {digito} " "
        // "COMPI1"((letra | digito | \s)+)?
        {
        System.out.println(".\"C\".\"O\".\"M\".\"P\".\"I\".\"1\" ? + | | {letra} {digito} \" \" - \"COMPI1\"((letra|digito|\\s)+)?");
        regex = new Regex();
        regex.id = "frase";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"C\"",Type.STRING));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"O\"",Type.STRING));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"M\"",Type.STRING));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"P\"",Type.STRING));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"I\"",Type.STRING));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"1\"",Type.STRING));
        regex.expression.push(new Token("?",Type.OPTIONAL));
        regex.expression.push(new Token("+",Type.POSITIVE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("letra",Type.ID));
        regex.expression.push(new Token("digito",Type.ID));
        regex.expression.push(new Token("\" \"",Type.STRING));

        thompsonMethod.setRegex(regex);
        thompsonMethod.build();
        thompsonMethod.buildAFND();
        }
        System.out.println("\n\n-----------------------------------------------------");
        // . \' . + | | | | \n {minus} {mayus} {digito} " " \'
        // \'(\n|minus|mayus|digito|\s)+\'
        {
        System.out.println(". \\' . + | | | | \\n {minus} {mayus} {digito} \" \" \\' - \\'(\\n|minus|mayus|digito|\\s)+\\'");
        regex = new Regex();
        regex.id = "cadena";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\'",Type.SINGLEQUOTE));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("+",Type.POSITIVE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("\n",Type.ENTER));
        regex.expression.push(new Token("minus",Type.ID));
        regex.expression.push(new Token("mayus",Type.ID));
        regex.expression.push(new Token("digito",Type.ID));
        regex.expression.push(new Token("\" \"",Type.STRING));
        regex.expression.push(new Token("\'",Type.SINGLEQUOTE));

        thompsonMethod.setRegex(regex);
        thompsonMethod.build();
        thompsonMethod.buildAFND();
    }
    System.out.println("\n\n-----------------------------------------------------");
        regex = new Regex();
        regex.id = "Kleene";
        regex.expression.push(new Token(".",Type.CONCAT));
        
        regex.expression.push(new Token("?",Type.OPTIONAL));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("+",Type.POSITIVE));
        regex.expression.push(new Token("digito",Type.ID));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("letra",Type.ID));
        
        regex.expression.push(new Token("?",Type.OPTIONAL));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("digito",Type.ID));
        regex.expression.push(new Token("+",Type.POSITIVE));
        regex.expression.push(new Token("letra",Type.ID));
        
        thompsonMethod.setRegex(regex);
        thompsonMethod.build();
        thompsonMethod.buildAFND();
        System.out.println("\n\n-----------------------------------------------------");
    }
}