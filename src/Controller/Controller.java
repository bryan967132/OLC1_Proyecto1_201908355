package Controller;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    MethodsBuilder mthdsBldr = new MethodsBuilder();
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
    public void analyze(IDE ide,int index,JTextPane editor,JTextPane console,JPanel graphics) {
        try {
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0,doc.getLength());
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            Parser parser = new Parser(scanner);
            IconFile currentFile = pjs.get(index);
            parser.setObjects(index,currentFile.name);
            parser.parse();
            if(parser.isSuccessExecution()) {
                if(parser.getExcecution().size() > 0) {
                    String consoleOut = "EXREGAN: " + currentFile.name + "\n-> Análisis de Entrada Exitoso.";
                    if(parser.getRegexs().size() > 0) {
                        mthdsBldr.buildethods(ide,index,pjs.get(index),parser.getSets(),parser.getRegexs());
                        ide.showManagerGraphs();
                        lookGraphs(ide,index);
                        consoleOut += "\n-> Autómatas creados.";
                    }
                    console.setText(consoleOut);
                }
                else {
                    console.setText("EXREGAN: " + currentFile.name + "\n->");
                }
            }
            else {
                console.setText("EXREGAN:\n" + parser.getStrErrors());
            }
            if(parser.s.getErrors().size() > 0 || parser.getErrors().size() > 0) {
                new ReportHTML().reportErrors(index,currentFile.name,parser.s.getErrors(),parser.getErrors());
            }
        } catch (Exception e) {}
    }
    public void lookGraphs(IDE ide,int index) {
        ide.zoomFactor = 1.05;
        ide.graphics.removeMouseListener(ide);
        ide.graphics.removeMouseWheelListener(ide);
        ide.graphics.removeMouseMotionListener(ide);
		ide.regexCB.repaint();
        ide.graphics.removeAll();
        ide.img = new JLabel();
        ide.image = new ImageIcon((ide.treesR.isSelected() ? "ARBOLES_201908355/tree_" : (ide.nextsR.isSelected() ? "SIGUIENTES_201908355/nexts_" : (ide.transitionsR.isSelected() ? "TRANSICIONES_201908355/transitions_" : (ide.afdsR.isSelected() ? "AFD_201908355/afd_" : "AFND_201908355/afnd_")))) + index + "_" + ide.regexCB.getSelectedItem() + ".png");
        ide.icono = new ImageIcon(ide.image.getImage().getScaledInstance(ide.image.getIconWidth(),ide.image.getIconHeight(),Image.SCALE_DEFAULT));
        ide.img.setIcon(ide.icono);
        ide.img.setBounds(0,0,ide.icono.getIconWidth(),ide.icono.getIconHeight());
        ide.graphics.add(ide.img);
        ide.graphics.addMouseListener(ide);
        ide.graphics.addMouseWheelListener(ide);
        ide.graphics.addMouseMotionListener(ide);
        ide.graphics.repaint();
    }
    public Parser getExpresions(int index,JTextPane editor) {
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
            return parser;
        }
        catch(Exception e) {}
        return null;
    }
    public void validateString(int index,JTextPane editor,JTextPane console) {
        IconFile currentFile = pjs.get(index);
        if(currentFile.treesM == null) {
            console.setText("EXREGAN: " + currentFile.name + "\n-> Aún no hay autómatas creados para validar cadenas.");
            return;
        }
        Parser currentParser = getExpresions(index,editor);
        if(currentParser.isSuccessExecution()) {
            if(currentParser.getExpressions().size() > 0) {
                String consoleOut = "EXREGAN: " + currentFile.name;
                TreeMethod currenTreeMethod;
                currentFile.json = "[";
                ArrayList<Expression> expressions = currentParser.getExpressions();
                Expression expression;
                for(int i = 0; i < expressions.size(); i ++) {
                    try {
                        expression = expressions.get(i);
                        currenTreeMethod = currentFile.treesM.get(expression.id);
                        consoleOut += "\n-> " + (
                            currenTreeMethod != null
                            ? currentFile.treesM.get(expression.id).validateString(expression.string.substring(1,expression.string.length() - 1))
                            : "No se declaró la expresión regular \"" + expression.id + "\"."
                        );
                        currentFile.json += currentFile.treesM.get(expression.id).getJSON();
                        if(i < expressions.size() - 1) currentFile.json += ",";
                    }
                    catch(Exception e) {}
                }
                currentFile.json += "\n]";
                buildJSON("SALIDAS_201908355","out_" + index + "_" + currentFile.name.replace(".olc",""),currentFile.json);
                console.setText(consoleOut);
                return;
            }
            console.setText("EXREGAN: " + currentFile.name + "\n-> Sin cadenas para analizar.");
            return;
        }
        console.setText("EXREGAN:\n" + currentParser.getStrErrors());
    }
    private void buildJSON(String type,String name,String content) {
        try {
            File file = new File(type);
            if(!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream(type + "/" + name + ".json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (Exception e) {}
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
        } catch (Exception e) {}
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
            return "java.io.FileNotFoundException";
        }
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