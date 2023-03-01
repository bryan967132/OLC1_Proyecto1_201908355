package Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import Colors.ParserC;
import Colors.ScannerC;
import Colors.Token;
import Colors.WordPainter;
import Language.Parser;
import Language.Scanner;
public class BackEnd {
    public void setFormat(JTextPane editor) throws IOException {
        String input = editor.getText();
        WordPainter painter = new WordPainter();
        ScannerC sc = new ScannerC(
            new BufferedReader(
                new StringReader(input)
            ),
            painter
        );
        painter.setStyle(input);
        editor.setDocument(painter.box.getDocument());
        Token token;
        ArrayList<Token> code = new ArrayList<>();
        while((token = sc.yylex()) != null) {
            code.add(token);
        }
        ParserC parser = new ParserC(code,painter);
        parser.parse();
    }
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