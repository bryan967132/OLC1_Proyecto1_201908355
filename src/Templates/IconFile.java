package Templates;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class IconFile extends JPanel implements MouseListener {
    private static final long serialVersionUID = 1L;
	boolean activado = false;
	Color backgroundColor,hoverColor,tmpColor;
	String texto;
	public IconFile() {
        this.setBackground(null);
    }
	public IconFile(String texto) {
		this.texto = texto;
        this.setBackground(null);
	}
	public void locationSize(int x,int y,int w,int h) {
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void text(Color color,int tamano) {
        Icon();
		this.add(new PJName(50,0,this.getWidth() - 70,this.getHeight(),texto,tamano));
	}
    public void text(String texto,Color color,int tamano) {
        Icon();
		this.add(new PJName(50,0,this.getWidth() - 70,this.getHeight(),texto,tamano));
	}
	private void Icon() {
		this.add(new IconImage(Icons.FILE1,15,3,this.getHeight() - 6,this.getHeight() - 6));
	}
	public void setHoverColor(Color color) {
		hoverColor = color;
		this.addMouseListener(this);
	}
	public void mouseEntered(MouseEvent e) {
        this.setBackground(hoverColor);
	}
	public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2) {
            System.out.println("DOBLE CLICK");
        }
    }
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {
        this.setBackground(null);
	}
}
class PJName extends JLabel {
	private static final long serialVersionUID = 1L;
	public PJName(int x,int y,int w,int h,String txt,int fuente) {
		this.setText(txt);
		this.setForeground(Colors.WHITE);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.PLAIN,fuente));
		this.setHorizontalAlignment(JLabel.LEFT);
		this.setVerticalAlignment(JLabel.CENTER);
	}
}