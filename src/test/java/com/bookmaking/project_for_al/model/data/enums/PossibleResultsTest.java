package com.bookmaking.project_for_al.model.data.enums;

import com.bookmaking.project_for_al.model.data.inputdata.other.InputDataContainer;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PossibleResultsTest {

    @Test
    void shouldGetPossibleResultBasedOnStringValueReturnCorrectValues() {

        //given
        String homeTeamWin = "1";
        String draw = "X";
        String visitingTeamWin = "2";

        //when
        PossibleResults homeTeamWinEnum = PossibleResults.getPossibleResultBasedOnStringValue(homeTeamWin);
        PossibleResults drawEnum = PossibleResults.getPossibleResultBasedOnStringValue(draw);
        PossibleResults visitingTeamWinEnum = PossibleResults.getPossibleResultBasedOnStringValue(visitingTeamWin);

        //then
        Assert.assertEquals(PossibleResults.HOME_TEAM_WIN, homeTeamWinEnum);
        Assert.assertEquals(PossibleResults.DRAW, drawEnum);
        Assert.assertEquals(PossibleResults.VISITING_TEAM_WIN, visitingTeamWinEnum);
    }

    @Test
    void shouldGetPossibleResultBasedOnStringValueReturnNullForWrongValue() {
        
        //given
        String wrongValue = "wrongValue";

        //when
        PossibleResults wrongValueEnum = PossibleResults.getPossibleResultBasedOnStringValue(wrongValue);

        //then
        Assertions.assertNull(wrongValueEnum);
    }
}