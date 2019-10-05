package com.lfa.tf;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AutomatonFormatter {

    public static void outputAutomaton(List<DeterministicState> afd, String[] vars, String startState, Set<String> allFinalStates) {
        Set<String> allStates = new HashSet<>();
        for (DeterministicState state : afd) {
            allStates.add(hashToStringTogether(state.getSourceStates()));
        }
        Set<String> varSet = new HashSet<>(Arrays.asList(vars));

        System.out.println("\nAutomato=({" + hashToStringWithComma(allStates) + "},{" + hashToStringWithComma(varSet) + "}," + startState + ",{" + hashToStringWithComma(allFinalStates) + "})");
        for (DeterministicState state : afd) {
            System.out.println(state);
        }
    }

    private static String hashToStringWithComma(Set<String> set) {
        String hashWithBracketsAndSpaces = set.toString();
        String hashWithoutSpaces = hashWithBracketsAndSpaces.replace(" ", "");
        String hashWithouBrackets = hashWithoutSpaces.replace("[", "");
        return hashWithouBrackets.replace("]", "");
    }

    private static String hashToStringTogether(Set<String> set) {
        String finalName = "";
        for (String state : set) {
            finalName = finalName + state;
        }
        return finalName;
    }
}
