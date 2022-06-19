package com.bookmaking.project_for_al.controller.dispather.input_dispather;

import com.bookmaking.project_for_al.model.data.enums.DataType;
import com.bookmaking.project_for_al.model.data.enums.InputKeysNames;
import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputResult;
import org.json.simple.JSONObject;

import java.math.BigDecimal;

public abstract class InputDataObjectCreator {

    public static InputBet createInputBet(JSONObject jsonObject) {
        return InputBet.builder()
                .fixture(getStringValueFromJSONObject(jsonObject, InputKeysNames.FIXTURE))
                .outcome(getStringValueFromJSONObject(jsonObject, InputKeysNames.OUTCOME))
                .stake(new BigDecimal(getStringValueFromJSONObject(jsonObject, InputKeysNames.STAKE)))
                .odds(Double.parseDouble(getStringValueFromJSONObject(jsonObject, InputKeysNames.ODDS)))
                .build();
    }

    public static InputResult createInputResult(JSONObject jsonObject) {
        return InputResult.builder()
                .fixture(getStringValueFromJSONObject(jsonObject, InputKeysNames.FIXTURE))
                .result(getStringValueFromJSONObject(jsonObject, InputKeysNames.RESULT))
                .build();
    }

    public static InputData createInputData(JSONObject jsonObject, Inputable inputBetOrResult, DataType inputDataType) {
        return InputData.builder()
                .inputObject(inputBetOrResult)
                .inputType(inputDataType.getDataTypeDescription())
                .build();
    }

    private static String getStringValueFromJSONObject(JSONObject jsonObject, InputKeysNames inputKeysNames) {
        return jsonObject.get(inputKeysNames.getInputKeyName()).toString();
    }

}