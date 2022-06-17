package com.bookmaking.project_for_al.model.data;

import lombok.Getter;

@Getter
public enum InputKeysNames {

    TYPE("type"), BET("bet"), FIXTURE("fixture"), OUTCOME("outcome"), STAKE("stake"), ODDS("odds"), RESULT("result");

    private final String inputKeyName;

    InputKeysNames(String inputKeyName) {
        this.inputKeyName = inputKeyName;
    }

}
