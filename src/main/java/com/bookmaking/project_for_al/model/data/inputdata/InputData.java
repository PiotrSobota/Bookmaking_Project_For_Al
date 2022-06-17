package com.bookmaking.project_for_al.model.data.inputdata;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class InputData {

    private String inputType;
    private Inputable inputObject;

}
