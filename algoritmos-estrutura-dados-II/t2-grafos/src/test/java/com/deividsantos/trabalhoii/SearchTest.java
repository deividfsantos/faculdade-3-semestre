package com.deividsantos.trabalhoii;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchTest {

    @Test
    public void caseMainExampleTest() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("mainexample.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 1", bestWay);
    }

    @Test
    public void caseCastleTest() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("castles.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 0", bestWay);
    }

    @Test
    public void case30Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso30.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 8", bestWay);
    }

    @Test
    public void case32Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso32.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 8", bestWay);
    }

    @Test
    public void case34Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso34.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 8", bestWay);
    }

    @Test
    public void case36Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso36.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 9", bestWay);
    }

    @Test
    public void case38Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso38.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 11", bestWay);
    }

    @Test
    public void case40Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso40.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 10", bestWay);
    }

    @Test
    public void case42Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso42.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 11", bestWay);
    }

    @Test
    public void case44Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso44.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 11", bestWay);
    }

    @Test
    public void case46Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso46.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 13", bestWay);
    }

    @Test
    public void case48Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso48.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 13", bestWay);
    }

    @Test
    public void case50Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso50.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 13", bestWay);
    }

    @Test
    public void case60Test() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("casos-t2/caso60.txt").getFile());
        List<String> warText = Files.readAllLines(file.toPath());
        Search search = new Search();
        String bestWay = search.findBestArmyWay(warText);
        assertEquals("Total de possibilidades de conquistas do exercito: 16", bestWay);
    }
}
