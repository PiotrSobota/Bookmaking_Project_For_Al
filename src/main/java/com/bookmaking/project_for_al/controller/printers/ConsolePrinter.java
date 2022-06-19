package com.bookmaking.project_for_al.controller.printers;

import java.util.stream.Stream;

public abstract class ConsolePrinter {

    //Just print input string to console. Created to replace "sout" method in code.
    public static void printString(String string) {
        System.out.println(string);
    }

    //Just print input string to console. Created to replace "sout" method based on Stream in code.
    public static void printStream(Stream<String> streamToPrint) {
        streamToPrint.forEach(ConsolePrinter::printString);
    }

}
