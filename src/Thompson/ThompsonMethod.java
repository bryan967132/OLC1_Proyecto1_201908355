package Thompson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import Controller.Regex;
public class ThompsonMethod {
    private Regex regex;
    private Thompson thompson;
    private int id;
    public void setRegex(int id,Regex regex) {
        this.id = id;
        this.regex = regex;
        this.thompson = new Thompson(regex);
    }
    public void build() {
        thompson.build();
    }
    public void buildAFND() {
        exportGraph(id + "_" + regex.id,thompson.getDot(regex.id),"AFND");
        buildPNG("AFND","AFND_201908355",id + "_" + regex.id);
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
    private void buildPNG(String typeDot,String typePng,String name) {
        try {
            File file = new File(typePng);
            if(!file.exists()) {
                file.mkdirs();
            }
            String graphviz_path = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String dot_path = "Dot/" + typeDot + "/" + name + ".dot";
            String png_path =  typePng + "/" + name + ".png" ;
            ProcessBuilder pBuilder = new ProcessBuilder(graphviz_path, "-Tpng", "-o", png_path, dot_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}