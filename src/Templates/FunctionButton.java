package Templates;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
public class FunctionButton extends JButton implements MouseListener {
	private static final long serialVersionUID = 1L;
	boolean activado = false;
	Color hoverColor;
	int x,y,w,mov,animSize,animSpeed;
	JLabel titulo;
	public FunctionButton() {
		this.addMouseListener(this);
		this.setBackground(null);
	}
	public void locationSize(int x,int y,int w,int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void text(String txt,int tamano) {
		this.setBorder(null);
		titulo = new Label(0,0,this.getWidth(),this.getHeight(),txt,tamano);
		this.add(titulo);
	}
	public void setHoverColor(Color color) {
		hoverColor = color;
	}
	public void mouseEntered(MouseEvent e) {
        this.setBackground(hoverColor);
	}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
		this.setBackground(null);
	}
}