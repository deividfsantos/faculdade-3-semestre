package com.deividsantos.trabalhoii.graphs;

import com.deividsantos.trabalhoii.Castle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DFSPaths {
    private boolean marked[];
    private int edgeTo[];
    private int s;
    private List<Castle> castles;

    public DFSPaths(Graph g, int source, List<Castle> castles) {
        this.s = source;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.castles = castles;
        dfs(g, s);
    }

    public boolean hasPath(int w) {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int w) {
        if (!hasPath(w)) return null;
        LinkedList<Integer> res = new LinkedList<>();
        for (int x = w; x != s; x = edgeTo[x])
            res.addFirst(x);
        res.addFirst(s);
        return res;
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public void printAllPaths(Graph graph, int source, int destiny) {
        boolean[] isVisited = new boolean[graph.V()];
        List<Integer> pathList = new ArrayList<>();
        pathList.add(source);

        printAllPathsUtil(source, destiny, isVisited, pathList, graph);
    }

    private void printAllPathsUtil(Integer source, Integer destiny,
                                   boolean[] isVisited,
                                   List<Integer> localPathList,
                                   Graph g) {
        isVisited[source] = true;
        if (source.equals(destiny)) {
            System.out.println(localPathList);
            isVisited[source] = false;
            return;
        }
        for (Integer i : g.adj(source)) {
            if (!isVisited[i]) {
                localPathList.add(i);
                printAllPathsUtil(i, destiny, isVisited, localPathList, g);
                localPathList.remove(i);
            }
        }
        isVisited[source] = false;
    }

    private boolean hasEnoughArmySize(int w, int v) {
        return castles.get(w).getArmySize() * 2 + 100 < castles.get(v).getArmySize();
    }
}
