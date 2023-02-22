package Generators;
public class Parser {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                new String[] {
                    "-destdir",
                    "src/BackEnd",
                    "-symbols",
                    "Sym",
                    "-parser",
                    "Parser",
                    "src/BackEnd/Parser.cup"
                }
            );
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}