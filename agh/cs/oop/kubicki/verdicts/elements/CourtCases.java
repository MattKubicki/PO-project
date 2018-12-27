package agh.cs.oop.kubicki.verdicts.elements;

public class CourtCases {
    private String caseNumber;

    public String getCaseNumber() {
        return caseNumber;
    }

    public CourtCases(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    @Override
    public String toString() {
        return "caseNumber='" + caseNumber + '\'' + '}';
    }
}
