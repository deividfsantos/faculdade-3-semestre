package com.deividsantos.trabalhoii;

import com.deividsantos.trabalhoii.graphs.In;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        In in = new In(args[0]);
        List<String> lines = Arrays.asList(in.readAllLines());
        Search search = new Search();
        String bestArmyWay = search.findBestArmyWay(lines);
        System.out.println(bestArmyWay);
    }
}
