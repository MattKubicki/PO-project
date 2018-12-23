package agh.cs.oop.kubicki.loaders;

import agh.cs.oop.kubicki.Database;
import agh.cs.oop.kubicki.verdicts.Verdicts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractLoadAndParse {
    private Path dirPath;

    public AbstractLoadAndParse(String path) {
        dirPath = Paths.get(path);
        if(!Files.isDirectory(dirPath))
            throw new InvalidPathException(dirPath.toString(), "is not a directory");
    }

    public ArrayList<String> getFiles(String extension) throws IOException {
        List<Path> paths = Files.walk(dirPath)
                .filter(path -> path.toString().endsWith(extension))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        ArrayList<String> results = new ArrayList<>();
        for(Path path : paths) {
            results.add(new FileLoader(path).toString());
        }
        return results;
    }

    public void parseAllFiles(ArrayList<String> files, Database database){
        List<List<Verdicts>> allVerdicts = new ArrayList<>();
        for(String file : files){
            allVerdicts.add(parse(file));
        }
        for (int k = 0; k < allVerdicts.size(); k++) {
            for (int i = 0; i < allVerdicts.get(k).size(); i++) {
                database.add(allVerdicts.get(k).get(i).getCourtCases().get(0).getCaseNumber(), allVerdicts.get(k).get(i));
            }
        }
    }

    public abstract ArrayList<Verdicts> parse(String file);
}
