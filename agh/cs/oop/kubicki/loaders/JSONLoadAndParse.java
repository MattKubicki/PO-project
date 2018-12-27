package agh.cs.oop.kubicki.loaders;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;

public class JSONLoadAndParse extends AbstractLoadAndParse {
    private Path jsonsDirPath;
    private ArrayList<String> gotFiles;
    private Database db;

    public JSONLoadAndParse(String jsonsDirPath, Database db) {
        super(jsonsDirPath);
        this.db = db;
        try {
            this.gotFiles = getFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseAllFiles();
    }

    public ArrayList<String> getFiles() throws IOException {
        return super.getFiles(".json");
    }

    @Override
    public ArrayList<Verdicts> parse(String json) {
        String properJson = dropMeta(json);
        Type verdictsListType = TypeToken.getParameterized(ArrayList.class, Verdicts.class).getType();
        // ^ the way to specify precisely that Gson should convert json to a ArrList of Verdicts
        Gson gson = new Gson();
        return gson.fromJson(properJson, verdictsListType);
    }

    public void parseAllFiles(){
        super.parseAllFiles(gotFiles, this.db);
    }

    private String dropMeta(String wholeJson) {
        int begin = wholeJson.indexOf("items") + 7;
        int end = wholeJson.indexOf("queryTemplate") - 2;
        return wholeJson.substring(begin, end);
    }
}
