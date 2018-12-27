package agh.cs.oop.kubicki.loaders;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;
import agh.cs.oop.kubicki.verdicts.elements.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HTMLLoadAndParse extends AbstractLoadAndParse {
    private String htmlDirPath;
    private ArrayList<String> gotFiles;
    private Database db;

    public HTMLLoadAndParse(String htmlDirPath, Database db) {
        super(htmlDirPath);
        this.db = db;
        try {
            this.gotFiles = getFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseAllFiles();
    }

    public ArrayList<String> getFiles() throws IOException {
        return super.getFiles(".html");
    }

    @Override
    public ArrayList<Verdicts> parse(String file) {
        Document document = Jsoup.parse(file);
        Element body = document.body();
        Verdicts parsedVer = new Verdicts();
        String caseNo = body.getElementsByClass("war_header").get(0).html().split(" -")[0];
        List<CourtCases> courtCases = new ArrayList<>();
        CourtCases cc = new CourtCases(caseNo);
        courtCases.add(cc);
        parsedVer.setCourtCases(courtCases);
        String textContent = body.getElementsByClass("info-list-value-uzasadnienie").get(0).child(0).html();
        //function child is needed cause content could have more than one reason
        parsedVer.setTextContent(textContent);
        String journalTitle = body.getElementsByClass("nakt").html();
        ReferencedRegulations rr = new ReferencedRegulations(journalTitle);
        List<ReferencedRegulations> refRegs = new ArrayList<>();
        refRegs.add(rr);
        parsedVer.setReferencedRegulations(refRegs);
        List<Element> elementList = body.getElementsByClass("niezaznaczona");
        for (Element element: elementList){
            String tmp = element.getElementsByClass("lista-label").html();
            if (tmp.equals("Data orzeczenia")){
                String judgmentDate =
                        element.getElementsByClass("info-list-value").get(0)
                                .getElementsByTag("td").get(1).html();
                parsedVer.setJudgmentDate(judgmentDate);
            }
            else if (tmp.equals("Sąd")){
                String courtType =
                        element.getElementsByClass("info-list-value").get(0).html();
                CourtType ct = parseCourtType(courtType);
                parsedVer.setCourtType(ct);
            }
            else if (tmp.equals("Sędziowie")){
                String[] allJudgesInVer =
                        element.getElementsByClass("info-list-value").html().split("<br>");
                List<Judge> parsedJudges = new ArrayList<>();
                for (int i=0; i<allJudgesInVer.length; i++){
                    parsedJudges.add(i,parseJudges(allJudgesInVer[i]));
                }
                parsedVer.setJudges(parsedJudges);
            }

        }
        ArrayList<Verdicts> verdicts=new ArrayList<>();
        verdicts.add(parsedVer);

        return verdicts;
    }

    public void parseAllFiles() {
        super.parseAllFiles(gotFiles,this.db);
    }

    private CourtType parseCourtType (String strCT){
        if (strCT.contains("Naczelny")) return CourtType.SUPREME_ADMINISTRATIVE;
        else{
            return CourtType.REGIONAL_ADMINISTRATIVE;
        }
    }

    private Judge parseJudges (String stringJudge){
        String name = "";
        if (stringJudge.contains("/")) {
            name = (stringJudge.substring(0, stringJudge.indexOf('/')));
            stringJudge = stringJudge.substring(stringJudge.indexOf('/') + 1);
        } else {
            name = stringJudge;
            stringJudge = "";
        }
        LinkedList<JudgeRole> specialRoles = parseJudgeRoles(stringJudge);
        return new Judge(name,"",specialRoles);
    }

    private LinkedList<JudgeRole> parseJudgeRoles(String strJR) {
        LinkedList<JudgeRole> specialRoles = new LinkedList<>();
        if (strJR.contains("przewodniczący sprawozdawca")) {
            specialRoles.add(JudgeRole.PRESIDING_JUDGE);
            specialRoles.add(JudgeRole.REPORTING_JUDGE);
        } else if (strJR.contains("przewodniczący")) {
            specialRoles.add(JudgeRole.PRESIDING_JUDGE);
        } else if (strJR.contains("sprawozdawca")) {
            specialRoles.add(JudgeRole.REPORTING_JUDGE);
        } else {
            specialRoles.add(JudgeRole.NONE);
        }
        return specialRoles;
    }
}
