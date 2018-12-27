package agh.cs.oop.kubicki.verdicts.elements;

public enum JudgeRole {
    PRESIDING_JUDGE,
    REPORTING_JUDGE,
    REASONS_FOR_JUDGEMENT_AUTHOR,
    NONE;


    public String toString() {
        switch (this) {
            case PRESIDING_JUDGE:
                return "Przewodniczący";
            case REPORTING_JUDGE:
                return "Sprawozdawca";
            case REASONS_FOR_JUDGEMENT_AUTHOR:
                return "Autor uzasadnienia";
            default:
                return "";
        }
    }
}
