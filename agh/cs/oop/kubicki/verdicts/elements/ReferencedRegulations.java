package agh.cs.oop.kubicki.verdicts.elements;

import java.util.Objects;

public class ReferencedRegulations {
    private String journalTitle;
    private int journalNo;
    private int journalYear;
    private int journalEntry;
    private String text;

    public ReferencedRegulations(String journalTitle) {
        this.journalTitle = journalTitle;
        this.journalNo = 0;
        this.journalYear = 0;
        this.journalEntry = 0;
        this.text = "";
    }



    public String getJournalTitle() {
        return journalTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferencedRegulations)) return false;
        ReferencedRegulations RR = (ReferencedRegulations) o;
        return journalTitle.equals(RR.journalTitle);
    }

    public int hashCode() {
        return Objects.hash(journalTitle);
    }
}
