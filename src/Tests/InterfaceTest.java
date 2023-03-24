package Tests;
import Controller.Controller;
import Interface.Window;
public class InterfaceTest {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Window w = new Window(controller);
        w.setVisible(true);
    }
}