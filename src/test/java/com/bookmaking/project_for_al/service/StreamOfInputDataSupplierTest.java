package com.bookmaking.project_for_al.service;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class StreamOfInputDataSupplierTest {

    @Test
    void shouldSupplyMethodThrowFileNotFoundExceptionForFakeLocation() {

        //given, when, then

        Assertions.assertThrows(FileNotFoundException.class,
                () -> StreamOfInputDataSupplier.supply("fakeFileLocation"));

    }

    @Test
    void shouldSupplyMethodNotThrowNothingForCorrectFileLocation() {

        //given
        String correctFileLocation = "src/main/resources/messages.json";

        //when, then
        Assertions.assertDoesNotThrow(() -> StreamOfInputDataSupplier.supply(correctFileLocation));
    }

}