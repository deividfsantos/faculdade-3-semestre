package com.lfa.tf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatesBuilder {
    public static List<String> generateAllStates(String[] states) {
        Set<String> allStates = new HashSet<>();
        for (int i = 0; i < states.length; i++) {
            generateAllStates(states, allStates, states[i], i);
        }
        return orderList(allStates);
    }

    private static void generateAllStates(String[] states, Set<String> allStates, String state, int startIndex) {
        if (startIndex < states.length) {
            allStates.add(states[startIndex]);
        }
        for (int i = startIndex + 1; i < states.length; i++) {
            String newState = state + states[i];
            allStates.add(newState);
            generateAllStates(states, allStates, newState, i);
        }
    }

    public static List<String> generateAllFinalStates(List<String> allStates, String[] originalFinalStates) {
        Set<String> finalStates = new HashSet<>();
        for (String state : allStates) {
            for (String originalFinalState : originalFinalStates) {
                if (state.contains(originalFinalState)) {
                    finalStates.add(state);
                }
            }
        }
        return orderList(finalStates);
    }

    public static Set<String> buildFinalStatesFromAfd(List<String> allFinalStates, List<DeterministicState> d) {
        Set<String> finalStates = new HashSet<>();
        for (int i = 0; i < d.size(); i++) {
            if (allFinalStates.contains(hashToStringTogether(d.get(i).getSourceStates()))) {
                finalStates.add(hashToStringTogether(d.get(i).getSourceStates()));
            }
            if (allFinalStates.contains(hashToStringTogether(d.get(i).getDestinyStates()))) {
                finalStates.add(hashToStringTogether(d.get(i).getDestinyStates()));
            }
        }
        return finalStates;
    }

    private static String hashToStringTogether(Set<String> set) {
        String finalName = "";
        for (String state : set) {
            finalName = finalName + state;
        }
        return finalName;
    }

    private static List<String> orderList(Set<String> allStates) {
        List<String> all = new ArrayList<>(allStates);
        Collections.sort(all);
        all.sort(Comparator.comparing(String::length));
        return all;
    }
}
