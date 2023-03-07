package Tests;
import BackEnd.TreeMethod;
import Colors.Token;
import Colors.Type;
import Controller.Regex;
public class TreeMethodTest {
    static Regex regex;
    static TreeMethod tree;
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------------");
        // ...*|ababb
        // (a|b)*abb
        System.out.println("...*|ababb - (a|b)*abb");
        regex = new Regex();
        regex.id = "Expression1";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("\"a\"",Type.STRING));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("\"a\"",Type.STRING));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("\"b\"",Type.STRING));

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();
        System.out.println("-----------------------------------------------------");
        // ...ab*b*|ba
        // abb*(b|a)*
        System.out.println("...ab*b*|ba - abb*(b|a)*");
        regex = new Regex();
        regex.id = "Expression2";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"a\"",Type.STRING));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("\"a\"",Type.STRING));

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();
        System.out.println("-----------------------------------------------------");
        // ..a*|abb
        // a(a|b)*b
        System.out.println("..a*|abb - a(a|b)*b");
        regex = new Regex();
        regex.id = "Expression3";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"a\"",Type.STRING));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("\"a\"",Type.STRING));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("\"b\"",Type.STRING));

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();
        System.out.println("-----------------------------------------------------");
        // |..acc..bcd
        // (acc|bcd)
        System.out.println("|..acc..bcd - (acc|bcd)");
        regex = new Regex();
        regex.id = "Expression4";
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"a\"",Type.STRING));
        regex.expression.push(new Token("\"c\"",Type.STRING));
        regex.expression.push(new Token("\"c\"",Type.STRING));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\"b\"",Type.STRING));
        regex.expression.push(new Token("\"c\"",Type.STRING));
        regex.expression.push(new Token("\"d\"",Type.STRING));

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();
        System.out.println("-----------------------------------------------------");
        // .+numero?."."+numero
        // numero+("."numero+)?
        System.out.println(".+numero?.\".\"+numero - numero+(\".\"numero+)?");
        regex = new Regex();
        regex.id = "Numeros";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("+",Type.POSITIVE));
        regex.expression.push(new Token("numero",Type.ID));
        regex.expression.push(new Token("?",Type.OPTIONAL));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("\".\"",Type.STRING));
        regex.expression.push(new Token("+",Type.POSITIVE));
        regex.expression.push(new Token("numero",Type.ID));

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();
    }
}