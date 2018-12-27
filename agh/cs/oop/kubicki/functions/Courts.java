package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.verdicts.elements.CourtType;
import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Courts extends AbstractFunction {
    private List<Verdicts> verdicts;

    public Courts(Database database, int argsRequired) {
        super(database, argsRequired);
        this.verdicts = database.allVerdicts();
    }

    @Override
    public String execute(List<String> list) {
        SortedMap<CourtType, Integer> testMap3 = verdictsByCourtType();
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<CourtType, Integer> entry : testMap3.entrySet()) {
            CourtType key = entry.getKey();
            Integer value = entry.getValue();
            result.append(key).append("---").append(value).append("\n");
        }
        return result.toString();
    }

    private SortedMap<CourtType,Integer> verdictsByCourtType(){
        SortedMap<CourtType, Integer> judgmentsByCTCount = new TreeMap<>();
        for(Verdicts ver: verdicts){
            judgmentsByCTCount.merge(ver.getCourtType(), 1, (a, b) -> a + b);
        }
        for (CourtType courtType: CourtType.values()){
            judgmentsByCTCount.putIfAbsent(courtType, 0);
        }
        return judgmentsByCTCount;
    }
}
