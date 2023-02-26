package Interface;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import Colors.Colors;
public class EditorArea extends JPanel {
    JTextPane editor;
    JScrollPane scroll;
    public EditorArea() {
        setAlignmentX(RIGHT_ALIGNMENT);
        setForeground(Colors.WHITE);
        setBackground(Colors.DARKECLIPSE);
        setMinimumSize(new Dimension (30, 30));
        setPreferredSize(new Dimension (30, 30));
        setMinimumSize(new Dimension (30, 30));
        //editor
        editor = new JTextPane(){
            public void paint(Graphics g) {
                super.paint(g);
                EditorArea.this.repaint();
            }
        };
        editor.setForeground(Colors.WHITE);
        editor.setBackground(Colors.DARKECLIPSE);
        editor.setFont(new java.awt.Font("Consolas", 0, 11));
        editor.setCaretColor(Colors.WHITE);
        editor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        editor.setBorder(BorderFactory.createEmptyBorder());
        //scroll
        scroll = new JScrollPane(editor);
        scroll.setOpaque(false);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
    }
    public void paint(Graphics g) {
        super.paint(g);
        int start = editor.viewToModel(scroll.getViewport().getViewPosition());
        int end = editor.viewToModel(
            new Point(
                scroll.getViewport().getViewPosition().x + editor.getWidth(),
                scroll.getViewport().getViewPosition().y + editor.getHeight()
            )
        );
        Document doc = editor.getDocument();
        int startline = doc.getDefaultRootElement().getElementIndex(start);
        int endline = doc.getDefaultRootElement().getElementIndex(end) + 1;
        int fontSize = g.getFontMetrics(editor.getFont()).getHeight();
        for(int line = startline, y = 0; line <= endline; line ++, y += fontSize) {
            g.drawString(Integer.toString(line),0,y);
        }
    }
}