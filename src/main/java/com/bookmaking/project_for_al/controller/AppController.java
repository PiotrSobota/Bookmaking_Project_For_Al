package com.bookmaking.project_for_al.controller;

import com.bookmaking.project_for_al.controller.dispather.input_dispather.InputDataDispatcher;
import com.bookmaking.project_for_al.controller.dispather.output_dispatcher.OutputDataDispatcher;
import com.bookmaking.project_for_al.controller.printers.ConsolePrinter;
import com.bookmaking.project_for_al.service.StreamOfInputDataSupplier;

public class AppController {

    public static void run() throws Exception {

        InputDataDispatcher.dispose(StreamOfInputDataSupplier.supply());
        OutputDataDispatcher.getOutputMessagesStream()
                .forEach(ConsolePrinter::printString);
    }

}

