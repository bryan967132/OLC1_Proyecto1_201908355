package Controller;
import java.util.Map;
import java.util.TreeMap;
import Interface.IconFile;
import Thompson.ThompsonMethod;
import TreeMethod.TreeMethod;
public class MethodsBuilder {
    Map<String,TreeMethod> strctsTree = new TreeMap<>();
    ThompsonMethod thompsonMethod;
    TreeMethod treeMethod;
    public void buildethods(int id,IconFile iconFile,Map<String,Set> sets,Map<String,Regex> regexs) {
        iconFile.treesM = new TreeMap<>();
        iconFile.thompsonsM = new TreeMap<>();
        for(Map.Entry<String,Regex> regex : regexs.entrySet()) {
            treeMethod = new TreeMethod(sets);
            treeMethod.setRegex(id,regex.getValue());
            treeMethod.build();
            treeMethod.buildNextsTable();
            treeMethod.buildTransitionsTable();
            treeMethod.buildAFD();
            thompsonMethod = new ThompsonMethod();
            thompsonMethod.setRegex(id,regex.getValue());
            thompsonMethod.build();
            thompsonMethod.buildAFND();
            iconFile.treesM.put(regex.getKey(),treeMethod);
            iconFile.thompsonsM.put(regex.getKey(),thompsonMethod);
        }
    }
}