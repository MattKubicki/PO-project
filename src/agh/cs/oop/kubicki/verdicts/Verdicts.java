package agh.cs.oop.kubicki.verdicts;

import agh.cs.oop.kubicki.verdicts.elements.CourtCases;
import agh.cs.oop.kubicki.verdicts.elements.CourtType;
import agh.cs.oop.kubicki.verdicts.elements.Judge;
import agh.cs.oop.kubicki.verdicts.elements.ReferencedRegulations;

import java.util.List;


public class Verdicts {
    private List<CourtCases> courtCases;
    private String judgmentDate;
    private CourtType courtType;
    private List<Judge> judges;
    private String textContent;
    private List<ReferencedRegulations> referencedRegulations;


    public String getRubrum() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Sygnatura: ");
        for(CourtCases cc : courtCases) {
            sb.append(cc.getCaseNumber());
            sb.append(" ");
        }
        sb.append("\n");
        sb.append("Data orzeczenia: ");
        sb.append(judgmentDate);
        sb.append("\n");
        sb.append("Typ sądu: ");
        sb.append(courtType);
        sb.append("\n");
        sb.append("Lista sędziów: ").append("\n");
        int judgeNum = 1;
        if (judges!=null) {
            for (Judge judge : judges) {
                sb.append(" ").append(judgeNum).append(". ").append(judge).append("\n");
                judgeNum++;
            }
        }
        return sb.toString();
    }

    public String getReason(){
        int index = getTextContent().indexOf("UZASADNIENIE");
        if (index == -1) return dropHtmlTags(getTextContent()); //verdicts parsed from html don't contain "UZASADNIENIE"
        return dropHtmlTags(getTextContent().substring(index));
    }

    public String dropHtmlTags(String reasonWithTags){
        return reasonWithTags.replaceAll("<[^>]+>",""); //remove substrings beginning with "<", having whichever length and consisting of any chars except of ">"  in "<..>"
    }

    public CourtType getCourtType() {
        return courtType;
    }

    public List<Judge> getJudges() {
        return judges;
    }

    public String getJudgmentDate() {
        return judgmentDate;
    }

    public String getTextContent() {
        return textContent;
    }

    public List<CourtCases> getCourtCases() {
        return courtCases;
    }

    public List<ReferencedRegulations> getReferencedRegulations() {
        return referencedRegulations;
    }

    public void setCourtCases(List<CourtCases> courtCases) {
        this.courtCases = courtCases;
    }

    public void setJudgmentDate(String judgmentDate) {
        this.judgmentDate = judgmentDate;
    }

    public void setCourtType(CourtType courtType) {
        this.courtType = courtType;
    }

    public void setJudges(List<Judge> judges) {
        this.judges = judges;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setReferencedRegulations(List<ReferencedRegulations> referencedRegulations) {
        this.referencedRegulations = referencedRegulations;
    }
}
