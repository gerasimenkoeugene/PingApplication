package com.doclerholding.pingapplication.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ResourceFileReader {
    public List<String> readFile(String fileName) throws IOException {
        String pathToFile = getClass().getClassLoader().getResource(fileName).getPath();
        return Files.lines(Paths.get(pathToFile)).collect(Collectors.toList());
    }
}
