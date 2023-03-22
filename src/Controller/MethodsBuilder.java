package Controller;
import java.util.Map;
import java.util.TreeMap;
import Interface.IDE;
import Interface.IconFile;
import Thompson.ThompsonMethod;
import TreeMethod.TreeMethod;
public class MethodsBuilder {
    Map<String,TreeMethod> strctsTree = new TreeMap<>();
    ThompsonMethod thompsonMethod;
    TreeMethod treeMethod;
    public void buildethods(IDE ide,int id,IconFile iconFile,Map<String,Set> sets,Map<String,Regex> regexs) {
        iconFile.treesM = new TreeMap<>();
        iconFile.thompsonsM = new TreeMap<>();
        Regex rgx;
        ide.regexCB.removeAllItems();        
        for(Map.Entry<String,Regex> regex : regexs.entrySet()) {
            rgx = regex.getValue();
            treeMethod = new TreeMethod(sets);
            treeMethod.setRegex(id,rgx);
            treeMethod.build();
            treeMethod.buildNextsTable();
            treeMethod.buildTransitionsTable();
            treeMethod.buildAFD();
            thompsonMethod = new ThompsonMethod();
            thompsonMethod.setRegex(id,rgx);
            thompsonMethod.build();
            thompsonMethod.buildAFND();
            iconFile.treesM.put(rgx.id,treeMethod);
            iconFile.thompsonsM.put(rgx.id,thompsonMethod);
            ide.regexCB.addItem(regex.getKey());
        }
        ide.regexCB.setSelectedItem(regexs.keySet().iterator().next());
    }
}