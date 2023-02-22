package Templates;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
public class Button extends JButton implements MouseListener,Runnable {
	private static final long serialVersionUID = 1L;
	boolean activado = false;
	int x,y,w,mov,animSize,animSpeed;
	String texto;
	JLabel titulo;
	public Button(String texto) {
		this.texto = texto;
	}
	public void locationSize(int x,int y,int w,int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.setBounds(x,y,w,h);
		this.setLayout(null);
		this.setVisible(true);
	}
	public void text(Color color,int tamano) {
		titulo = new Label(0,0,this.getWidth(),this.getHeight(),texto,color,tamano);
		this.add(titulo);
	}
	public void setDesign(int grosorBorde,Color colorBorde,Color colorFondo) {
		this.setBackground(colorFondo);
		this.setBorder(BorderFactory.createLineBorder(colorBorde,grosorBorde));
	}
	public void animate(int animSize,int animSpeed) {
		this.addMouseListener(this);
		this.animSize = animSize;
		this.animSpeed = animSpeed;
		activado = true;
	}
	public void run() {
		try {
			int a = this.getWidth();
			int h = this.getHeight();
			if(a <= w) {
				for(int i = 1; i <= this.animSize; i ++) {
					a += i;
					h += i;
					this.setBounds(x + w / 2 - a / 2,y - i,a,h);
					titulo.setSize(a,h);
					Thread.sleep(animSpeed);
				}
				for(int i = this.animSize; i >= 1; i --) {
					a -= i;
					h -= i;
					setBounds(x + w / 2 - a / 2,y - i,a,h);
					titulo.setSize(a,h);
					Thread.sleep(animSpeed);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void mouseEntered(MouseEvent e) {
        if(activado) {
            Thread hilo = new Thread(this);
            hilo.start();
        }
	}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}