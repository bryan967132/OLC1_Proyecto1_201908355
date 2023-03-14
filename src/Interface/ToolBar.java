package Interface;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import Controller.Controller;
import Templates.Colors;
import Templates.Button;
import Templates.FunctionButton;
public class ToolBar extends JPanel implements MouseListener {
    Button newOLC,openOLC,saveAsOLC;
    Controller controller;
    FunctionButton close,minimize;
    IDE ide;
    JFileChooser file;
    JPanel div;
    Window w;
    public ToolBar(Controller controller,IDE ide,Window w) {
        this.w = w;
        this.ide = ide;
        this.controller = this.ide.controller;
        init();
        addOpenOLC();
        addNewOLC();
        addSaveAsOLC();
        addMinimizeButton();
        addCloseButton();
        addDivisor();
    }
    private void addOpenOLC() {
        openOLC = new Button("Abrir");
        openOLC.locationSize(20,5,50,25);
        openOLC.text(Colors.WHITE,14);
        openOLC.setDesign(Colors.MEDIUMECLIPSE1);
        openOLC.setHoverColor(Colors.MEDIUMECLIPSE2);
        openOLC.addMouseListener(this);
        this.add(openOLC);
    }
    private void addNewOLC() {
        newOLC = new Button("Nuevo");
        newOLC.locationSize(72,5,60,25);
        newOLC.text(Colors.WHITE,14);
        newOLC.setDesign(Colors.MEDIUMECLIPSE1);
        newOLC.setHoverColor(Colors.MEDIUMECLIPSE2);
        newOLC.addMouseListener(this);
        this.add(newOLC);
    }
    private void addSaveAsOLC() {
        saveAsOLC = new Button("Guardar Como");
        saveAsOLC.locationSize(134,5,110,25);
        saveAsOLC.text(Colors.WHITE,14);
        saveAsOLC.setDesign(Colors.MEDIUMECLIPSE1);
        saveAsOLC.setHoverColor(Colors.MEDIUMECLIPSE2);
        saveAsOLC.addMouseListener(this);
        this.add(saveAsOLC);
    }
    private void init() {
        this.setLayout(null);
        this.setBackground(Colors.MEDIUMECLIPSE1);
        this.setVisible(true);
    }
    private void addDivisor() {
        div = new JPanel();
        div.setBackground(Colors.MEDIUMECLIPSE2);
        div.setBounds(0,35,1390,5);
        div.setVisible(true);
        this.add(div);
    }
    private void addCloseButton() {
        close = new FunctionButton();
        close.locationSize(1331,0,45,35);
        close.text("×",25);
        close.setHoverColor(Colors.RED);
        close.addMouseListener(this);
        this.add(close);
    }
    private void addMinimizeButton() {
        minimize = new FunctionButton();
        minimize.locationSize(1286,0,45,35);
        minimize.text("─",20);
        minimize.setHoverColor(Colors.LIGHTECLIPSE);
        minimize.addMouseListener(this);
        this.add(minimize);
    }
    private void chooseFile() {
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
		}
        this.file = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.olc)", "olc");
        file.setFileFilter(filtro);
        int seleccion = file.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File olcFile = file.getSelectedFile();
            int index = controller.existPJFile(olcFile.getAbsolutePath());
            if(index == -1) {
                controller.pjs.add(new IconFile(controller.countPJ(),olcFile,ide,controller));
                ide.lookPJFiles();
                controller.pjs.get(controller.countPJ() - 1).lookCode();
            }
            else {
                controller.pjs.get(index).lookCode();
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == openOLC) {
            chooseFile();
        }
        else if(e.getSource() == newOLC) {
            
        }
        else if(e.getSource() == saveAsOLC) {
            
        }
        else if(e.getSource() == close) {
            System.exit(0);
        }
        else if(e.getSource() == minimize) {
            w.setState(1);
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}