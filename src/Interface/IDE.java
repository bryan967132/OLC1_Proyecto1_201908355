package Interface;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java_cup.runtime.Symbol;
import Colors.*;
import Templates.Button;
import Controller.Controller;
import java.awt.event.KeyListener;
public class IDE extends JPanel implements ActionListener,KeyListener {
    ArrayList<Token> code;
    Controller controller;
    Button analyzeInput;
    EditorArea editorArea;
    int posCaret;
    JLabel cursorPosition;
    JPanel editorAreaContent;
    JPanel editorAreaContentFalse;
    JPanel graphics;
    JPanel projects;
    JTextPane console;
    String input;
    Symbol sym;
    ToolBar toolbar;
    Window w;
    WordPainter painter;
    public IDE(Window w) {
        this.w = w;
        init();
        initComponents();
        defineComponents();
        addComponents();
        addToolBar();
        cursorPosition();
    }
    void initComponents() {
        controller = new Controller();
        projects = new JPanel();
        editorAreaContent = new JPanel();
        cursorPosition = new JLabel();
        console = new JTextPane();
        graphics = new JPanel();
        analyzeInput = new Button("►");
    }
    void defineComponents() {
        //projects
        projects.setBackground(Colors.LIGHTECLIPSE);
        projects.setBounds(40,105,160,585);
        //editorArea
        editorAreaContent.setLayout(new BorderLayout());
        editorAreaContent.setBorder(BorderFactory.createLineBorder(Colors.DARKECLIPSE,8));
        editorArea = new EditorArea();
        editorArea.editor.addKeyListener(this);
        editorAreaContent.add(editorArea,BorderLayout.WEST);
        editorAreaContent.add(editorArea.scroll,BorderLayout.CENTER);
        editorAreaContent.setBounds(220,105,550,425);
        //cursorPosition
        cursorPosition.setText("1 : 1");
        cursorPosition.setForeground(Colors.WHITE);
        cursorPosition.setBounds(220,535,550,10);
        cursorPosition.setHorizontalAlignment(JLabel.RIGHT);
        cursorPosition.setVerticalAlignment(JLabel.CENTER);
        //console
        console.setEditable(false);
        console.setForeground(Colors.WHITE);
        console.setBackground(Colors.DARKECLIPSE);
        console.setFont(new java.awt.Font("Consolas", 0, 11));
        console.setBounds(220,550,1120,140);
        console.setBorder(BorderFactory.createLineBorder(Colors.DARKECLIPSE,8));
        console.setText("EXREGAN:\n->");
        //graphics
        graphics.setBackground(Colors.WHITE);
        graphics.setBounds(790,105,550,425);
        graphics.setBorder(BorderFactory.createEmptyBorder());
        //analyzeInput
        analyzeInput.locationSize(220,56,30,30);
        analyzeInput.text(Colors.WHITE,15);
        analyzeInput.setDesign(Colors.GREEN2);
        analyzeInput.setHoverColor(Colors.GREEN3);
        analyzeInput.addActionListener(this);
    }
    void addComponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(cursorPosition);
        this.add(console);
        this.add(graphics);
        this.add(analyzeInput);
    }
    void cursorPosition() {
        editorArea.editor.addCaretListener(
            new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    try {
                        posCaret = e.getDot();
                        int row = 1,col = 1;
                        int lastRow = -1;
                        String text = editorArea.editor.getText().replaceAll("\r","");
                        for(int i = 0; i < posCaret; i ++) {
                            if(text.charAt(i) == '\n') {
                                row ++;
                                lastRow = i;
                            }
                        }
                        col = posCaret - lastRow;
                        cursorPosition.setText(row + " : " + col);
                    }
                    catch(Exception e1) {}
                }
            }
            );
        }
    void addToolBar() {
        toolbar = new ToolBar(w);
        toolbar.setBounds(0,0,1390,40);
        this.add(toolbar);
    }
    void init() {
        this.setBackground(Colors.MEDIUMECLIPSE1);
        this.setLayout(null);
    }
    void execute() {
        try {
            controller.setFormat(editorArea.editor);
            controller.analyze(editorArea.editor,console);
        }
        catch (Exception e1) {}
    }
    void setFormat() {
        try {
            controller.setFormat(editorArea.editor);
        }
        catch (Exception e1) {}
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == analyzeInput) {
            execute();
        }
    }
    public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        try {
            setFormat();
        }
        catch(Exception e1) {}
    }
    public void keyReleased(KeyEvent e) {
        try {
            setFormat();
        }
        catch(Exception e1) {}
    }
}