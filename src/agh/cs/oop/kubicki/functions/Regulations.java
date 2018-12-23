package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.elements.ReferencedRegulations;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Regulations extends AbstractTop10{
    private List<Verdicts> verdicts;

    public Regulations(Database database, int argsRequired) {
        super(database, argsRequired);
        this.verdicts = database.allVerdicts();
    }

    @Override
    public String execute(List<String> list) {
        LinkedHashMap<String,Integer> mapResult = top10CalledRefRegs();
        StringBuilder result = new StringBuilder("");
        for (Map.Entry<String, Integer> entry : mapResult.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            result.append(value).append("---").append(key).append("\n");
        }
        return result.toString();
    }

    private LinkedHashMap<String,Integer> top10CalledRefRegs(){
        Map<String, Integer> cCCount = new HashMap<>();
        for (Verdicts ver : verdicts){
            List<ReferencedRegulations> regulationsList = ver.getReferencedRegulations();
            for (ReferencedRegulations r : regulationsList) {
                if(r.getJournalTitle().equals("")) continue; //don't want to have "" as top regulation
                cCCount.merge(r.getJournalTitle(), 1, (a, b) -> a + b);
            }
        }
        return super.extractTop10(cCCount);
    }
}
