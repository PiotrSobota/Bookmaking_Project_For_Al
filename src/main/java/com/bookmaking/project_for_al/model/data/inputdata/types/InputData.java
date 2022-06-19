package com.bookmaking.project_for_al.model.data.inputdata.types;

import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Builder
@Getter
public class InputData {

    private String inputType;
    private Inputable inputObject; //Can store InputResult or InputBet object. Nested object in JSON file.

}
