package agh.java.project;

import java.util.Objects;

public class ReferencedRegulations {
    private String journalTitle;
    private int journalNo;
    private int journalYear;
    private int journalEntry;
    private String text;

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
