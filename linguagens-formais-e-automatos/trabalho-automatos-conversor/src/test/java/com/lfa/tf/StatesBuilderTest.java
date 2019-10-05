package com.lfa.tf;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatesBuilderTest {

    @Test
    public void generateAllStatesTest() {
        String[] states = {"q0", "q1", "q2", "q3"};
        List<String> allStates = StatesBuilder.generateAllStates(states);
        assertEquals("q0", allStates.get(0));
        assertEquals("q1", allStates.get(1));
        assertEquals("q2", allStates.get(2));
        assertEquals("q3", allStates.get(3));
        assertEquals("q0q1", allStates.get(4));
        assertEquals("q0q2", allStates.get(5));
        assertEquals("q0q3", allStates.get(6));
        assertEquals("q1q2", allStates.get(7));
        assertEquals("q1q3", allStates.get(8));
        assertEquals("q2q3", allStates.get(9));
        assertEquals("q0q1q2", allStates.get(10));
        assertEquals("q0q1q3", allStates.get(11));
        assertEquals("q0q2q3", allStates.get(12));
        assertEquals("q1q2q3", allStates.get(13));
        assertEquals("q0q1q2q3", allStates.get(14));
        assertEquals(15, allStates.size());
    }

    @Test
    public void generateAllFinalStatesTest() {
        List<String> allStates = Arrays.asList("q0", "q1", "q2", "q3", "q0q1", "q0q2", "q0q3", "q1q2", "q1q3", "q2q3", "q0q1q2", "q0q1q3", "q0q2q3", "q1q2q3", "q0q1q2q3");
        String[] originalFinalStates = {"q1", "q3"};
        List<String> finalStates = StatesBuilder.generateAllFinalStates(allStates, originalFinalStates);
        assertEquals("q1", finalStates.get(0));
        assertEquals("q3", finalStates.get(1));
        assertEquals("q0q1", finalStates.get(2));
        assertEquals("q0q3", finalStates.get(3));
        assertEquals("q1q2", finalStates.get(4));
        assertEquals("q1q3", finalStates.get(5));
        assertEquals("q2q3", finalStates.get(6));
        assertEquals("q0q1q2", finalStates.get(7));
        assertEquals("q0q1q3", finalStates.get(8));
        assertEquals("q0q2q3", finalStates.get(9));
        assertEquals("q1q2q3", finalStates.get(10));
        assertEquals("q0q1q2q3", finalStates.get(11));
        assertEquals(12, finalStates.size());
    }
}
