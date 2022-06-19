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

    private static BigDecimal companyProfit = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP); //final company profit, starts with value 0
    private static final HashSet<String> completedMatches = new HashSet<String>(); //HashSet for fast search
    private static final HashMap<String, PartialResult> partialResultMap = new HashMap<String, PartialResult>(); //HashMap for fast search
    private static final LinkedList<String> outputMessages = new LinkedList<>(); //list of outputs


    /*Master method in this class. Should stay public.
    Gets data from InputDataContainer, then creates output messages dependent on InputData type*/

    public static Stream<String> getOutputMessagesStream() {
        InputDataContainer.getInputDataList().forEach(OutputDataDispatcher::dispose);
        return outputMessages.stream();
    }

    //Chooses what to do with next inputData objects,
    private static void dispose(InputData inputData) {

        if (checkIfInputDataIsInType(inputData, DataType.BET)) {

            //Gets InputBet object from InputData object
            InputBet nextBet = (InputBet) inputData.getInputObject();

            // Checks if match is already finished or not
            if (completedMatches.isEmpty() || !completedMatches.contains(nextBet.getFixture())) {
                //Checks if partialResult object exist in map, if not -> adds new result, if yes -> corrects result in map
                PartialResult newPartialResult = PartialResultCreator.createPartialResult(nextBet, partialResultMap);
                addNewPartialResultToMap(nextBet.getFixture(), newPartialResult);

                //Adds message to message list
                outputMessages.add(newPartialResult.toString());
            }

        } else if (checkIfInputDataIsInType(inputData, DataType.RESULT)) {

            //Gets InputBet object from InputData object
            InputResult result = (InputResult) inputData.getInputObject();

            // Checks if match is already finished or not
            if (!completedMatches.contains(result.getFixture())) {

                //Adds result of match to HashSet to stop recalculating result requests - match name HAVE TO BE UNIQUE!
                completedMatches.add(result.getFixture());

                //This method calculating financial result based on PartialResults and current financial balance
                OverallBalance overallBalanceForSelectedFixtureName = updateOverallBalanceForSelectedFixture(result);

                //Catches null. If present - update companyProfit, creates new OverallBalance, and adds it to output messages
                Optional.
                        ofNullable(overallBalanceForSelectedFixtureName)
                        .ifPresent(OutputDataDispatcher::addOverallBalanceToOutput);
            }
        }
    }

    //This method calculating financial result based on PartialResults and current financial balance
    private static OverallBalance updateOverallBalanceForSelectedFixture(InputResult result) {

        // Extraction of searched
        String searchedFixture = result.getFixture();

        try {
            //If partial results don't exist for this result - this: ...
            PartialResult partialResult = partialResultMap.get(searchedFixture);
            /*... could give null object (cause bet don't exist for this match result).
            And this... */
            BigDecimal finalResultBasedOnOutcome = partialResult.getFinalResultBasedOnOutcome(result.getResult());
            //will throw NullPointerException - we need just skip this result. That's why I used optional in dispose method

            companyProfit = companyProfit.add(finalResultBasedOnOutcome);
            return new OverallBalance(companyProfit, searchedFixture, finalResultBasedOnOutcome);
        } catch (NullPointerException e) {
            return null;
        }
    }


    private static void addOverallBalanceToOutput(OverallBalance overallBalanceForSelectedFixtureName) {
        outputMessages.add(overallBalanceForSelectedFixtureName.toString());
    }

    private static void addNewPartialResultToMap(String fixture, PartialResult newPartialResult) {
        partialResultMap.put(fixture, newPartialResult);
    }

    private static boolean checkIfInputDataIsInType(InputData inputData, DataType requiredType) {
        return inputData.getInputType().equals(requiredType.getDataTypeDescription());
    }

}
