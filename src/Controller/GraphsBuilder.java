package Controller;
import java.util.Map;
import java.util.TreeMap;
import Interface.IconFile;
import Thompson.ThompsonMethod;
import TreeMethod.TreeMethod;
public class GraphsBuilder {
    Map<String,TreeMethod> strctsTree = new TreeMap<>();
    ThompsonMethod thompson;
    TreeMethod treeMethod;
    public void buildTreeMethod(int id,IconFile iconFile,Map<String,Set> sets,Map<String,Regex> regexs) {
        thompson = new ThompsonMethod();
        treeMethod = new TreeMethod(sets);
        for(Map.Entry<String,Regex> regex : regexs.entrySet()) {
            System.out.println(regex.getValue());
            thompson.setRegex(id,regex.getValue());
            thompson.build();
            thompson.buildAFND();
            treeMethod.setRegex(id,regex.getValue());
            treeMethod.build();
            treeMethod.buildNextsTable();
            treeMethod.buildTransitionsTable();
            treeMethod.buildAFD();
        }
    }
    private void buildDot(String typeDot,String typePng,String name) {
        try {
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