package Controller;
import java.io.BufferedReader;
import java.io.StringReader;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import Colors.ParserC;
import Colors.ScannerC;
import Colors.WordPainter;
import Language.Parser;
import Language.Scanner;
import TreeMethod.TreeMethod;
public class Controller {
    TreeMethod treeMthod;
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
            ParserC parser = new ParserC(sc,painter);
            parser.parse();
        }
        catch(Exception e) {}
    }
    public void analyze(JTextPane editor,JTextPane console) {
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0,doc.getLength());
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            parser.parse();
            //if(scanner)
            if(parser.isSuccessExecution()) {
                if(parser.getExcecution().size() > 0) {
                    console.setText("EXREGAN:\n-> Successfully Input Analysis.");
                    return;
                }
                console.setText("EXREGAN:\n->");
                return;
            }
            console.setText("EXREGAN:\n" + parser.getStrErrors());
        } catch (Exception e) {
            System.out.println(e);
        }
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