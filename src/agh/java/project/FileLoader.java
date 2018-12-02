package agh.java.project;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    private List<String> lines;

    public FileLoader(Path path){
        List<String> lines = new ArrayList<>();
        try{
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        }
        catch (IOException e){
            System.out.println("Couldn't open file with path: \"" + path + "\".");
            System.exit(1);
            e.printStackTrace();
        }
        this.lines = lines;

    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i=0; i<lines.size();i++){
            sb.append(lines.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
}
