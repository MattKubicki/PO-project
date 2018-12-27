package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;

import java.util.List;

public abstract class AbstractFunction {
    protected Database database;
    int argsRequired;

    public AbstractFunction(Database database, int argsRequired) {
        this.database = database;
        this.argsRequired = argsRequired;
    }

    public int getNumberOfArgsRequired() {
        return argsRequired;
    }

    public abstract String execute(List<String> list);

}
