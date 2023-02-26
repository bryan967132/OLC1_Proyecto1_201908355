package Interface;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import Templates.FunctionButton;
import Colors.Colors;
public class ToolBar extends JPanel implements ActionListener {
    Window w;
    FunctionButton close,minimize;
    JPanel div;
    public ToolBar(Window w) {
        this.w = w;
        init();
        addDivisor();
        addCloseButton();
        addMinimizeButton();
    }
    void init() {
        this.setLayout(null);
        this.setBackground(Colors.MEDIUMECLIPSE1);
        this.setVisible(true);
    }
    void addDivisor() {
        div = new JPanel();
        div.setBackground(Colors.MEDIUMECLIPSE2);
        div.setBounds(0,35,1390,5);
        div.setVisible(true);
        this.add(div);
    }
    void addCloseButton() {
        close = new FunctionButton();
        close.locationSize(1331,0,45,35);
        close.text("×",25);
        close.setHoverColor(Colors.RED);
        close.addActionListener(this);
        this.add(close);
    }
    void addMinimizeButton() {
        minimize = new FunctionButton();
        minimize.locationSize(1286,0,45,35);
        minimize.text("─",20);
        minimize.setHoverColor(Colors.LIGHTECLIPSE);
        minimize.addActionListener(this);
        this.add(minimize);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == close) {
            System.exit(0);
        }
        else if(e.getSource() == minimize) {
            w.setState(1);
        }
    }
}