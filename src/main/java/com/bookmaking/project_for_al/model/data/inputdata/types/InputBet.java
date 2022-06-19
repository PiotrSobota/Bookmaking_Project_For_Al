package com.bookmaking.project_for_al.model.data.inputdata.types;

import com.bookmaking.project_for_al.model.data.inputdata.other.Inputable;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class InputBet implements Inputable {

    private String fixture;
    private String outcome;
    private BigDecimal stake;  //Should stay BigDecimal -> money
    private double odds;

}
