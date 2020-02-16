package com.doclerholding.pingapplication.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum  ResourceFileReader {

    INSTANCE;

    public List<String> readFile(String fileName) throws IOException {
        final InputStream input = ResourceFileReader.class.getResourceAsStream("/" + fileName);
        return Arrays.stream(new String(input.readAllBytes()).split("\n"))
            .collect(Collectors.toList());
    }
}
