package com.bookmaking.project_for_al.controller.dispather.output_dispatcher;

import com.bookmaking.project_for_al.model.data.enums.PossibleResults;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.outputdata.PartialResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public abstract class PartialResultCreator {

    final static BigDecimal INITIAL_VALUE = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    public static PartialResult createPartialResult(InputBet inputBet, HashMap<String, PartialResult> partialResultMap) {
        PartialResult existingPartialResult;
        String newBetFixtureName = inputBet.getFixture();

        PartialResult newPartialResult = createNewPartialResult(inputBet);

        if (checkIfPartialResultAlreadyExistInMap(newBetFixtureName, partialResultMap)) {
            existingPartialResult = getExistingPartialResult(newBetFixtureName, partialResultMap);
            newPartialResult = getCorrectedPartialResult(newPartialResult, existingPartialResult);
        }

        return newPartialResult;
    }

    private static PartialResult createNewPartialResult(InputBet inputBet) {

        BigDecimal stake = inputBet.getStake();
        BigDecimal productOfStakeAndOdds = multiplyStakeByOdds(stake, inputBet.getOdds());
        BigDecimal subtractionResultOfStakeAndProduct = stake.subtract(productOfStakeAndOdds);

        BigDecimal homeTeamWin = INITIAL_VALUE, draw = INITIAL_VALUE, visitingTeamWin = INITIAL_VALUE;

        PossibleResults inputBetOutcome = PossibleResults.getPossibleResultBasedOnStringValue(inputBet.getOutcome());

        switch (inputBetOutcome) {
            case HOME_TEAM_WIN:
                homeTeamWin = subtractionResultOfStakeAndProduct; draw = stake; visitingTeamWin = stake;
                break;
            case DRAW:
                draw = subtractionResultOfStakeAndProduct; homeTeamWin = stake; visitingTeamWin = stake;
                break;
            case VISITING_TEAM_WIN:
                visitingTeamWin = subtractionResultOfStakeAndProduct; homeTeamWin = stake; draw = stake;
                break;
        }
        return new PartialResult(homeTeamWin, draw, visitingTeamWin, inputBet.getFixture());
    }

    private static PartialResult getCorrectedPartialResult(PartialResult newPartialResult, PartialResult existingPartialResult) {

        BigDecimal correctedHomeTeamWin, correctedDraw, correctedVisitingTeamWin;

        correctedHomeTeamWin = newPartialResult.getResultIfHomeTeamWin().add(existingPartialResult.getResultIfHomeTeamWin());
        correctedDraw = newPartialResult.getResultIfDraw().add(existingPartialResult.getResultIfDraw());
        correctedVisitingTeamWin = newPartialResult.getResultIfVisitingTeamWin().add(existingPartialResult.getResultIfVisitingTeamWin());

        return new PartialResult(correctedHomeTeamWin, correctedDraw, correctedVisitingTeamWin, newPartialResult.getFixture());
    }

    private static BigDecimal multiplyStakeByOdds(BigDecimal stake, double odds) {
        BigDecimal oddsBigDecimal = BigDecimal.valueOf(odds);
        return stake.multiply(oddsBigDecimal).setScale(2, RoundingMode.HALF_UP);
    }

    private static PartialResult getExistingPartialResult(String newBetFixtureName, HashMap<String, PartialResult> partialResultMap) {
        return partialResultMap.get(newBetFixtureName);
    }

    private static boolean checkIfPartialResultAlreadyExistInMap(String newBetFixtureName, HashMap<String, PartialResult> partialResultMap) {
        return partialResultMap.containsKey(newBetFixtureName);
    }

}
