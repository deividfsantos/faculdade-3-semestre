package com.lfa.tf;

public class NonDeterministicState {
    private String sourceState;
    private String var;
    private String destinyState;

    public NonDeterministicState(String sourceState, String var, String destinyState) {
        this.sourceState = sourceState;
        this.var = var;
        this.destinyState = destinyState;
    }

    public String getSourceState() {
        return sourceState;
    }

    public void setSourceState(String sourceState) {
        this.sourceState = sourceState;
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getDestinyState() {
        return destinyState;
    }

    public void setDestinyState(String destinyState) {
        this.destinyState = destinyState;
    }

}
