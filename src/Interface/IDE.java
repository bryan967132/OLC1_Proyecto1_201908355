package Interface;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java_cup.runtime.Symbol;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Colors.*;
import Controller.Controller;
import Templates.Button;
import Templates.Colors;
import Templates.Icons;
import Templates.Label;
public class IDE extends JPanel implements KeyListener,MouseWheelListener,MouseListener,MouseMotionListener  {
    ArrayList<Token> code;
    Controller controller;
    Button analyzeInput,analyzeStrings,saveOLC;
    double zoomFactor = 1.05; // factor de zoom
    EditorArea editorArea;
    Icon icono;
    ImageIcon image;
    int indexFilePJ = -1;
    int posCaret,posXImg,posYImg,posXLabImg,posYLabImg;
    JLabel cursorPosition;
    JLabel img;
    JPanel editorAreaContent;
    JPanel editorAreaContentFalse;
    JPanel graphics;
    JPanel projects;
    JTextPane console;
    String input;
    Symbol sym;
    Tag tag;
    ToolBar toolbar;
    Window w;
    WordPainter painter;
    public IDE(Window w) {
        this.w = w;
        this.controller = w.controller;
        init();
        initComponents();
        defineComponents();
        addComponents();
        addToolBar();
        cursorPosition();
        lookPJFiles();
    }
    void initComponents() {
        projects = new JPanel();
        editorAreaContent = new JPanel();
        cursorPosition = new JLabel();
        console = new JTextPane();
        graphics = new JPanel();
        analyzeInput = new Button();
        analyzeStrings = new Button();
        saveOLC = new Button();
    }
    void defineComponents() {
        //projects
        projects.setBackground(Colors.MEDIUMECLIPSE2);
        projects.setBounds(40,105,160,585);
        projects.setLayout(null);
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
        graphics.setLayout(null);

        img = new JLabel();
		image = new ImageIcon("ARBOLES_201908355/frase.png");
		icono = new ImageIcon(image.getImage().getScaledInstance(image.getIconWidth(),image.getIconHeight(), Image.SCALE_DEFAULT));
        img.setIcon(icono);
        img.setBounds(0,0,icono.getIconWidth(),icono.getIconHeight());
        graphics.add(img);
        graphics.addMouseListener(this);
        graphics.addMouseWheelListener(this);
        graphics.addMouseMotionListener(this);
        //analyzeInput
        analyzeInput.locationSize(440,56,30,30);
        analyzeInput.Icon(Icons.PLAY);
        analyzeInput.setDesign(Colors.GREEN2);
        analyzeInput.setHoverColor(Colors.GREEN3);
        analyzeInput.addMouseListener(this);
        //analyzeStrings
        analyzeStrings.locationSize(475,56,30,30);
        analyzeStrings.Icon(Icons.CHECK);
        analyzeStrings.setDesign(Colors.GREEN2);
        analyzeStrings.setHoverColor(Colors.GREEN3);
        analyzeStrings.addMouseListener(this);
        //saveOLC
        saveOLC.locationSize(510,56,30,30);
        saveOLC.Icon(Icons.SAVE);
        saveOLC.setDesign(Colors.GREEN2);
        saveOLC.setHoverColor(Colors.GREEN3);
        saveOLC.addMouseListener(this);
    }
    public void updateTag() {
        if(tag != null) {
            tag.removeAll();
			this.remove(tag);
		}
		tag = new Tag(indexFilePJ,this,controller);
        this.add(tag);
		this.repaint();
    }
    public void lookPJFiles() {
        projects.removeAll();
        projects.add(new Label(0,10,projects.getWidth(),25,"Proyectos",16));
        for(int i = 0; i < controller.countPJ(); i ++) {
            controller.pjs.get(i).locationSize(0,i * 25 + 40,this.projects.getWidth(),25);
            controller.pjs.get(i).setHoverColor(Colors.LIGHTECLIPSE);
            projects.add(controller.pjs.get(i));
        }
        projects.repaint();
    }
    void addComponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(cursorPosition);
        this.add(console);
        this.add(graphics);
        this.add(analyzeInput);
        this.add(analyzeStrings);
        this.add(saveOLC);
    }
    void addToolBar() {
        toolbar = new ToolBar(controller,this,w);
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
            controller.analyze(indexFilePJ,editorArea.editor,console);
        }
        catch (Exception e1) {}
    }
    void setFormat() {
        try {
            controller.setFormat(editorArea.editor);
        }
        catch (Exception e1) {}
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        setFormat();
    }
    public void keyReleased(KeyEvent e) {
        setFormat();
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
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {
            // zoom in
            zoomFactor *= 1.05;
        } else {
            // zoom out
            zoomFactor /= 1.05;
        }
        int w = image.getIconWidth();
        int h = image.getIconHeight();
        img.removeAll();
        icono = new ImageIcon(image.getImage().getScaledInstance((int) (w * zoomFactor),(int) (h * zoomFactor), Image.SCALE_DEFAULT));
        img.setIcon(icono);
        img.setSize(icono.getIconWidth(),icono.getIconHeight());
        graphics.revalidate();
        graphics.repaint();
    }
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - posXImg;
        int dy = e.getY() - posYImg;
        img.setLocation(posXLabImg + dx,posYLabImg + dy);
    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == analyzeInput) {
            if(indexFilePJ != -1) {
                execute();
            }
            else {
                console.setText("EXREGAN:\n->");
            }
        }
        else if(e.getSource() == saveOLC) {
            if(indexFilePJ != -1) {
                controller.saveOLCPJ(indexFilePJ,editorArea.editor);
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        posXImg = e.getX();
        posYImg = e.getY();
        posXLabImg = img.getX();
        posYLabImg = img.getY();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}