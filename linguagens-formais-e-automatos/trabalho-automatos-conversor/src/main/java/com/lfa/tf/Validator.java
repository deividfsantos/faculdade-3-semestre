package com.lfa.tf;

import java.util.List;
import java.util.Set;

public class Validator {
    private List<DeterministicState> afd;
    private String startState;
    private Set<String> finalStates;

    public Validator(List<DeterministicState> afd, String startState, Set<String> finalStates) {
        this.afd = afd;
        this.startState = startState;
        this.finalStates = finalStates;
    }

    public Boolean validateWord(String word) {
        return validateWord(0, word, startState);
    }

    private Boolean validateWord(int index, String word, String startState) {
        if (word.length() == 0) {
            return finalStates.contains(startState);
        }

        DeterministicState state = findState(startState, String.valueOf(word.charAt(index)));
        if (index + 1 == word.length()) {
            if (state == null) {
                return false;
            }
            if (finalStates.contains(hashToString(state.getDestinyStates()))) {
                return true;
            }
        }

        if (state == null || index + 1 == word.length()) {
            return false;
        }

        return validateWord(index + 1, word, hashToString(state.getDestinyStates()));
    }

    public DeterministicState findState(String state, String var) {
        for (DeterministicState df : afd) {
            String sourceStates = hashToString(df.getSourceStates());
            if (state.equals(sourceStates) && df.getVar().equals(var)) {
                return df;
            }
        }
        return null;
    }

    public String hashToString(Set<String> states) {
        String state = "";
        for (String hashState : states) {
            state = state + hashState;
        }
        return state;
    }
}
