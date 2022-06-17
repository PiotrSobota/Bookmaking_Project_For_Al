package com.bookmaking.project_for_al.model.data.inputdata.types;

import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@ToString
public class InputBet implements Inputable {

    private String fixture;
    private String outcome;
    private BigDecimal stake;  //Should stay BigDecimal -> money
    private double odds;

}
