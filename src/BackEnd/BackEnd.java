package BackEnd;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import Colors.Parser;
import Colors.Scanner;
import Colors.Token;
import Colors.WordPainter;
public class BackEnd {
    public void setFormat(JTextPane editor) throws IOException {
        String input = editor.getText();
        WordPainter painter = new WordPainter();
        Scanner sc = new Scanner(
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
        Parser parser = new Parser(code,painter);
        parser.parse();
    }
}