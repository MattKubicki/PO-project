package agh.java.project;

public enum JudgeRole {
    PRESIDING_JUDGE,
    REPORTING_JUDGE,
    REASONS_FOR_JUDGEMENT_AUTHOR;


    public String toString() {
        switch (this) {
            case PRESIDING_JUDGE:
                return "Przewodniczący";
            case REPORTING_JUDGE:
                return "Sprawozdawca";
            case REASONS_FOR_JUDGEMENT_AUTHOR:
                return "Autor uzasadnienia";
            default:
                return "-";
        }
    }
}
