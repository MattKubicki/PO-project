package agh.cs.oop.kubicki.functions;

import agh.cs.oop.kubicki.Database;

import java.util.List;

public class Help extends AbstractFunction{
    public Help(Database database, int argsRequired) {
        super(database, argsRequired);
    }

    @Override
    public String execute(List<String> list) {
        StringBuilder result = new StringBuilder("");
        result.append("content").append("---").append("wyświetla uzasadnienie konkretnego wyroku")
                .append(" wymaga argumentu - numer sygnatury").append("\n");
        result.append("courts").append("---").append("wyświetla rozkład statystyczny ze względu na typ sądu ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("exit").append("---").append("kończy działanie programu, ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("help").append("---").append("wyświetla pomoc, ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("judge").append("---").append("wyświetla liczbę orzeczeń dla wybranego sędziego ")
                .append("wymaga argumentu - nazwiska sędziego").append("\n");
        result.append("judges").append("---").append("wyświetla 10 sędziów, którzy wydali najwięcej orzeczeń ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("jury").append("---").append("wyświetla rozkład statystyczny liczby sędziów przypadających na orzeczenie ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("months").append("---").append("wyświetla liczbę orzeczeń w poszczególnych miesiącach ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("regulations").append("---").append("wyświetla 10 najczęściej przywoływanych ustaw ")
                .append("nie wymaga dodatkowych argumentów").append("\n");
        result.append("rubrum").append("---").append("wyświetla metryki jednego lub wielu orzeczeń ")
                .append("wymaga jednego lub wielu argumentów - sygnatur oddzielonych przecinkami").append("\n");
        return result.toString();
    }
}
