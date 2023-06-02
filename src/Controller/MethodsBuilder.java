package Controller;
import java.util.Map;
import java.util.TreeMap;
import Components.*;
import Interface.IDE;
import Interface.IconFile;
import Thompson.ThompsonMethod;
import TreeMethod.TreeMethod;
import Tree.Node;
public class MethodsBuilder {
    Map<String,TreeMethod> strctsTree = new TreeMap<>();
    ThompsonMethod thompsonMethod;
    TreeMethod treeMethod;
    public void buildethods(IDE ide,int id,IconFile iconFile,Map<String,Set> sets,Map<String,Node> regexs) {
        iconFile.treesM = new TreeMap<>();
        iconFile.thompsonsM = new TreeMap<>();
        Node rgx;
        ide.regexCB.removeAllItems();        
        for(Map.Entry<String,Node> regex : regexs.entrySet()) {
            rgx = regex.getValue();
            treeMethod = new TreeMethod(sets,regex.getKey());
            treeMethod.setRegex(id,rgx);
            treeMethod.build();
            treeMethod.buildNextsTable();
            treeMethod.buildTransitionsTable();
            treeMethod.buildAFD();
            thompsonMethod = new ThompsonMethod(id,regex.getKey());
            thompsonMethod.setRegex(id,rgx);
            thompsonMethod.build();
            thompsonMethod.buildAFND();
            iconFile.treesM.put(regex.getKey(),treeMethod);
            iconFile.thompsonsM.put(regex.getKey(),thompsonMethod);
            ide.regexCB.addItem(regex.getKey());
        }
        ide.regexCB.setSelectedItem(regexs.keySet().iterator().next());
    }
}