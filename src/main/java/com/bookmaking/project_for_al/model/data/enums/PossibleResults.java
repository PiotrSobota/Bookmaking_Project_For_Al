package com.bookmaking.project_for_al.model.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PossibleResults {

    //Possible results of match - important enum, for switch-case conditions in code

    HOME_TEAM_WIN("1"), DRAW("X"), VISITING_TEAM_WIN("2");

    private final String result;

    //created for switch-case conditions
    public static PossibleResults getPossibleResultBasedOnStringValue(String value) {
        for (PossibleResults possibleResult : PossibleResults.values()) {
            if (value.equals(possibleResult.getResult())) {
                return possibleResult;
            }
        }
        return null;
    }

}
