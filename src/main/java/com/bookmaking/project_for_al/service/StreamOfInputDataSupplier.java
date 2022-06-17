package com.bookmaking.project_for_al.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public abstract class StreamOfInputDataSupplier {

    private static final String INPUT_FILE_NAME = "src/main/resources/messages.json";

    private static BufferedReader createBufferReaderForFile() throws FileNotFoundException {

        FileReader fileReader = new FileReader(INPUT_FILE_NAME);
        return new BufferedReader(fileReader);
    }

    public static Stream<String> supply() throws FileNotFoundException {
        return createBufferReaderForFile().lines();
    }

}
