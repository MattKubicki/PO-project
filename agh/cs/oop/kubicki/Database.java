package agh.cs.oop.kubicki;

import agh.cs.oop.kubicki.loaders.HTMLLoadAndParse;
import agh.cs.oop.kubicki.loaders.JSONLoadAndParse;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.*;

public class Database {
    private Map <String, Verdicts> verdictsMap;
    private String jsonsPath;
    private String htmlsPath;

    public Database(String jsonsPath, String htmlsPath) {
        verdictsMap = new HashMap<>();
        getJsonsToDb(jsonsPath);
        getHtmlsToDb(htmlsPath);
    }

    public Map<String, Verdicts> getDatabase() {
        return verdictsMap;
    }

    private void getJsonsToDb(String path){
        JSONLoadAndParse getter = new JSONLoadAndParse(path, this);
    }

    private void getHtmlsToDb(String path){
        HTMLLoadAndParse getter = new HTMLLoadAndParse(path,this);
    }

    public void add(String signature, Verdicts verdict) {
        verdictsMap.put(signature, verdict);
    }

    public Verdicts search(String signature){
        Verdicts foundVerdict = verdictsMap.get(signature);
        if(foundVerdict == null) throw new NoSuchElementException();
        return foundVerdict;
    }
    public List<Verdicts> allVerdicts() {
        return new LinkedList<>(verdictsMap.values());
    }
}
