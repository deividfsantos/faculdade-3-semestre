package com.lfa.tf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Converter {

    public List<DeterministicState> convertAfnToAfd(List<NonDeterministicState> afn, String[] vars) {
        List<DeterministicState> afd = new ArrayList<>();
        String sourceState = afn.get(0).getSourceState();
        String var = afn.get(0).getVar();
        DeterministicState deterministicState = new DeterministicState(Collections.singleton(sourceState), var, new HashSet<>());
        generateStatesAux(afd, afn, vars, deterministicState);
        return removeUselessStates(afd);
    }

    private void generateStatesAux(List<DeterministicState> afd, List<NonDeterministicState> afn, String[] vars, DeterministicState deterministicState) {
        List<DeterministicState> tempDeterministicAutomaton = new ArrayList<>();
        for (String var : vars) {
            Set<String> destinys = getAllDestinys(afn, deterministicState, var);
            DeterministicState newstate = new DeterministicState(deterministicState.getSourceStates(), var, destinys);
            afd.add(newstate);
            tempDeterministicAutomaton.add(new DeterministicState(destinys, var, new HashSet<>()));
        }
        for (DeterministicState state : tempDeterministicAutomaton) {
            if (!state.getSourceStates().isEmpty() && !alreadyPassedThisState(afd, state)) {
                generateStatesAux(afd, afn, vars, state);
            }
        }
    }

    private List<DeterministicState> removeUselessStates(List<DeterministicState> afd) {
        return afd.stream()
                .filter(x -> !x.getDestinyStates().isEmpty())
                .collect(Collectors.toList());
    }

    private boolean alreadyPassedThisState(List<DeterministicState> afd, DeterministicState deterministicState) {
        for (int i = 0; i < afd.size(); i++) {
            if (afd.get(i).getSourceStates().equals(deterministicState.getSourceStates())) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getAllDestinys(List<NonDeterministicState> afn, DeterministicState deterministicState, String var) {
        Set<String> destinys = new HashSet<>();
        for (String source : deterministicState.getSourceStates()) {
            destinys.addAll(findDestinys(source, var, afn));
        }
        return destinys;
    }

    private Set<String> findDestinys(String source, String var, List<NonDeterministicState> afn) {
        Set<String> destinys = new HashSet<>();
        for (NonDeterministicState nonDeterministicState :
                afn) {
            if (source != null && source.equals(nonDeterministicState.getSourceState()) && var.equals(nonDeterministicState.getVar())) {
                destinys.add(nonDeterministicState.getDestinyState());
            }
        }
        return destinys;
    }
}