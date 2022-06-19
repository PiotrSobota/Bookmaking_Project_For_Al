package com.bookmaking.project_for_al.model.data.inputdata.types;

import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Builder
@Getter
public class InputResult implements Inputable {

    //Representation of nested JSON object

    private String fixture;
    private String result;

}
