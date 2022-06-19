package com.bookmaking.project_for_al.model.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataType {

    //Data types of input value - "type" key in JSON

    BET("BET"), RESULT("RESULT");

    private final String dataTypeDescription;

}
