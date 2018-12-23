package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Months extends AbstractFunction {
    private List<Verdicts> verdicts;

    public Months(Database database, int argsRequired) {
        super(database, argsRequired);
        this.verdicts = database.allVerdicts();
    }

    @Override
    public String execute(List<String> list) {
        SortedMap<Month, Integer> resultMap = verdictsByMonth();
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<Month, Integer> entry : resultMap.entrySet()) {
            Month key = entry.getKey();
            Integer value = entry.getValue();
            result.append(key).append(" - ").append(value).append("\n");
        }
        return result.toString();
    }

    private SortedMap<Month, Integer> verdictsByMonth(){
        SortedMap<Month, Integer> judgmentsByMonthCount = new TreeMap<>();
        for (Verdicts ver : verdicts){
            String result = ver.getJudgmentDate().substring(5,7);
            Integer res = Integer.valueOf(result);
            Month month = Month.of(res);
            judgmentsByMonthCount.merge(month, 1, (a, b) -> a + b);
        }
        for (int i=1; i<13; i++){
            judgmentsByMonthCount.putIfAbsent(Month.of(i), 0);
        }
        return judgmentsByMonthCount;
    }
}
