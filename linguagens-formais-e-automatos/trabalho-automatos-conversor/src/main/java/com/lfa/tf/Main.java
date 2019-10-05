package com.lfa.tf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static com.lfa.tf.AutomatonFormatter.outputAutomaton;

public class Main {

    private static final int STATES_INDEX = 1;
    private static final int VARS_INDEX = 3;
    private static final int START_STATE_INDEX = 4;
    private static final int FINAL_STATES_INDEX = 5;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nInput the full file path:");
        File file = new File(scanner.next());
        List<String> afnLines = Files.readAllLines(file.toPath());

        String grammarLine = afnLines.get(0);
        String stateLine = grammarLine.substring(grammarLine.indexOf("(") + 1, grammarLine.indexOf(")"));
        String[] grammar = stateLine.split("\\},|,\\{|\\{|\\}");

        String startState = grammar[START_STATE_INDEX];
        String[] varList = grammar[VARS_INDEX].split(",");

        List<NonDeterministicState> nonDeterministicStates = AutomatonBuilder.buildAFN(afnLines.subList(2, afnLines.size()));
        List<DeterministicState> afd = convertAfnToAfd(nonDeterministicStates, varList);
        Set<String> finalStatesFromAfd = getFinalStates(grammar[STATES_INDEX], grammar[FINAL_STATES_INDEX], afd);

        outputAutomaton(afd, varList, startState, finalStatesFromAfd);

        Validator validator = new Validator(afd, startState, finalStatesFromAfd);
        while (true) {
            System.out.println("\nInput a word to validate: ");
            if(validator.validateWord(scanner.next())){
                System.out.println("Aceita");
            }else {
                System.out.println("Rejeita");
            }
        }
    }

    private static Set<String> getFinalStates(String state, String finalStates, List<DeterministicState> afd) {
        List<String> allStates = StatesBuilder.generateAllStates(state.split(","));
        List<String> allFinalStates = StatesBuilder.generateAllFinalStates(allStates, finalStates.split(","));
        return StatesBuilder.buildFinalStatesFromAfd(allFinalStates, afd);
    }

    private static List<DeterministicState> convertAfnToAfd(List<NonDeterministicState> nonDeterministicStates, String[] varList) {
        Converter converter = new Converter();
        return converter.convertAfnToAfd(nonDeterministicStates, varList);
    }

}
