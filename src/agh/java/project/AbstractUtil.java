package agh.java.project;

import java.util.*;

public abstract class AbstractUtil {
    protected Database database;
    protected List<Verdicts> verdicts;
    public AbstractUtil(Database database) {
        this.database = database;
        this.verdicts = database.allVerdicts();
    }

    public LinkedHashMap<String,Integer> extractTop10(Map<String, Integer> count){
        List<Map.Entry<String,Integer>> list = new ArrayList<>(count.entrySet());
        Comparator<Map.Entry<String,Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
        Collections.sort(list,comparator.reversed());
        list = list.subList(0,10);
        LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
