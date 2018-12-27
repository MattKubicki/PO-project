package agh.cs.oop.kubicki.verdicts.elements;

public enum CourtType {
    CONSTITUTIONAL_TRIBUNAL,
    NATIONAL_APPEAL_CHAMBER,
    REGIONAL_ADMINISTRATIVE,
    SUPREME,
    SUPREME_ADMINISTRATIVE,
    COMMON;

    @Override
    public String toString() {
        switch (this){
            case CONSTITUTIONAL_TRIBUNAL:
                return "Trybunał Konstytucyjny";
            case SUPREME:
                return "Sąd Najwyższy";
            case NATIONAL_APPEAL_CHAMBER:
                return "Krajowa Izba Odwoławcza";
            case SUPREME_ADMINISTRATIVE:
                return "Naczelny Sąd Administracyjny";
            case REGIONAL_ADMINISTRATIVE:
                return "Wojewódzki Sąd Administracyjny";
            case COMMON:
                return "Sąd Powszechny";
            default:
                return "";
        }
    }
}
