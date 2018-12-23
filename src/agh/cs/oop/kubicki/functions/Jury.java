package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.HashMap;
import java.util.List;

public class Jury extends AbstractFunction{
    private List<Verdicts> verdicts;

    public Jury(Database database, int argsRequired) {
        super(database, argsRequired);
        verdicts = database.allVerdicts();
    }

    @Override
    public String execute(List<String> list) {
        HashMap<Integer, Integer> resMap = juryMembersCount();
        StringBuilder result = new StringBuilder("");
        for (int i=0; i<16;i++){
            result.append("Skład ").append(i).append(" - osobowy --- ").append(resMap.get(i)).append("\n");
        }
        result.append("Liczba sedziów przypadająca na orzeczenie --- ").append(avgJudgesNoByVerdict());
        return result.toString();
    }

    private HashMap<Integer, Integer> juryMembersCount() {
        HashMap<Integer, Integer> result = new HashMap<>();
        for (int i=0; i<16;i++){ //possible number of jury members in polish courts
            result.put(i,0);
        }
        for(Verdicts ver: verdicts){
            int size = 0;
            if (ver.getJudges()!=null){ //no judges case
                size = ver.getJudges().size();
            }
            int value = result.get(size);
            value++;
            result.put(size,value);
        }

        return result;
    }

    private double avgJudgesNoByVerdict(){
        int allJudges = 0;
        int allVerdicts = verdicts.size();
        for(Verdicts ver: verdicts){
            if (ver.getJudges() == null) continue;
            allJudges += ver.getJudges().size();
        }
        return (double)allJudges / (double) allVerdicts;
    }
}
