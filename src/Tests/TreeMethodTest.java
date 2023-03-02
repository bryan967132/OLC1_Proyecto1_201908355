package Tests;
import BackEnd.TreeMethod;
import Colors.Token;
import Colors.Type;
import Controller.Regex;
public class TreeMethodTest {
    static Regex regex;
    static TreeMethod tree;
    public static void main(String[] args) {
        // ...*|ababb
        // (a|b)*abb
        regex = new Regex();
        regex.id = "Expresion1";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("a",Type.ID));
        regex.expression.push(new Token("b",Type.ID));
        regex.expression.push(new Token("a",Type.ID));
        regex.expression.push(new Token("b",Type.ID));
        regex.expression.push(new Token("b",Type.ID));

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();

        System.out.println("-----------------------------------------------------");
        // ...ab*b*|ba
        // abb*(b|a)*
        regex = new Regex();
        regex.id = "Expresion2";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("a",Type.ID));
        regex.expression.push(new Token("b",Type.ID));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("b",Type.ID));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("b",Type.ID));
        regex.expression.push(new Token("a",Type.ID));
        
        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();

        System.out.println("-----------------------------------------------------");
        // .a.*|abb
        // a(a|b)*b
        regex = new Regex();
        regex.id = "Expresion2";
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("a",Type.ID));
        regex.expression.push(new Token(".",Type.CONCAT));
        regex.expression.push(new Token("*",Type.KLEENE));
        regex.expression.push(new Token("|",Type.OR));
        regex.expression.push(new Token("a",Type.ID));
        regex.expression.push(new Token("b",Type.ID));
        regex.expression.push(new Token("b",Type.ID));
        
        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree = new TreeMethod(regex);
        tree.build();
    }
}