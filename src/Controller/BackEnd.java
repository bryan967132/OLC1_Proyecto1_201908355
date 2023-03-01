package Controller;
import java.io.BufferedReader;
import java.io.StringReader;
import Language.Parser;
import Language.Scanner;
public class BackEnd {
    public void analyze(String input) {
        try {
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            System.out.println(input);
            Parser parser = new Parser(scanner);
            parser.parse();
            System.out.println(parser.getStrExecution());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}