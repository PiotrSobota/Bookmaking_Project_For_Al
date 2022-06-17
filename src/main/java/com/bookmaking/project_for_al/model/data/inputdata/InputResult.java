package com.bookmaking.project_for_al.model.data.inputdata;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class InputResult implements Inputable{

    private String fixture;
    private char result;

}
