package agh.java.project;

import java.util.*;

public class Database {

    private Map <String, Verdicts> verdictsMap;

    public Database() {
        verdictsMap = new HashMap<>();
    }



    public Map<String, Verdicts> getDatabase() {
        return verdictsMap;
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
