package com.lfa.tf;

import java.util.Set;

public class DeterministicState {
    private Set<String> sourceStates;
    private String var;
    private Set<String> destinyStates;

    public DeterministicState(Set<String> sourceStates, String var, Set<String> destinyState) {
        this.sourceStates = sourceStates;
        this.var = var;
        this.destinyStates = destinyState;
    }

    public Set<String> getSourceStates() {
        return sourceStates;
    }

    public void setSourceStates(Set<String> sourceStates) {
        this.sourceStates = sourceStates;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public Set<String> getDestinyStates() {
        return destinyStates;
    }

    public void setDestinyStates(Set<String> destinyStates) {
        this.destinyStates = destinyStates;
    }

    @Override
    public String toString() {
        String destinyState = "";
        for (String state : destinyStates) {
            destinyState = destinyState + state;
        }

        String sourceState = "";
        for (String state : sourceStates) {
            sourceState = sourceState + state;
        }
        return "(" + sourceState + "," + var + ")=" + destinyState;
    }
}
