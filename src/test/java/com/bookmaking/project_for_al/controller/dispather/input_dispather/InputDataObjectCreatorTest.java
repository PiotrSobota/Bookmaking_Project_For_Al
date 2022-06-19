package com.bookmaking.project_for_al.controller.dispather.input_dispather;

import com.bookmaking.project_for_al.model.data.enums.DataType;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InputDataObjectCreatorTest {

    //GIVEN
    InputBet inputBet1 = InputBet.builder()
            .fixture("Example")
            .odds(20.0)
            .stake(new BigDecimal("20.0"))
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
    void shouldCreateCorrectInputData() {

        //given, when
        InputData inputData1Result = InputDataObjectCreator.createInputData(inputBet1, DataType.BET);
        InputData inputData2Result = InputDataObjectCreator.createInputData(inputBet2, DataType.BET);
        InputData inputData3Result = InputDataObjectCreator.createInputData(inputBet3, DataType.BET);
        InputData inputData4Result = InputDataObjectCreator.createInputData(inputResult1, DataType.RESULT);
        InputData inputData5Result = InputDataObjectCreator.createInputData(inputResult2, DataType.RESULT);
        InputData inputData6Result = InputDataObjectCreator.createInputData(inputResult3, DataType.RESULT);

        System.out.println(inputData1Result.toString());
        System.out.println(inputData1.toString());

        //then
        Assertions.assertEquals(inputData1, inputData1Result);
        Assertions.assertEquals(inputData2, inputData2Result);
        Assertions.assertEquals(inputData3, inputData3Result);
        Assertions.assertEquals(inputData4, inputData4Result);
        Assertions.assertEquals(inputData5, inputData5Result);
        Assertions.assertEquals(inputData6, inputData6Result);

    }

    @Test
    void shouldCreateInputBet() throws ParseException {

        //given
        String jsonWithInputBet = "{\"fixture\":\"Example\",\"stake\":20.00,\"odds\":20.00,\"outcome\":\"1\"}";
        JSONParser jsonParser;
        JSONObject jsonObject;

        //when
        jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(jsonWithInputBet);

        InputBet createdInputBet = InputDataObjectCreator.createInputBet(jsonObject);

        //then
        Assert.assertEquals(inputBet1, createdInputBet);

    }

    @Test
    void shouldCreateInputResult() throws ParseException {

        //given
        String jsonWithInputResult = "{ \"fixture\" : \"Example\", \"result\" : \"1\" }";
        JSONParser jsonParser;
        JSONObject jsonObject;

        //when
        jsonParser = new JSONParser();
        jsonObject = (JSONObject) jsonParser.parse(jsonWithInputResult);

        InputResult createdInputResult = InputDataObjectCreator.createInputResult(jsonObject);

        //then
        Assert.assertEquals(inputResult1, createdInputResult);

    }
}