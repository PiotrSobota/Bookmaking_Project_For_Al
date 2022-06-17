package com.bookmaking.project_for_al.model.data;

import lombok.Getter;

@Getter
public enum DataType {

    BET("BET"), RESULT("RESULT");

    private final String dataTypeDescription;

    DataType(String dataTypeDescription) {
        this.dataTypeDescription = dataTypeDescription;
    }

}
