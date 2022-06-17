package com.bookmaking.project_for_al.controller.dispather;

import com.bookmaking.project_for_al.model.data.DataType;
import com.bookmaking.project_for_al.model.data.InputKeysNames;
import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputResult;
import org.json.simple.JSONObject;

import java.math.BigDecimal;

public abstract class InputDataObjectCreator {

    public static InputBet createInputBet(JSONObject jsonObject) {
        InputBet inputBet = InputBet.builder()
                .fixture(getStringValueFromJSONObject(jsonObject, InputKeysNames.FIXTURE))
                .outcome(getStringValueFromJSONObject(jsonObject, InputKeysNames.OUTCOME))
                .stake(new BigDecimal(getStringValueFromJSONObject(jsonObject, InputKeysNames.STAKE)))
                .odds(Double.parseDouble(getStringValueFromJSONObject(jsonObject, InputKeysNames.ODDS)))
                .build();
        return inputBet;
    }

    public static InputResult createInputResult(JSONObject jsonObject) {
        InputResult inputResult = InputResult.builder()
                .fixture(getStringValueFromJSONObject(jsonObject, InputKeysNames.FIXTURE))
                .result(getStringValueFromJSONObject(jsonObject, InputKeysNames.RESULT).charAt(0))
                .build();
        return inputResult;
    }

    public static InputData createInputData(JSONObject jsonObject, Inputable inputBetOrResult, DataType inputDataType) {
        InputData inputData = InputData.builder()
                .inputObject(inputBetOrResult)
                .inputType(inputDataType.getDataTypeDescription())
                .build();
        return inputData;
    }

    private static String getStringValueFromJSONObject(JSONObject jsonObject, InputKeysNames inputKeysNames) {
        return jsonObject.get(inputKeysNames.getInputKeyName()).toString();
    }

}
