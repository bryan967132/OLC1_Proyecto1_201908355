package Tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java_cup.runtime.Symbol;
import Language.Scanner;
public class ScannerAnalysisTest {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("src/Tests/Input.olc");
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            System.out.println(input);
            Symbol sym;
            do {
                sym = scanner.next_token();
            } while(sym.value != null);
            System.out.println(scanner.getStrTokens());
            System.out.println(scanner.getStrErrors());
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