package com.bookmaking.project_for_al.model.data.outputdata;

import com.bookmaking.project_for_al.model.data.enums.PossibleResults;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class PartialResult {

    private BigDecimal resultIfHomeTeamWin;
    private BigDecimal resultIfDraw;
    private BigDecimal resultIfVisitingTeamWin;
    private String fixture;

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
        return finalResult;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        //Format => "1: 5.00 X: 5.00 2: -10.00"
        stringBuilder
                .append(PossibleResults.HOME_TEAM_WIN.getResult())
                .append(": ")
                .append(resultIfHomeTeamWin.toString())
                .append(" ")
                .append(PossibleResults.DRAW.getResult())
                .append(": ")
                .append(resultIfDraw.toString())
                .append(" ")
                .append(PossibleResults.VISITING_TEAM_WIN.getResult())
                .append(": ")
                .append(resultIfVisitingTeamWin)
                .append(" {fixture name: ")
                .append(fixture)
                .append("}");

        return stringBuilder.toString();
    }
}
