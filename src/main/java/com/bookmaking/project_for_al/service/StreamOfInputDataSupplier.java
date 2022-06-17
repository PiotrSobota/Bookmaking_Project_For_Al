package com.bookmaking.project_for_al.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.stream.Stream;

public abstract class StreamOfInputDataSupplier {

    /*This class is made to create input Stream from messages.json file in 'resources' package.
    It should have only static class and stay abstract. */

    //Input file location
    private static final String INPUT_FILE_NAME = "src/main/resources/messages.json";

    //Master method in this class. Should stay public. Return next lines from input file as Stream.
    public static Stream<String> supply() throws FileNotFoundException {
        return createBufferReaderForFile().lines();
    }

    //Create bufferReader from input JSON file
    private static BufferedReader createBufferReaderForFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader(INPUT_FILE_NAME);
        return new BufferedReader(fileReader);
    }

}
