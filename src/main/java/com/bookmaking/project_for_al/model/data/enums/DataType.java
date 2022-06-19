package com.bookmaking.project_for_al.model.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DataType {

    BET("BET"), RESULT("RESULT");

    private final String dataTypeDescription;

}
