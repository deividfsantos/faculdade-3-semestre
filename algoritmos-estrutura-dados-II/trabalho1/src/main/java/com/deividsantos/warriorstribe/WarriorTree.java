package com.deividsantos.warriorstribe;

import com.deividsantos.warriorstribe.model.Warrior;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class WarriorTree {

    private class Node {

        private Node father;
        private String warriorName;
        private LinkedList<Node> sons;
        private Integer lands;

        Node(String warriorName, Integer lands) {
            this.father = null;
            this.warriorName = warriorName;
            this.lands = lands;
            this.sons = new LinkedList<>();
        }

        void addSon(Node n) {
            n.father = this;
            sons.add(n);
        }

        Node getSon(int i) {
            if ((i < 0) || (i >= sons.size())) {
                throw new IndexOutOfBoundsException();
            }
            return sons.get(i);
        }

        int getSubtreesSize() {
            return sons.size();
        }

    }

    WarriorTree(String warriorName, Integer lands) {
        this.root = new Node(warriorName, lands);
    }

    private Node root;

    private Node searchNodeRef(String warriorName, Node target) {
        Node res = null;
        if (target != null) {
            if (warriorName.equals(target.warriorName)) {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while (aux == null && i < target.getSubtreesSize()) {
                    aux = searchNodeRef(warriorName, target.getSon(i));
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }

    void add(String father, String warriorName, Integer lands) {
        Node n = new Node(warriorName, lands);
        Node nAux;
        if (father == null) {
            if (root != null) {
                n.addSon(root);
                root.father = n;
            }
            root = n;
        } else {
            nAux = searchNodeRef(father, root);
            if (nAux != null) {
                nAux.addSon(n);
                n.father = nAux;
            }
        }
    }

    Warrior getWarriorWithMostLands() {
        List<Node> warriorsFromLastGeneration = new ArrayList<>();
        findAllLeafNodes(root, warriorsFromLastGeneration);
        Warrior bestWarrior = new Warrior(warriorsFromLastGeneration.get(0).warriorName, warriorsFromLastGeneration.get(0).father.warriorName, warriorsFromLastGeneration.get(0).lands);

        for (Node warrior : warriorsFromLastGeneration) {
            if (bestWarrior.getLands() < warrior.lands) {
                bestWarrior = new Warrior(warrior.warriorName, warrior.father.warriorName, warrior.lands);
            }
        }
        return bestWarrior;
    }

    private void findAllLeafNodes(Node warriorRoot, List<Node> warriorsFromLastGeneration) {
        if (warriorRoot != null) {
            if (warriorRoot.getSubtreesSize() == 0) {
                warriorsFromLastGeneration.add(warriorRoot);
            }
            for (int i = 0; i < warriorRoot.getSubtreesSize(); i++) {
                findAllLeafNodes(warriorRoot.getSon(i), warriorsFromLastGeneration);
            }
        }
    }

    void updateLandsWithFatherLands() {
        updateLandAux(root);
    }

    private void updateLandAux(Node root) {
        Integer lands = root.lands;
        for (int i = 0; i < root.getSubtreesSize(); i++) {
            root.getSon(i).lands += (lands / root.getSubtreesSize());
            updateLandAux(root.getSon(i));
        }
    }
}