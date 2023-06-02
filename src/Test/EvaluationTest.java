package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Map;
import Language.Parser;
import Language.Scanner;
import Thompson.ThompsonMethod;
import Tree.Node;
import TreeMethod.TreeMethod;
public class EvaluationTest {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("./Inputs/THOMPSON.olc");
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            ThompsonMethod thompsonMethod;
            TreeMethod treeMethod;
            Node rgx;
            for(Map.Entry<String,Node> regex : parser.regexs.entrySet()) {
                rgx = regex.getValue();
                treeMethod = new TreeMethod(parser.sets,regex.getKey());
                treeMethod.setRegex(1,rgx);
                treeMethod.build();
                treeMethod.buildNextsTable();
                treeMethod.buildTransitionsTable();
                treeMethod.buildAFD();
                thompsonMethod = new ThompsonMethod(1,regex.getKey());
                thompsonMethod.setRegex(1,rgx);
                thompsonMethod.build();
                thompsonMethod.buildAFND();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    public static String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String texto = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
            fis.close();
            return texto;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
}