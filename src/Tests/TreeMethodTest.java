package Tests;
import BackEnd.TreeMethod;
import Colors.Token;
import Colors.Types;
import Controller.Regex;
public class TreeMethodTest {
    static Regex regex;
    static TreeMethod tree;
    public static void main(String[] args) {
        // ....*|ababb#
        // (a|b)*abb#
        /*regex = new Regex();
        regex.id = "Expresion1";
        regex.expression.push(new Token(".",Tokens.CONCAT));
        regex.expression.push(new Token(".",Tokens.CONCAT));
        regex.expression.push(new Token(".",Tokens.CONCAT));
        regex.expression.push(new Token("*",Tokens.KLEENE));
        regex.expression.push(new Token("|",Tokens.OR));
        regex.expression.push(new Token("a",Tokens.ID));
        regex.expression.push(new Token("b",Tokens.ID));
        regex.expression.push(new Token("a",Tokens.ID));
        regex.expression.push(new Token("b",Tokens.ID));
        regex.expression.push(new Token("b",Tokens.ID));

        regex.expression.add(0,new Token(".",Tokens.CONCAT));
        regex.expression.push(new Token("#",Tokens.END));
        tree = new TreeMethod(regex);
        tree.build();*/
        // ...ab*b*|ba#
        // abb*(b|a)*#
        regex = new Regex();
        regex.id = "Expresion1";
        regex.expression.push(new Token(".",Types.CONCAT));
        regex.expression.push(new Token(".",Types.CONCAT));
        regex.expression.push(new Token(".",Types.CONCAT));
        regex.expression.push(new Token("a",Types.ID));
        regex.expression.push(new Token("b",Types.ID));
        regex.expression.push(new Token("*",Types.KLEENE));
        regex.expression.push(new Token("b",Types.ID));
        regex.expression.push(new Token("*",Types.KLEENE));
        regex.expression.push(new Token("|",Types.OR));
        regex.expression.push(new Token("b",Types.ID));
        regex.expression.push(new Token("a",Types.ID));

        regex.expression.add(0,new Token(".",Types.CONCAT));
        regex.expression.push(new Token("#",Types.END));
        tree = new TreeMethod(regex);
        tree.build();
    }
}