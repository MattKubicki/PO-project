package agh.java.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Month;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser();
            String path = System.getProperty("user.dir") + "\\json";
            System.out.println(path);
            DirLoader getter = new DirLoader(path);
            Database db = new Database();
            ArrayList<String> files = getter.getFiles();
            List<List<Verdicts>> verList = parser.parseAllFiles(files);
            int counter = 0;
            for (int k = 0; k < verList.size(); k++) {
                for (int i = 0; i < verList.get(k).size(); i++) {
                    List<CourtCases> RCC = verList.get(k).get(i).getCourtCases();
                    counter++;
                    for (int j = 0; j < RCC.size(); j++) {
                        db.add(RCC.get(j).getCaseNumber(), verList.get(k).get(i));
                       // System.out.println(verList.get(k).get(i).getCourtCases().toString());

                    }
                }
            }
//            System.out.println();
//            System.out.println(counter);
            Util util = new Util(db);
            List<String> signatures = new ArrayList<>();
            signatures.add("III AUa 846/12");
            signatures.add("VI ACa 819/13");
            signatures.add("IV CZ 130/13");
            signatures.add("KIO 2505/12");
            List<String> testList = util.getMetrics(signatures);
            for (String test : testList) {
                System.out.println(test);
            }
            System.out.println();
            System.out.println(util.judgmentsByJudgeNo("Ewa Drzymała"));
            System.out.println();
            LinkedHashMap<String, Integer> testMap = util.top10ByVerdicts();
            for (Map.Entry<String, Integer> entry : testMap.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                System.out.println(value + "    " + key);
            }//wypisalo 10 a co jesli 11 ma tyle samo ???
            SortedMap<Month, Integer> testMap2 = util.verdictsByMonth();
            System.out.println();
            for (Map.Entry<Month, Integer> entry : testMap2.entrySet()) {
                Month key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key + " - " + value);
            }
            System.out.println();
            SortedMap<CourtType, Integer> testMap3 = util.verdictsByCourtType();
            for (Map.Entry<CourtType, Integer> entry : testMap3.entrySet()) {
                CourtType key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println(key + " - " + value);
            }
            System.out.println();
            LinkedHashMap<String,Integer> testMap4 = util.top10CalledRefRegs();
            for (Map.Entry<String, Integer> entry : testMap4.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                System.out.println(key + "    " + value);
            }
            System.out.println();
            System.out.println(util.avgJudgesNoByVerdict());
        }
        catch (IOException ex){
            ex.printStackTrace();
           System.out.println("bad directory");
           System.exit(1);
        }
    }
}