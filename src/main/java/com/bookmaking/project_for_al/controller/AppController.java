package com.bookmaking.project_for_al.controller;

import com.bookmaking.project_for_al.controller.dispather.InputDataDispatcher;
import com.bookmaking.project_for_al.service.StreamOfInputDataSupplier;

public class AppController {

    public static void run() throws Exception {

        InputDataDispatcher.dispose(StreamOfInputDataSupplier.supply());


    }

}

