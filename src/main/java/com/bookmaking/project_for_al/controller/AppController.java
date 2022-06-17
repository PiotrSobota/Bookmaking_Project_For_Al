package com.bookmaking.project_for_al.controller;

import com.bookmaking.project_for_al.model.data.DataType;
import com.bookmaking.project_for_al.model.data.InputKeysNames;
import com.bookmaking.project_for_al.model.data.inputdata.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.InputData;
import com.bookmaking.project_for_al.service.StreamOfInputDataSupplier;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;

public class AppController {

    private static LinkedList<InputData> inputDataList = new LinkedList<>();

    public static void run() throws Exception {

        JSONParser jsonParser = new JSONParser();

        StreamOfInputDataSupplier.supply()
                .forEach(line -> createInputObjectFromJsonBasedStringAndAddToInputDataList(line, jsonParser));

        System.out.println("------------");
        System.out.println(inputDataList.toString());
    }

    public static void createInputObjectFromJsonBasedStringAndAddToInputDataList(String jsonBasedString, JSONParser jsonParser) {
        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) jsonParser.parse(jsonBasedString);

            if (jsonObject.get(InputKeysNames.TYPE.getInputKeyName()).equals(DataType.BET.getDataTypeDescription())) {

                JSONObject nestedInputDataObject = (JSONObject) jsonObject.get(InputKeysNames.BET.getInputKeyName());

                InputBet inputBet = InputBet.builder()
                        .fixture((String) nestedInputDataObject.get(InputKeysNames.FIXTURE.getInputKeyName()))
                        .outcome(nestedInputDataObject.get(InputKeysNames.OUTCOME.getInputKeyName()).toString())
                        .stake(new BigDecimal(nestedInputDataObject.get(InputKeysNames.STAKE.getInputKeyName()).toString()))
                        .odds((Double) nestedInputDataObject.get(InputKeysNames.ODDS.getInputKeyName()))
                        .build();

                InputData inputData = InputData.builder()
                        .inputObject(inputBet)
                        .inputType(DataType.BET.getDataTypeDescription())
                        .build();

                inputDataList.add(inputData);

                System.out.println(jsonObject.get("bet"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static BufferedReader supplyWithBufferReaderForMessagesFile() throws FileNotFoundException {

        FileReader fileReader = new FileReader("src/main/resources/messages.json");
        return new BufferedReader(fileReader);
    }


}

