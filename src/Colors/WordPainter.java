package Colors;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
public class WordPainter {
    public JTextPane box;
    private StyleContext sc;
    private StyledDocument doc;
    public WordPainter() {
        box = new JTextPane();
        sc = new StyleContext();
        doc = new DefaultStyledDocument(sc);
    }
    public void setStyle(String txt) {
        box.setDocument(doc);
        try {
            doc.insertString(doc.getLength(),txt,null);
        }
        catch(Exception e) {}
    }
    public void RW(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.KEYWORD);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void STRING(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.STRING);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void CHARACTER(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.CHARACTER);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void VARIABLE(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.VARIABLE);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void VARIABLEUSE(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.VARIABLEUSE);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void REGEX(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.REGEX);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void REGEXUSE(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.REGEXUSE);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void OPERATOR(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.OPERATOR);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void LIMIT(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.LIMIT);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void COMMENT(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.COMMENT);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
    public void ERROR(int startPos,int endPos) {
        Style style = sc.addStyle("ConstantWidth",null);
        StyleConstants.setForeground(style,Colors.COMMENT);
        StyleConstants.setStrikeThrough(style,true);
        doc.setCharacterAttributes(startPos,endPos,style,false);
    }
}