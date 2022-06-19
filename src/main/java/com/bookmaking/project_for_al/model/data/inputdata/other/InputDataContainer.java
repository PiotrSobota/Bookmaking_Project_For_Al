package com.bookmaking.project_for_al.model.data.inputdata.other;

import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;

@Getter
@Setter
@ToString
public abstract class InputDataContainer {

    //This class is container for InputData objects (InputBet and InputResult)

    private static LinkedList<InputData> inputDataList = new LinkedList<>();

    public static void addObjectToContainer(InputData inputData) {
        inputDataList.add(inputData);
    }

    public static LinkedList<InputData> getInputDataList() {
        return inputDataList;
    }
}
