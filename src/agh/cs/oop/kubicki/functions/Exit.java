package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;

import java.util.List;

public class Exit extends AbstractFunction {
    public Exit(Database database, int argsRequired) {
        super(database, argsRequired);
    }

    @Override
    public String execute(List<String> list) {
        System.exit(0);
        return null;
    }
}
