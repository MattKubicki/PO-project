package agh.java.project;

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
        sb.append("Sygnatura: ");
        for(CourtCases cc : courtCases) {
            sb.append(cc.getCaseNumber());
            sb.append(" ");
        }
        sb.append("\n");
        sb.append("Data orzeczenia: ");
        /*
        if (judgmentDate.get(Calendar.DATE) <10)
            sb.append("0").append(judgmentDate.get(Calendar.DATE)).append(". ");
        else
            sb.append(judgmentDate.get(Calendar.DATE)).append(". ");
        if (judgmentDate.get(Calendar.MONTH) < Calendar.OCTOBER)
            sb.append("0").append(judgmentDate.get(Calendar.MONTH)+1).append(". ");
        else
            sb.append(judgmentDate.get(Calendar.MONTH)+1).append(". ");
        sb.append(judgmentDate.get(Calendar.YEAR));
        */
        sb.append(judgmentDate);
        sb.append("\n");
        sb.append("Typ sądu: ");
        sb.append(courtType);
        sb.append("\n");
        sb.append("Lista sędziów: ").append("\n");
        int judgeNum = 1;
        for(Judge judge : judges) {
            sb.append(" ").append(judgeNum).append(". ").append(judge).append("\n");
            judgeNum++;
        }
        return sb.toString();
    }

    public String getReason(){
        int index = getTextContent().indexOf("UZASADNIENIE");
        //return getTextContent().substring(index);
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
}
