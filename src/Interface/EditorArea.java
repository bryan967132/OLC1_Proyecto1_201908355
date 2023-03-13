package Interface;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import Templates.Colors;
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
        editor = new JTextPane() {
            public void paint(Graphics g) {
                super.paint(g);
                EditorArea.this.repaint();
            }
        };
        editor.setEditorKit(
            new StyledEditorKit() {
                public ViewFactory getViewFactory() {
                    return new NoWrapViewFactory();
                }
            }
        );
        editor.setForeground(Colors.WHITE);
        editor.setBackground(Colors.DARKECLIPSE);
        editor.setFont(new java.awt.Font("Consolas", 0, 11));
        editor.setCaretColor(Colors.WHITE);
        editor.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        editor.setBorder(BorderFactory.createEmptyBorder());
        //scroll
        scroll = new JScrollPane(editor);
        scroll.setBorder(null);
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
class NoWrapEditorKit extends DefaultEditorKit {
    private static final long serialVersionUID = 1L;
    public ViewFactory getViewFactory() {
        return new NoWrapViewFactory();
    }
}
class NoWrapViewFactory implements ViewFactory {
    public View create(Element element) {
        String kind = element.getName();
        if (kind != null) {
            if (kind.equals(AbstractDocument.ContentElementName)) {
                return new LabelView(element);
            } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                return new NoWrapParagraphView(element);
            } else if (kind.equals(AbstractDocument.SectionElementName)) {
                return new BoxView(element, View.Y_AXIS);
            } else if (kind.equals(StyleConstants.ComponentElementName)) {
                return new ComponentView(element);
            } else if (kind.equals(StyleConstants.IconElementName)) {
                return new IconView(element);
            }
        }
        return new LabelView(element);
    }
}

class NoWrapParagraphView extends ParagraphView {
    public NoWrapParagraphView(Element elem) {
        super(elem);
    }
    public void layout(int width, int height) {
        super.layout(Short.MAX_VALUE, height);
    }
    public float getMinimumSpan(int axis) {
        return super.getPreferredSpan(axis);
    }
}