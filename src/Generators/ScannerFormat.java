package Generators;
import java.io.File;
public class ScannerFormat {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        jflex.Main.generate(
            new File(
                "src/Colors/Scanner.jflex"
            )
        );
    }
}