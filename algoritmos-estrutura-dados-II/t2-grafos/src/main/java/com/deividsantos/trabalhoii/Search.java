package com.deividsantos.trabalhoii;

import com.deividsantos.trabalhoii.graphs.Graph;

import java.util.List;

public class Search {

    private static final int SIBERIAN_ARMY_INDEX = 0;
    private static final int NEARBY_CASTLES_INDEX = 1;
    private static final int ROADS_INDEX = 2;

    public String findBestArmyWay(List<String> warText) {
        String[] firstLine = warText.get(0).split(" ");
        int siberianArmySize = Integer.valueOf(firstLine[SIBERIAN_ARMY_INDEX]);
        int nearbyCastlesNumber = Integer.valueOf(firstLine[NEARBY_CASTLES_INDEX]);

        List<Castle> castles = GraphBuilder.createCastles(warText, siberianArmySize, nearbyCastlesNumber);
        Graph castleGraph = GraphBuilder.createGraph(warText, nearbyCastlesNumber);

        CastlePathBuilder castleDFS = new CastlePathBuilder(castleGraph, castles);
        int bestPath = castleDFS.getBestPath();
        return "Total de possibilidades de conquistas do exercito: " + bestPath;
    }
}
