package Interface;
import javax.swing.JFrame;
public class Window extends JFrame {
    IDE ide;
    public Window() {
        super("ExReganUSAC");
        init();
        initComponents();
    }
    void initComponents() {
        ide = new IDE(this);
        this.getContentPane().add(ide);
    }
    void init() {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setBounds(-10,0,1390,738);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}