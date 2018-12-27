package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.elements.Judge;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.List;

public class JudgmentByJudge extends AbstractFunction {
    private List<Verdicts> verdicts;
    public JudgmentByJudge(Database database, int argsRequired) {
        super(database, argsRequired);
        this.verdicts = database.allVerdicts();
    }

    @Override
    public String execute(List<String> list) {
        StringBuilder result = new StringBuilder("");
        if (judgmentsByJudgeNo(list.get(0))!=0)
            result.append("Sędzia wziął udział w ").append(judgmentsByJudgeNo(list.get(0))).append(" ").append("orzeczeniach.");
        else
            result.append("Sędzia nie wziął udział w żadnym orzeczeniu. Być może został wpisany błędny argument");
        return result.toString();
    }

    private int judgmentsByJudgeNo(String name){
        int counter = 0;
        for (Verdicts ver : verdicts){
            List<Judge> judges = ver.getJudges();
            if (judges == null) continue;
            for (Judge j : judges){
                if (j.getName().equals(name))
                    counter++;
            }
        }
        return counter;
    }
}
