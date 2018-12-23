package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.elements.Judge;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Top10Judges extends AbstractTop10 {
    private List<Verdicts> verdicts;

    public Top10Judges(Database database, int argsRequired) {
        super(database, argsRequired);
        this.verdicts = database.allVerdicts();
    }
    private LinkedHashMap<String,Integer> top10ByVerdicts(){
        Map<String, Integer> judgeCount = new HashMap<>();
        for (Verdicts ver : verdicts){
            List<Judge> judges = ver.getJudges();
            if (judges == null)
                continue; //no judges case
            for (Judge j : judges){
                judgeCount.merge(j.getName(), 1, (a, b) -> a + b);
            }
        }
        return super.extractTop10(judgeCount);
    }
    @Override
    public String execute (List<String> list){
        LinkedHashMap<String,Integer> resultMap = top10ByVerdicts();
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            result.append(key).append(" ").append(value).append("\n");
        }
        return result.toString();
    }
}
