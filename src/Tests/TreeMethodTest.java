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
        set.id = "mayus";
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
        set.id = "minus";
        //a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
        {set.specifics.add('a');
        set.specifics.add('b');
        set.specifics.add('c');
        set.specifics.add('d');
        set.specifics.add('e');
        set.specifics.add('f');
        set.specifics.add('g');
        set.specifics.add('h');
        set.specifics.add('i');
        set.specifics.add('j');
        set.specifics.add('k');
        set.specifics.add('l');
        set.specifics.add('m');
        set.specifics.add('n');
        set.specifics.add('o');
        set.specifics.add('p');
        set.specifics.add('q');
        set.specifics.add('r');
        set.specifics.add('s');
        set.specifics.add('t');
        set.specifics.add('u');
        set.specifics.add('v');
        set.specifics.add('w');
        set.specifics.add('x');
        set.specifics.add('y');
        set.specifics.add('z');}
        sets.put(set.id,set);
        System.out.println(set);
        set = new Set();
        set.id = "letra";
        set.startChar = 'a';
        set.endChar = 'z';
        sets.put(set.id,set);
        System.out.println(set);
        set = new Set();
        set.id = "digito";
        set.startChar = '0';
        set.endChar = '9';
        sets.put(set.id,set);
        System.out.println(set);
        tree = new TreeMethod(sets);
        System.out.println("-----------------------------------------------------");
        // ."C"."O"."M"."P"."I"."1" ? + | | {letra} {digito} " "
        // "COMPI1"((letra|digito|\s)+)?
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

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree.setRegex(regex);
        tree.build();
        tree.nextsTable();
        tree.transitionsTable();
        tree.buildAFD();
        }
        System.out.println(tree.validateString("COMPI1 sale con 100"));
        System.out.println("-----------------------------------------------------");
        // . \' . + | | | | \n {minus} {mayus} {digito} " " \'
        // \'(\n|minus|mayud|digito|\s)+\'
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

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree.setRegex(regex);
        tree.build();
        tree.nextsTable();
        tree.transitionsTable();
        tree.buildAFD();
        }
        System.out.println(tree.validateString("\'cadena entre\ncomilla simple\'"));

        System.out.println("\n\n-----------------------------------------------------");
        // . ? | + {digito} * {letra} ? . * {digito} + {letra}
        // (digito+|letra*)?(digito*letra+)?
        System.out.println(". ? | + {digito} * {letra} ? . * {digito} + {letra} - (digito+|letra*)?(digito*letra+)?");
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

        regex.expression.add(0,new Token(".",Type.CONCAT));
        regex.expression.push(new Token("#",Type.END));
        tree.setRegex(regex);
        tree.build();
        tree.nextsTable();
        tree.transitionsTable();
        tree.buildAFD();
    }
}