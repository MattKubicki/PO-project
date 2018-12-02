package agh.java.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

import java.text.ParseException;

public class Parser {
    public ArrayList<Verdicts> parse(String json) {
        String properJson = dropMeta(json);
        Type verdictsListType = TypeToken.getParameterized(ArrayList.class, Verdicts.class).getType();
        // ^ the way to specify precisely that Gson should convert json to a ArrList of Verdicts
        Gson gson = new Gson();
        return gson.fromJson(properJson, verdictsListType);
    }

    public List<List<Verdicts>> parseAllFiles(ArrayList<String> files) throws IOException {
        List<List<Verdicts>> allVerdicts = new ArrayList<>();
        for(String file : files){
            allVerdicts.add(parse(file));
        }

        return allVerdicts;
    }


    private String dropMeta(String wholeJson) {
        int begin = wholeJson.indexOf("items") + 7;
        int end = wholeJson.indexOf("queryTemplate") - 2;
        return wholeJson.substring(begin, end);
    }

}
