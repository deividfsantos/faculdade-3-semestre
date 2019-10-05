package com.deividsantos.trabalhoii;

import com.deividsantos.trabalhoii.graphs.Graph;

import java.util.List;

public class CastlePathBuilder {
    private boolean marked[];
    private Integer pathSize;
    private Graph g;
    private List<Castle> castles;
    private int bestPath;
    private int[] armys;
    private int[] paths;

    public CastlePathBuilder(Graph g, List<Castle> castles) {
        this.marked = new boolean[g.V()];
        this.g = g;
        this.castles = castles;
        this.pathSize = 0;
        this.bestPath = 0;
        this.armys = new int[castles.size()];
        this.paths = new int[castles.size()];
        buildBestPathSize();
    }

    public int getBestPath() {
        return bestPath;
    }

    private void buildBestPathSize() {
        for (Castle castle : castles) {
            buildBestPathSize(0, castle.getNumber(), g);
        }
    }

    private void buildBestPathSize(Integer source, Integer destiny, Graph g) {
        marked[source] = true;
        if (source.equals(destiny)) {
            if (bestPath < pathSize) {
                bestPath = pathSize;
            }
            marked[source] = false;
            return;
        }

        for (Integer i : g.adj(source)) {
            int originalArmySize = castles.get(i).getArmySize();
            if (!marked[i] && hasEnoughArmySize(source, i)) {
                int armySize = calculateArmySizeAfterAttack(source, i);
                if (armys[i] < armySize || pathSize > paths[i]) {
                    paths[i] = pathSize;
                    armys[i] = armySize;
                    castles.get(i).setArmySize(armySize);
                    pathSize++;
                    buildBestPathSize(i, destiny, g);
                    pathSize--;
                    castles.get(i).setArmySize(originalArmySize);
                }
            }
        }
        marked[source] = false;
    }

    private int calculateArmySizeAfterAttack(int source, int destiny) {
        Integer sourceArmySize = castles.get(source).getArmySize();
        Integer destinyArmySize = castles.get(destiny).getArmySize();
        return sourceArmySize - (destinyArmySize * 2) - 50;
    }

    private boolean hasEnoughArmySize(int source, int destiny) {
        return castles.get(destiny).getArmySize() * 2 + 100 < castles.get(source).getArmySize();
    }
}
