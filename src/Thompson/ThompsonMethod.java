package Thompson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import Tree.Node;
public class ThompsonMethod {
    private Thompson thompson;
    private int id;
    private String name;
    public ThompsonMethod(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setRegex(int id, Node regex) {
        this.id = id;
        this.thompson = new Thompson(regex);
    }
    public void build() {
        thompson.build();
    }
    public void buildAFND() {
        exportGraph("afnd_" + id + "_" + name, thompson.getDot(name), "AFND");
        buildPNG("AFND", "AFND_201908355", "afnd_" + id + "_" + name);
    }
    private void exportGraph(String id, String content, String type) {
        try {
            File file = new File("Dot/" + type);
            if(!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream outputStream = new FileOutputStream("Dot/" + type + "/" + id + ".dot");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(content);
            outputStreamWriter.close();
            outputStream.close();
        }
        catch(IOException e) {}
    }
    private void buildPNG(String typeDot, String typePng, String name) {
        try {
            File file = new File("Data/" + typePng);
            if(!file.exists()) {
                file.mkdirs();
            }
            String graphviz_path = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            String dot_path = "Dot/" + typeDot + "/" + name + ".dot";
            String png_path = "Data/" + typePng + "/" + name + ".png" ;
            ProcessBuilder pBuilder = new ProcessBuilder(graphviz_path, "-Tpng", "-o", png_path, dot_path);
            pBuilder.redirectErrorStream(true);
            pBuilder.start();
        } catch (Exception e) {}
    }
}