package com.bookmaking.project_for_al.controller.dispather.input_dispather;

import com.bookmaking.project_for_al.model.data.enums.DataType;
import com.bookmaking.project_for_al.model.data.enums.InputKeysNames;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import com.bookmaking.project_for_al.model.data.inputdata.other.InputDataContainer;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.util.stream.Stream;

public abstract class InputDataDispatcher {

    /*This dispatcher class use JSON-simple library. Why? Because based on this comparsion:
     https://www.overops.com/blog/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/
     ... this library is almost fastest for small and large json files also.
     Differences between fastest library for large files (Jackson) and fastest for small files (GSON) are lower than
     6 % in the cited comparison.

     It's also very easy to use, to get input data type (result or bet) before dispatch.
     */

    //Parser from JSON-simple library
    private static final JSONParser JSON_PARSER = new JSONParser();


    //Master method in this class. Should stay public.
    public static void dispose(Stream<String> inputDataStream) throws FileNotFoundException {
        inputDataStream
                .forEach(InputDataDispatcher::dispatch);
    }

    //This method check type of json object, convert to correct type, and save java object in InputDataContainer
    private static void dispatch(String jsonBasedString) {

        JSONObject jsonObject;
        JSONObject nestedInputDataObject;
        InputData inputData;

        //had to use try/catch to use method in lambda
        try {

            jsonObject = parseJsonBasedStringToJsonObject(jsonBasedString, JSON_PARSER);

            if (checkIfJSONObjectIsInType(jsonObject, DataType.BET)) {

                nestedInputDataObject = getJSONObjectFromOtherJSONObjBasedOnKeyName(jsonObject, InputKeysNames.BET);

                InputBet inputBet = InputDataObjectCreator.createInputBet(nestedInputDataObject);
                inputData = InputDataObjectCreator.createInputData(jsonObject, inputBet, DataType.BET);

                InputDataContainer.addObjectToContainer(inputData);
            } else if (checkIfJSONObjectIsInType(jsonObject, DataType.RESULT)) {

                nestedInputDataObject = getJSONObjectFromOtherJSONObjBasedOnKeyName(jsonObject, InputKeysNames.RESULT);

                InputResult inputResult = InputDataObjectCreator.createInputResult(nestedInputDataObject);
                inputData = InputDataObjectCreator.createInputData(jsonObject, inputResult, DataType.RESULT);
                InputDataContainer.addObjectToContainer(inputData);
            } else throw new IllegalArgumentException();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

    //Just parsing String from input to JsonObject
    private static JSONObject parseJsonBasedStringToJsonObject(String jsonBasedString, JSONParser jsonParser) throws ParseException {
        JSONObject jsonObject;
        jsonObject = (JSONObject) jsonParser.parse(jsonBasedString);
        return jsonObject;
    }

    //Method to extract a nested object
    private static JSONObject getJSONObjectFromOtherJSONObjBasedOnKeyName(JSONObject jsonObject, InputKeysNames keyName) {
        return (JSONObject) jsonObject.get(keyName.getInputKeyName());
    }

    //Method to check type of object. For conditional statement in "dispatch" method
    private static boolean checkIfJSONObjectIsInType(JSONObject jsonObject, DataType requiredType) {
        return jsonObject
                .get(InputKeysNames.TYPE.getInputKeyName())
                .equals(requiredType.getDataTypeDescription());
    }

}
