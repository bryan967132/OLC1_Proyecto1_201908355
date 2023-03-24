package Generators;
public class ParserFormat {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                new String[] {
                    "-destdir",
                    "src/Colors",
                    "-symbols",
                    "Sym",
                    "-parser",
                    "ParserC",
                    "src/Colors/ParserC.cup"
                }
            );
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}