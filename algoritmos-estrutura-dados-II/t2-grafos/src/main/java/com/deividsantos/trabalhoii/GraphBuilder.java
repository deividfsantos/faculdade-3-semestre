package com.deividsantos.trabalhoii;

import com.deividsantos.trabalhoii.graphs.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {

    public static List<Castle> createCastles(List<String> castleGraphLines, int siberianArmySize, int nearbyCastlesNumber) {
        List<Castle> castles = new ArrayList<>();
        castles.add(new Castle(0, siberianArmySize));
        for (int i = 1; i < nearbyCastlesNumber + 1; i++) {
            String[] castleLine = castleGraphLines.get(i).split(" ");
            Castle castle = new Castle(Integer.valueOf(castleLine[0]), Integer.valueOf(castleLine[1]));
            castles.add(castle);
        }
        return castles;
    }

    public static Graph createGraph(List<String> castleGraphLines, int nearbyCastlesNumber) {
        Graph castleGraph = new Graph(nearbyCastlesNumber + 1);
        List<String> edges = castleGraphLines.subList(nearbyCastlesNumber + 1, castleGraphLines.size());
        for (int i = 0; i < edges.size(); i++) {
            String[] castlesEdge = edges.get(i).split(" ");
            int castle1 = Integer.valueOf(castlesEdge[0]);
            int castle2 = Integer.valueOf(castlesEdge[1]);
            castleGraph.addEdge(castle1, castle2);
        }
        return castleGraph;
    }
}
