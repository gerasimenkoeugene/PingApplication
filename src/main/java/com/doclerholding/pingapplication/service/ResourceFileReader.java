package com.doclerholding.pingapplication.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public enum  ResourceFileReader {

    INSTANCE;

    public List<String> readFile(String fileName) throws IOException {
        String pathToFile = getClass().getClassLoader().getResource(fileName).getPath();
        return Files.lines(new File(pathToFile).toPath()).collect(Collectors.toList());
    }
}
