package com.lfa.tf;

import java.util.ArrayList;
import java.util.List;

public class AutomatonBuilder {

    private static final int SOURCE_STATE_INDEX = 0;
    private static final int VAR_INDEX = 1;

    public static List<NonDeterministicState> buildAFN(List<String> afnLines) {
        List<NonDeterministicState> afn = new ArrayList<>();
        for (String line : afnLines) {
            String state = line.substring(line.indexOf("(")+1, line.indexOf(")"));
            String[] source = state.split(",");
            String destiny = line.substring(line.indexOf("=") + 1);
            afn.add(new NonDeterministicState(source[SOURCE_STATE_INDEX], source[VAR_INDEX], destiny));
        }
        return afn;
    }
}
