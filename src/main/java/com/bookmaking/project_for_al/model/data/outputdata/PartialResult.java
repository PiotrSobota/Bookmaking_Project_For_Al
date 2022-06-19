package com.bookmaking.project_for_al.model.data.outputdata;

import com.bookmaking.project_for_al.model.data.enums.PossibleResults;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class PartialResult {

    private BigDecimal resultIfHomeTeamWin;
    private BigDecimal resultIfDraw;
    private BigDecimal resultIfVisitingTeamWin;
    private String fixture;

    //Chooses 1 of 3 possible result based of final real result/outcome
    public BigDecimal getFinalResultBasedOnOutcome(String outcome) {
        PossibleResults finalResultId = PossibleResults.getPossibleResultBasedOnStringValue(outcome);
        BigDecimal finalResult;

        switch (finalResultId) {
            case HOME_TEAM_WIN:
                finalResult = resultIfHomeTeamWin;
                break;
            case DRAW:
                finalResult = resultIfDraw;
                break;
            case VISITING_TEAM_WIN:
                finalResult = resultIfVisitingTeamWin;
                break;
            default:
                finalResult = null;
        }
        return finalResult.setScale(2, RoundingMode.HALF_UP);
    }

    //Formatter wasn't used because I think it would be totally illegible.
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        //Format => "1: 5.00 X: 5.00 2: -10.00"
        stringBuilder
                .append(PossibleResults.HOME_TEAM_WIN.getResult())
                .append(": ")
                .append(resultIfHomeTeamWin.setScale(2, RoundingMode.HALF_UP))
                .append(" ")
                .append(PossibleResults.DRAW.getResult())
                .append(": ")
                .append(resultIfDraw.setScale(2, RoundingMode.HALF_UP))
                .append(" ")
                .append(PossibleResults.VISITING_TEAM_WIN.getResult())
                .append(": ")
                .append(resultIfVisitingTeamWin.setScale(2, RoundingMode.HALF_UP))
                .append(" {fixture name: ")
                .append(fixture)
                .append("}");

        return stringBuilder.toString();
    }
}
