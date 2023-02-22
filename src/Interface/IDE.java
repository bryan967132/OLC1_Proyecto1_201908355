package Interface;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import Templates.Button;
import Templates.Colors;
public class IDE extends JPanel {
    Button analyzeInput;
    Button generateAFD;
    EditorArea editorArea;
    JLabel cursorPosition;
    JPanel editorAreaContent;
    JPanel graphics;
    JPanel projects;
    JTextPane console;
    ToolBar toolbar;
    Window w;
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
        console.setBounds(220,550,1120,140);
        console.setBorder(BorderFactory.createLineBorder(Colors.DARKECLIPSE,8));
        //graphics
        graphics.setBackground(Colors.WHITE);
        graphics.setBounds(790,105,550,425);
        graphics.setBorder(BorderFactory.createEmptyBorder());
        //analyzeInput
        analyzeInput.locationSize(220,56,30,30);
        analyzeInput.text(Colors.WHITE,15);
        analyzeInput.setDesign(Colors.GREEN2);
        analyzeInput.setHoverColor(Colors.GREEN3);
    }
    void addComponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(cursorPosition);
        this.add(console);
        this.add(graphics);
        this.add(analyzeInput);
        //this.add(generateAFD);
    }
    void cursorPosition() {
        editorArea.editor.addCaretListener(
            new CaretListener() {
                public void caretUpdate(CaretEvent e) {
                    int pos = e.getDot();
                    int row = 1,col = 1;
                    int lastRow = -1;
                    String text = editorArea.editor.getText().replaceAll("\r","");
                    for(int i = 0; i < pos; i ++) {
                        if(text.charAt(i) == 10) {
                            row ++;
                            lastRow = i;
                        }
                    }
                    col = pos - lastRow;
                    cursorPosition.setText(row + " : " + col);
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
}