package Colors;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
public class WordPainter {
    public JTextPane box;
    private StyleContext sc;
    public StyledDocument doc;
    public WordPainter() {
        box = new JTextPane();
        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
    }
    public void setStyle(JTextPane editor) {
        doc = editor.getStyledDocument();
    }
    public void RW(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.KEYWORD);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void STRING(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.STRING);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void SPECIALCHAR(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.SPECIALCHAR);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void CHARACTER(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.CHARACTER);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void VARIABLE(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.VARIABLE);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void VARIABLEUSE(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.VARIABLEUSE);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void REGEX(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.REGEX);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void REGEXUSE(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.REGEXUSE);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void OPERATOR(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.OPERATOR);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void LIMIT(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.LIMIT);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void COMMENT(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.COMMENT);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void ERROR(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.COMMENT);
        StyleConstants.setStrikeThrough(attr,true);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
    public void DEFAULT(int startPos,int endPos) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setForeground(attr,Colors.WHITE);
        doc.setCharacterAttributes(startPos,endPos,attr,false);
    }
}