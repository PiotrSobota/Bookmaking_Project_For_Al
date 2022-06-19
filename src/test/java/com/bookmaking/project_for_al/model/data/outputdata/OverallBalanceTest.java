package com.bookmaking.project_for_al.model.data.outputdata;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class OverallBalanceTest {

    @ParameterizedTest
    @CsvSource({"222.448443,A-B,20,Overall balance: 222.45 Fixture A-B profit: 20.00",
                "0,A-B,20.999,Overall balance: 0.00 Fixture A-B profit: 21.00",
                "-20.098,A-B,-990,Overall balance: -20.10 Fixture A-B profit: -990.00",
                "-20,A-B,-990.233, Overall balance: -20.00 Fixture A-B profit: -990.23"})
    void shouldToStringReturnCorrectOutputs(String overallBalance, String fixture, String profit, String expectedOutput) {

        //given
        BigDecimal overallBalanceDec = new BigDecimal(overallBalance);
        BigDecimal profitDec = new BigDecimal(profit).setScale(5, RoundingMode.HALF_UP);
        OverallBalance overallBalanceObject = new OverallBalance(overallBalanceDec, fixture, profitDec);

        //when
        String toStringResult = overallBalanceObject.toString();

        //then
        Assert.assertEquals(expectedOutput, toStringResult);
    }

}