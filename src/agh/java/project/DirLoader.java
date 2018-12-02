package agh.java.project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DirLoader {
    private Path jsonsDirPath;

    public DirLoader(String path) {
        jsonsDirPath = Paths.get(path);
        if(!Files.isDirectory(jsonsDirPath))
            throw new InvalidPathException(jsonsDirPath.toString(), "is not a directory");
    }

    public ArrayList<String> getFiles() throws IOException {
        List<Path> paths = Files.walk(jsonsDirPath)
                .filter(path -> path.toString().endsWith(".json"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        ArrayList<String> results = new ArrayList<>();
        for(Path path : paths) {
            results.add(new FileLoader(path).toString());
        }
        return results;
    }

}
