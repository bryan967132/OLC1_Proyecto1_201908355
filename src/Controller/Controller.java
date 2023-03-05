package Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import Colors.ParserC;
import Colors.ScannerC;
import Colors.Token;
import Colors.WordPainter;
import Language.Parser;
import Language.Scanner;
public class Controller {
    public void setFormat(JTextPane editor) {
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0,doc.getLength());
            WordPainter painter = new WordPainter();
            ScannerC sc = new ScannerC(
                new BufferedReader(
                    new StringReader(input)
                ),
                painter
            );
            painter.setStyle(editor);
            Token token;
            ArrayList<Token> code = new ArrayList<>();
            while((token = sc.yylex()) != null) {
                code.add(token);
            }
            ParserC parser = new ParserC(code,painter);
            parser.parse();
        }
        catch(Exception e) {}
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