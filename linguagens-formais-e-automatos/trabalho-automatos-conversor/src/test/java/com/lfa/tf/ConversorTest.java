package com.lfa.tf;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConversorTest {

    @Test
    public void convertAfnToAfd() {
        Converter conversor = new Converter();
        List<NonDeterministicState> nonDeterministicAutomaton = buildDeterministicAutomaton();
        String[] vars = {"a", "b"};
        List<DeterministicState> deterministicStates = conversor.convertAfnToAfd(nonDeterministicAutomaton, vars);
        String expected = "[(q0,a)=q1, " +
                "(q0,b)=q2, " +
                "(q1,b)=q2, " +
                "(q2,a)=q2q3, " +
                "(q2q3,a)=q2q3, " +
                "(q2q3,b)=q2]";
        assertEquals(expected, deterministicStates.toString());
    }

    private List<NonDeterministicState> buildDeterministicAutomaton() {
        NonDeterministicState deterministicState = new NonDeterministicState("q0", "a", "q1");
        NonDeterministicState deterministicState1 = new NonDeterministicState("q0", "b", "q2");
        NonDeterministicState deterministicState2 = new NonDeterministicState("q1", "b", "q2");
        NonDeterministicState deterministicState3 = new NonDeterministicState("q2", "a", "q3");
        NonDeterministicState deterministicState4 = new NonDeterministicState("q2", "a", "q2");
        NonDeterministicState deterministicState5 = new NonDeterministicState("q3", "a", "q3");
        NonDeterministicState deterministicState6 = new NonDeterministicState("q3", "b", "q2");
        return Arrays.asList(deterministicState, deterministicState1,
                deterministicState2, deterministicState3, deterministicState4, deterministicState5, deterministicState6);
    }
}