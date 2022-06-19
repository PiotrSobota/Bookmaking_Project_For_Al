package com.bookmaking.project_for_al.model.data.inputdata.other;

import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputResult;
import com.bookmaking.project_for_al.service.StreamOfInputDataSupplier;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputDataContainerTest {

    //GIVEN
    InputBet inputBet1 = InputBet.builder()
            .fixture("Example")
            .odds(20.0)
            .stake(new BigDecimal("20.00"))
            .outcome("1")
            .build();

    InputBet inputBet2 = InputBet.builder()
            .fixture("Example")
            .odds(20)
            .stake(new BigDecimal("20.0023323"))
            .outcome("X")
            .build();

    InputBet inputBet3 = InputBet.builder()
            .fixture("Example")
            .odds(20.23323)
            .stake(new BigDecimal("20"))
            .outcome("2")
            .build();

    InputResult inputResult1 = InputResult.builder()
            .fixture("Example")
            .result("1")
            .build();

    InputResult inputResult2 = InputResult.builder()
            .fixture("Example")
            .result("X")
            .build();

    InputResult inputResult3 = InputResult.builder()
            .fixture("Example")
            .result("2")
            .build();

    InputData inputData1 = InputData.builder().inputType("BET").inputObject(inputBet1).build();
    InputData inputData2 = InputData.builder().inputType("BET").inputObject(inputBet2).build();
    InputData inputData3 = InputData.builder().inputType("BET").inputObject(inputBet3).build();

    InputData inputData4 = InputData.builder().inputType("RESULT").inputObject(inputResult1).build();
    InputData inputData5 = InputData.builder().inputType("RESULT").inputObject(inputResult2).build();
    InputData inputData6 = InputData.builder().inputType("RESULT").inputObject(inputResult3).build();


    @Test
    void shouldAddObjectToContainerDontThrowException() {

        //given, when, then
        Assertions.assertDoesNotThrow(() -> InputDataContainer.addObjectToContainer(inputData1));
        Assertions.assertDoesNotThrow(() -> InputDataContainer.addObjectToContainer(inputData2));
        Assertions.assertDoesNotThrow(() -> InputDataContainer.addObjectToContainer(inputData3));
        Assertions.assertDoesNotThrow(() -> InputDataContainer.addObjectToContainer(inputData4));
        Assertions.assertDoesNotThrow(() -> InputDataContainer.addObjectToContainer(inputData5));
        Assertions.assertDoesNotThrow(() -> InputDataContainer.addObjectToContainer(inputData6));
    }

    @Test
    void shouldGetInputDataListReturnCorrectList() {

        //given
        LinkedList<InputData> requiredDataLinkedList = new LinkedList<>();
        requiredDataLinkedList.add(inputData1);
        requiredDataLinkedList.add(inputData2);
        requiredDataLinkedList.add(inputData3);
        requiredDataLinkedList.add(inputData4);
        requiredDataLinkedList.add(inputData5);
        requiredDataLinkedList.add(inputData6);

        //when
        for (InputData inputData : requiredDataLinkedList) {
            InputDataContainer.addObjectToContainer(inputData);
        }

        //then
        Assert.assertEquals(requiredDataLinkedList, InputDataContainer.getInputDataList());

    }
}