package Interface;
import javax.swing.JFrame;
import Controller.Controller;
import Templates.Colors;
public class Window extends JFrame {
    Controller controller;
    IDE ide;
    public Window(Controller controller) {
        super("ExReganUSAC");
        this.controller = controller;
        init();
        initComponents();
    }
    void initComponents() {
        ide = new IDE(this);
        this.getContentPane().setBackground(Colors.LIGHTECLIPSE);
        this.getContentPane().add(ide);
    }
    void init() {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setBounds(-10,0,1390,738);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}