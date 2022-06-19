package com.bookmaking.project_for_al.controller;

import com.bookmaking.project_for_al.controller.dispather.input_dispather.InputDataDispatcher;
import com.bookmaking.project_for_al.controller.dispather.output_dispatcher.OutputDataDispatcher;
import com.bookmaking.project_for_al.controller.printers.ConsolePrinter;
import com.bookmaking.project_for_al.model.data.enums.InputFileName;
import com.bookmaking.project_for_al.service.StreamOfInputDataSupplier;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class ConsoleAppController {

    public static void run() {

        try {
            //Reads input JSON, creates objects and send them to InputDataContainer
            InputDataDispatcher.dispose(StreamOfInputDataSupplier.supply(InputFileName.INPUT_FILE_NAME.getJsonfileName()));

            //Calculates solution and send output to console
            ConsolePrinter.printStream(OutputDataDispatcher.getOutputMessagesStream());
        }
        catch (FileNotFoundException e)
        {
            ConsolePrinter.printString("Json file: \"src/main/resources/messages.json\" does not exist");
        }
        catch (InputMismatchException | IllegalArgumentException e)
        {
            ConsolePrinter.printString("Incorrect input data (JSON file or inside values)");
        }
        catch (NullPointerException e)
        {
            ConsolePrinter.printString("Something went wrong in application - NullPointerException occurred");
        }
    }

}

