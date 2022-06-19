package com.bookmaking.project_for_al.model.data.outputdata;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class PartialResultTest {

    @ParameterizedTest
    @CsvSource({"11.00,22.00,33.00,1: 11.00 X: 22.00 2: 33.00 {fixture name: value}",
            "11,22,33,1: 11.00 X: 22.00 2: 33.00 {fixture name: value}",
            "11.001123,22.00231111,33.002111,1: 11.00 X: 22.00 2: 33.00 {fixture name: value}",
            "-11.001223,-22.00231111,-33.00211,1: -11.00 X: -22.00 2: -33.00 {fixture name: value}"})
    void shouldToStringReturnCorrectOutputs(BigDecimal resultIfHomeTeamWin,
                                            BigDecimal resultIfDraw,
                                            BigDecimal resultIfVisitingTeamWin,
                                            String expectedResult) {

        //given
        String exampleValue = "value";
        PartialResult partialResult = new PartialResult(resultIfHomeTeamWin, resultIfDraw, resultIfVisitingTeamWin, exampleValue);

        //when
        String toStringResult = partialResult.toString();

        //then
        Assert.assertEquals(expectedResult, toStringResult);
    }

    @ParameterizedTest
    @CsvSource({
            "11.00,22.00,33.00,1,11.00",
            "11.00,22.00,33.00,X,22.00",
            "11.00,22.00,33.00,2,33.00",
            "11,22,33,1,11.00",
            "11,22,33,X,22.00",
            "11,22,33,2,33.00",
            "11.001123,22.00231111,33.002111,1,11.00",
            "11.00122,22.00231111,33.00212,X,22.00",
            "11.001223,22.00231111,33.00211,2,33.00",
            "-11.001123,-22.00231111,-33.002111,1,-11.00",
            "-11.00122,-22.00231111,-33.00212,X,-22.00",
            "-11.001223,-22.00231111,-33.00211,2,-33.00"})
    void shouldGetFinalResultBasedOnOutcomeReturnCorrectValue(BigDecimal resultIfHomeTeamWin,
                                                              BigDecimal resultIfDraw,
                                                              BigDecimal resultIfVisitingTeamWin,
                                                              String outcome,
                                                              BigDecimal expectedResult) {

        //Given
        PartialResult partialResult = new PartialResult(resultIfHomeTeamWin, resultIfDraw, resultIfVisitingTeamWin, "value");

        //When
        BigDecimal finalResultBasedOnOutcome = partialResult.getFinalResultBasedOnOutcome(outcome);

        //Then
        Assert.assertEquals(expectedResult, finalResultBasedOnOutcome);
    }


}