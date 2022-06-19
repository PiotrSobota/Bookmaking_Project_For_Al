package com.bookmaking.project_for_al.model.data.outputdata;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Formatter;
import java.util.Locale;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class OverallBalance {

    private BigDecimal overallBalance;
    private String fixture;
    private BigDecimal profit;

    @Override
    public String toString() {

        StringBuffer stringBuffer = new StringBuffer();
        Formatter formatter = new Formatter(stringBuffer, Locale.GERMAN);

        // Format => Overall balance: 10.00 Fixture A - B profit: 10.00
        return formatter.format("Overall balance: %s Fixture %s profit: %s",
                        overallBalance.setScale(2, RoundingMode.HALF_UP),
                        fixture,
                        profit.setScale(2, RoundingMode.HALF_UP))
                .toString();
    }

}
