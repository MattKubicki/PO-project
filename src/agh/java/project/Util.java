package agh.java.project;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Util {
    private Database database;
    private List<Verdicts> verdicts;
    public Util(Database database) {
        this.database = database;
        this.verdicts = database.allVerdicts();
    }

    public List<String> getMetrics(List<String> signatures) {
        List<Verdicts> verdicts = new LinkedList<>();
        for(String signature : signatures){
            verdicts.add(database.search(signature));
        }
        List<String> metrics = new LinkedList<>();
        for(Verdicts ver : verdicts){
            metrics.add(ver.getRubrum());
        }
        return metrics;
    }

    public String getReasons(String signature){
        return database.search(signature).getReason();
    }

    public int judgmentsByJudgeNo(String name){
        int counter = 0;
        List<Verdicts> verdicts = database.allVerdicts();
        for (Verdicts ver : verdicts){
            List<Judge> judges = ver.getJudges();
            for (Judge j : judges){
                if (j.getName().equals(name))
                    counter++;
            }
        }
        return counter;
    }
    public LinkedHashMap<String,Integer> top10ByVerdicts(){
        Map<String, Integer> judgeCount = new HashMap<>();
        for (Verdicts ver : verdicts){
            List<Judge> judges = ver.getJudges();
            List<String> names = new LinkedList<>();
            for (Judge j : judges){
                names.add(j.getName());
            }
            for (String name : names){
                if (judgeCount.get(name) == null){
                    judgeCount.put(name,1);
                } else {
                    judgeCount.put(name, judgeCount.get(name) + 1);
                }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(judgeCount.entrySet());
        Comparator<Map.Entry<String,Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
        Collections.sort(list,comparator.reversed());
        list = list.subList(0,10);
        LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    public SortedMap<Month, Integer> verdictsByMonth(){
        SortedMap<Month, Integer> judgmentsByMonthCount = new TreeMap<>();
        for (Verdicts ver : verdicts){
            String result = ver.getJudgmentDate().substring(5,7);
            Integer res = Integer.valueOf(result);
            Month month = Month.of(res);
            judgmentsByMonthCount.merge(month, 1, (a, b) -> a + b);
        }/* lambda <=> :
            if (judgmentsByMonthCount.get(res) == null){
                judgmentsByMonthCount.put(res,1);
            } else {
                judgmentsByMonthCount.put(res, judgmentsByMonthCount.get(res) + 1);
            }
        }*/
        for (int i=1; i<13; i++){
            judgmentsByMonthCount.putIfAbsent(Month.of(i), 0);
        }
        return judgmentsByMonthCount;
    }

    public SortedMap<CourtType,Integer> verdictsByCourtType(){
        SortedMap<CourtType, Integer> judgmentsByCTCount = new TreeMap<>();
        for(Verdicts ver: verdicts){
            judgmentsByCTCount.merge(ver.getCourtType(), 1, (a, b) -> a + b);
        }
        for (CourtType courtType: CourtType.values()){
            judgmentsByCTCount.putIfAbsent(courtType, 0);
        }
        return judgmentsByCTCount;
    }

    public LinkedHashMap<String,Integer> top10CalledRefRegs(){
        Map<String, Integer> cCCount = new HashMap<>();
        for (Verdicts ver : verdicts){
            List<ReferencedRegulations> regulationsList = ver.getReferencedRegulations();
            List<String> journalTitles = new LinkedList<>();
            for (ReferencedRegulations r : regulationsList) {
                journalTitles.add(r.getJournalTitle());
            }
            for(String jT : journalTitles){
                if (cCCount.get(jT) == null){
                    cCCount.put(jT,1);
                } else {
                    cCCount.put(jT, cCCount.get(jT) + 1);
                }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<>(cCCount.entrySet());
        Comparator<Map.Entry<String,Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
        Collections.sort(list,comparator.reversed());
        list = list.subList(0,10);
        LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result; //zrobic abstrakty laczac z "II"
    }

    public double avgJudgesNoByVerdict(){
        int allJudges = 0;
        int allVerdicts = verdicts.size();
        for(Verdicts ver: verdicts){
            allJudges += ver.getJudges().size();
        }
        return (double)allJudges / (double) allVerdicts;
    }
}
