package Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import Colors.ParserC;
import Colors.ScannerC;
import Colors.WordPainter;
import Interface.IconFile;
import Language.Parser;
import Language.Scanner;
import TreeMethod.TreeMethod;
public class Controller {
    public ArrayList<IconFile> pjs = new ArrayList<>();
    TreeMethod treeMthod;
    public int existPJFile(String path) {
        for(int i = 0; i < pjs.size(); i ++) {
            if(pjs.get(i).path.equals(path)) {
                return i;
            }
        }
        return -1;
    }
    public int countPJ() {
        return pjs.size();
    }
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
    public String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
                if(br.ready()) {
                    text += "\n";
                }
            }
            br.close();
            fis.close();
            return text;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}