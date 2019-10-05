package com.deividsantos.warriorstribe;

import com.deividsantos.warriorstribe.model.Warrior;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            System.out.println("\nInput the full file path:");
            File file = new File(scanner.next());
            long startTime = System.currentTimeMillis();
            long startReadTime = System.currentTimeMillis();
            List<String> warriorFileLines = Files.readAllLines(file.toPath());
            long endReadTime = System.currentTimeMillis();
            System.out.println("Reading time: " + (endReadTime - startReadTime));
            Warriors warriors = new Warriors(warriorFileLines);
            Warrior warriorWithMostLands = warriors.getWarriorWithMostLands();
            long endTime = System.currentTimeMillis();
            System.out.println("\nFilho com maior quantidade de terras: " + warriorWithMostLands);
            System.out.println("\nExecution time: " + (endTime - startTime));
        }
    }
}