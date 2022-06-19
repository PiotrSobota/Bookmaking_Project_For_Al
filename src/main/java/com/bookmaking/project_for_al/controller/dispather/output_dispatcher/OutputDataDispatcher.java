package com.bookmaking.project_for_al.controller.dispather.output_dispatcher;

import com.bookmaking.project_for_al.model.data.enums.DataType;
import com.bookmaking.project_for_al.model.data.inputdata.other.InputDataContainer;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputBet;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputData;
import com.bookmaking.project_for_al.model.data.inputdata.types.InputResult;
import com.bookmaking.project_for_al.model.data.outputdata.OverallBalance;
import com.bookmaking.project_for_al.model.data.outputdata.PartialResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class OutputDataDispatcher {

    private static BigDecimal companyProfit = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    private static final HashSet<String> completedMatches = new HashSet<String>();
    private static final HashMap<String, PartialResult> partialResultMap = new HashMap<String, PartialResult>();
    private static final LinkedList<String> outputMessages = new LinkedList<>();

    public static Stream<String> getOutputMessagesStream() {
        InputDataContainer.getInputDataList()
                .forEach(OutputDataDispatcher::dispose);
        return outputMessages.stream();
    }

    private static void dispose(InputData inputData) {

        if (checkIfInputDataIsInType(inputData, DataType.BET)) {
            InputBet nextBet = (InputBet) inputData.getInputObject();

            if (completedMatches.isEmpty() || !completedMatches.contains(nextBet.getFixture())) {
                PartialResult newPartialResult = PartialResultCreator.createPartialResult(nextBet, partialResultMap);
                addNewPartialResultToMap(nextBet.getFixture(), newPartialResult);
                outputMessages.add(newPartialResult.toString());
            }

        } else if (checkIfInputDataIsInType(inputData, DataType.RESULT))
        {
            InputResult result = (InputResult) inputData.getInputObject();
            completedMatches.add(result.getFixture());
            OverallBalance overallBalanceForSelectedFixtureName = updateOverallBalanceForSelectedFixture(result);
            Optional.ofNullable(overallBalanceForSelectedFixtureName).ifPresent(OutputDataDispatcher::addOverallBalanceToOutput);
        }
    }

    private static OverallBalance updateOverallBalanceForSelectedFixture(InputResult result) {
        String searchedFixture = result.getFixture();

        try {
            PartialResult partialResult = partialResultMap.get(searchedFixture);

            BigDecimal finalResultBasedOnOutcome = partialResult.getFinalResultBasedOnOutcome(result.getResult());
            companyProfit = companyProfit.add(finalResultBasedOnOutcome);
            return new OverallBalance(companyProfit, searchedFixture, finalResultBasedOnOutcome);
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }

    private static void addOverallBalanceToOutput(OverallBalance overallBalanceForSelectedFixtureName)
    {
        outputMessages.add(overallBalanceForSelectedFixtureName.toString());
    }

    private static void addNewPartialResultToMap(String fixture, PartialResult newPartialResult) {
        partialResultMap.put(fixture, newPartialResult);
    }

    private static boolean checkIfInputDataIsInType(InputData inputData, DataType requiredType) {
        return inputData.getInputType()
                .equals(requiredType.getDataTypeDescription());
    }

}
