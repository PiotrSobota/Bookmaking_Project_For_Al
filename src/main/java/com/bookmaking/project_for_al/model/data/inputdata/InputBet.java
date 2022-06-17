package com.bookmaking.project_for_al.model.data.inputdata;

import lombok.Builder;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@ToString
public class InputBet implements Inputable{

    private String fixture;
    private String outcome;
    private BigDecimal stake;
    private double odds;

}
