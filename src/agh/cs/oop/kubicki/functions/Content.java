package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;

import java.util.List;

public class Content extends AbstractFunction {
    public Content(Database database, int argsRequired) {
        super(database, argsRequired);
    }

    @Override
    public String execute(List<String> list) {
        StringBuilder result = new StringBuilder("");
        try {
            result.append("Uzasadnienie wyroku ").append((list.get(0))).append(":").append("\n").
                    append(getReasons(list.get(0)));
        }
        catch (Exception ex){
            result.append("Nie znaleziono! Być może podano błędną sygnaturę").append("\n");
        }
        return result.toString();
    }

    private String getReasons(String signature) {
           return database.search(signature).getReason();
    }
}
