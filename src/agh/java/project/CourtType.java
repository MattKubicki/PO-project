package agh.java.project;

public enum CourtType {
    CONSTITUTIONAL_TRIBUNAL,
    NATIONAL_APPEAL_CHAMBER,
    SUPREME,
    ADMINISTRATIVE,
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
            case ADMINISTRATIVE:
                return "Sąd Administracyjny";
            case COMMON:
                return "Sąd Powszechny";
            default:
                return "";
        }
    }
}
