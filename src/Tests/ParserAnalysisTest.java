package Tests;
import BackEnd.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
public class ParserAnalysisTest {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("src/Tests/Input.olc");
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            System.out.println(input);
            Parser parser = new Parser(scanner);
            parser.parse();
            System.out.println(scanner.getTokens());
            System.out.println(scanner.getErrors());
            System.out.println(parser.getExecution());
        } catch (Exception e) {
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}