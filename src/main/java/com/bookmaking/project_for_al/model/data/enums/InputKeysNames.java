package com.bookmaking.project_for_al.model.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InputKeysNames {

    //All keys of nested object in JSON file

    TYPE("type"), BET("bet"), FIXTURE("fixture"),
    OUTCOME("outcome"), STAKE("stake"), ODDS("odds"), RESULT("result");

    private final String inputKeyName;

}
