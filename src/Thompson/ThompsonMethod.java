package Thompson;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import Controller.Regex;
public class ThompsonMethod {
    private Regex regex;
    private Thompson thompson;
    public void setRegex(Regex regex) {
        this.regex = regex;
        this.thompson = new Thompson(regex);
    }
    public void build() {
        thompson.build();
    }
    public void buildAFND() {
        exportGraph(regex.id,thompson.getDot(),"AFN");
    }
    public void exportGraph(String id,String content,String file) {
        try {
            FileOutputStream outputStream = new FileOutputStream("Dot/" + file + "/" + id + ".dot");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}