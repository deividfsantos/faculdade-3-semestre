package com.deividsantos.warriorstribe;

import com.deividsantos.warriorstribe.model.Warrior;

import java.util.List;

class Warriors {

    private WarriorTree warriorTree;
    private List<String> warriorsAndSons;

    private static final Integer FATHER_NAME_INDEX = 0;
    private static final Integer WARRIOR_NAME_INDEX = 1;
    private static final Integer LANDS_NAME_INDEX = 2;
    private static final Integer LANDS_FROM_FIRST_WARRIOR_INDEX = 0;
    private static final Integer FIRST_WARRIOR_INDEX = 1;


    Warriors(List<String> warriorsFamily) {
        this.warriorsAndSons = warriorsFamily;
        this.warriorTree = getFirstWarrior(warriorsFamily);
        insertWarriors();
    }

    private WarriorTree getFirstWarrior(List<String> warriorsFamily) {
        return new WarriorTree(warriorsFamily.get(FIRST_WARRIOR_INDEX).split(" ")[FATHER_NAME_INDEX], Integer.valueOf(warriorsFamily.get(LANDS_FROM_FIRST_WARRIOR_INDEX)));
    }

    private void insertWarriors() {
        long startTime = System.currentTimeMillis();
        for (int i = 1; i < warriorsAndSons.size(); i++) {
            String[] line = warriorsAndSons.get(i).split(" ");
            warriorTree.add(line[FATHER_NAME_INDEX], line[WARRIOR_NAME_INDEX], Integer.valueOf(line[LANDS_NAME_INDEX]));
        }
        warriorTree.updateLandsWithFatherLands();
        long endTime = System.currentTimeMillis();
        System.out.println("Insertion time: " + (endTime - startTime));

    }

    Warrior getWarriorWithMostLands() {
        long startTime = System.currentTimeMillis();
        Warrior warriorWithMostLands = warriorTree.getWarriorWithMostLands();
        long endTime = System.currentTimeMillis();
        System.out.println("Search time: " + (endTime - startTime));
        return warriorWithMostLands;
    }
}
