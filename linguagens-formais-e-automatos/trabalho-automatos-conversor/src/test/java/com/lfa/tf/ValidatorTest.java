package com.lfa.tf;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void validateWord() {
        Converter conversor = new Converter();
        List<NonDeterministicState> nonDeterministicAutomaton = buildDeterministicAutomaton();
        String[] vars = {"a", "b"};
        List<DeterministicState> deterministicStates = conversor.convertAfnToAfd(nonDeterministicAutomaton, vars);
        Validator validator = new Validator(deterministicStates, "q0", new HashSet<>(Arrays.asList("q1", "q2q3")));
        assertFalse(validator.validateWord("abav"));
        assertFalse(validator.validateWord(""));
        assertFalse(validator.validateWord("b"));
        assertFalse(validator.validateWord("aa"));
        assertFalse(validator.validateWord("aaaaa"));
        assertFalse(validator.validateWord("bb"));
        assertFalse(validator.validateWord("baaaaaab"));
        assertFalse(validator.validateWord("baab"));
        assertFalse(validator.validateWord("baaaab"));
        assertFalse(validator.validateWord("abb"));
        assertFalse(validator.validateWord("aabaaaaa"));
        assertFalse(validator.validateWord("ab"));
        assertFalse(validator.validateWord("abb"));
        assertFalse(validator.validateWord("bab"));
        assertTrue(validator.validateWord("a"));
        assertTrue(validator.validateWord("aba"));
        assertTrue(validator.validateWord("abaaaaaaaa"));
        assertTrue(validator.validateWord("baba"));
        assertTrue(validator.validateWord("babaaaaa"));
        assertTrue(validator.validateWord("ababababababa"));
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