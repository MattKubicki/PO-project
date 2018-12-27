package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.util.LinkedList;
import java.util.List;

public class Rubrum extends AbstractFunction {
    public Rubrum(Database database, int argsRequired) {
        super(database, argsRequired);
    }

    @Override
    public String execute(List<String> list) {
        StringBuilder result = new StringBuilder("");
        List<String> rubrumList = getRubrum(list);
        for (int i=0; i<rubrumList.size(); i++)
            result.append(getRubrum(list).get(i));
        return result.toString();
    }

    private List<String> getRubrum(List<String> signatures) {
        List<Verdicts> verdicts = new LinkedList<>();
        for(String signature : signatures){
            verdicts.add(database.search(signature));
        }
        List<String> rubrum = new LinkedList<>();
        for(Verdicts ver : verdicts){
            rubrum.add(ver.getRubrum());
        }
        return rubrum;
    }
}
