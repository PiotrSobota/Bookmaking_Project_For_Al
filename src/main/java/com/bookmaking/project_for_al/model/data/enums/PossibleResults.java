package com.bookmaking.project_for_al.model.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PossibleResults {

    HOME_TEAM_WIN("1"), DRAW("X"), VISITING_TEAM_WIN("2");

    private final String result;

    //created for switch-case
    public static PossibleResults getPossibleResultBasedOnStringValue(String value) {
        for (PossibleResults possibleResult : PossibleResults.values()) {
            if (value.equals(possibleResult.getResult())) {
                return possibleResult;
            }
        }
        return null;
    }

}
