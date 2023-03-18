package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;
import Colors.ParserC;
import Colors.ScannerC;
import Colors.WordPainter;
import Interface.IDE;
import Interface.IconFile;
import Interface.Path;
import Language.Parser;
import Language.Scanner;
import TreeMethod.TreeMethod;
public class Controller {
    public ArrayList<IconFile> pjs = new ArrayList<>();
    GraphsBuilder grphsBldr = new GraphsBuilder();
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
    public void analyze(int index,JTextPane editor,JTextPane console) {
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
                IconFile current = pjs.get(index);
                if(parser.getExcecution().size() > 0) {
                    console.setText("EXREGAN: " + current.name + "\n-> Análisis de Entrada Exitoso.");
                    grphsBldr.buildTreeMethod(index,pjs.get(index),parser.getSets(),parser.getRegexs());
                    return;
                }
                console.setText("EXREGAN: " + current.name + "\n->");
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
    public void saveOLCPJ(int index,JTextPane editor) {
        try {
            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(pjs.get(index).path),
                    "utf-8"
                )
            );
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0,doc.getLength());
            writer.write(input);
            writer.close();
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
    public void serialize() {
        try {
            Path[] pjs1 = new Path[pjs.size()];
            for(int i = 0; i < pjs.size(); i ++) {
                pjs1[i] = new Path(pjs.get(i).id,pjs.get(i).path);
            }
            FileOutputStream file = new FileOutputStream("bin/Files");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(pjs1);
            output.close();
            file.close();
        }
        catch(Exception e) {}
    }
    public void deserialize(IDE ide) {
        try {
            FileInputStream file = new FileInputStream("bin/Files");
            ObjectInputStream input = new ObjectInputStream(file);
            Path[] pjs1 = (Path[]) input.readObject();
            input.close();
            file.close();
            pjs = new ArrayList<>();
            for(Path path : pjs1) {
                pjs.add(new IconFile(path.id,new File(path.path),ide,this));
            }
        }
        catch (Exception e) {}
    }
}