package Tests;
import java.awt.Desktop;
import java.io.File;
public class DotToPngTest {
    public static void main(String[] args) {
        String file_input_path = "Dot/Tree/frase.dot";
        String do_path = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
        String file_get_path =  "ARBOLES_201908355/frase.png" ;
        try {
            ProcessBuilder pBuilder;
            pBuilder = new ProcessBuilder(do_path, "-Tpng", "-o", file_get_path, file_input_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
            Desktop.getDesktop().open(new File(file_get_path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}