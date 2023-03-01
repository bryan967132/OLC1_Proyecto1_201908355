package Tests;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import BackEnd.BackEnd;
public class ParserAnalysisTest {
    public static void main(String[] args) throws Exception {
        BackEnd backend = new BackEnd();
        backend.analyze(readInput("src/Tests/Input.olc"));
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