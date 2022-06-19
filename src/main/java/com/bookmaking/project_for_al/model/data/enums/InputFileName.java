package com.bookmaking.project_for_al.model.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InputFileName {

    INPUT_FILE_NAME("src/main/resources/messages.json");

    private final String jsonfileName;

}
