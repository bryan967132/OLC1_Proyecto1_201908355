package Interface;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import Templates.Colors;
import Templates.FunctionButton;
public class ToolBar extends JPanel implements MouseListener {
    FunctionButton close,minimize;
    FunctionButton newOLC,openOLC,saveAsOLC;
    IDE ide;
    JPanel div;
    Window w;
    public ToolBar(Window w) {
        this.w = w;
        this.ide = this.w.ide;
        init();
        addOpenOLC();
        addNewOLC();
        addSaveAsOLC();
        addMinimizeButton();
        addCloseButton();
        addDivisor();
    }
    private void addOpenOLC() {
        openOLC = new FunctionButton();
        openOLC.locationSize(20,5,50,25);
        openOLC.text("Abrir",14);
        openOLC.setHoverColor(Colors.MEDIUMECLIPSE2);
        openOLC.addMouseListener(this);
        this.add(openOLC);
    }
    private void addNewOLC() {
        newOLC = new FunctionButton();
        newOLC.locationSize(72,5,60,25);
        newOLC.text("Nuevo",14);
        newOLC.setHoverColor(Colors.MEDIUMECLIPSE2);
        newOLC.addMouseListener(this);
        this.add(newOLC);
    }
    private void addSaveAsOLC() {
        saveAsOLC = new FunctionButton();
        saveAsOLC.locationSize(134,5,110,25);
        saveAsOLC.text("Guardar Como",14);
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
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == close) {
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