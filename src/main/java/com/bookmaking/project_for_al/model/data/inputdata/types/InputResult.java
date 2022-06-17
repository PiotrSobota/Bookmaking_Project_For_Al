package com.bookmaking.project_for_al.model.data.inputdata.types;

import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class InputResult implements Inputable {

    private String fixture;
    private char result;

}
