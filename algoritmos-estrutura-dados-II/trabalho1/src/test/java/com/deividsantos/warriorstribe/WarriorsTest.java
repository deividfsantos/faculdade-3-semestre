package com.deividsantos.warriorstribe;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WarriorsTest {

    @Test
    public void warriorWithMostEarthsTest() {
        List<String> warriorsAndSons = Arrays.asList("103787",
                "Thorgestax Deldriralex 4626",
                "Thorgestax Jocgnibardyx 3530",
                "Jocgnibardyx Diorflimhikox 5154",
                "Jocgnibardyx Grutohiaux 5797",
                "Jocgnibardyx Docmangax 4471",
                "Jocgnibardyx Delscatorflex 6180",
                "Jocgnibardyx Cristipix 5299",
                "Jocgnibardyx Pacmonumicynax 2056",
                "Thorgestax Alteetoflex 6042",
                "Thorgestax Delrenmax 6080",
                "Delrenmax Diormanclox 4736",
                "Delrenmax Neppanpix 2249",
                "Delrenmax Klodrimanrix 5353",
                "Delrenmax Mirtpliblepkox 3177",
                "Thorgestax Frinvinulamax 3189");
        Warriors warriors = new Warriors(warriorsAndSons);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Alteetoflex'\n" +
                "Father: Thorgestax'\n" +
                "Lands: 26799", warriors.getWarriorWithMostLands().toString());
    }

    @Test
    public void testCaseMC4b() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC4b.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Thorcrivergex'\n" +
                "Father: Frindritornix'\n" +
                "Lands: 18119", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }


    @Test
    public void testCaseMC8a() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC8a.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Mirthibleptrigax'\n" +
                "Father: Prirenstax'\n" +
                "Lands: 11051", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }

    @Test
    public void testCaseMC8b() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC8b.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Tripbloclox'\n" +
                "Father: Gorramonaux'\n" +
                "Lands: 11346", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }


    @Test
    public void testCaseMC10a() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC10a.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Primannox'\n" +
                "Father: Triptroverdyx'\n" +
                "Lands: 21871", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }


    @Test
    public void testCaseMC10b() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC10b.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Diordenhivermax'\n" +
                "Father: Kloratex'\n" +
                "Lands: 11331", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }


    @Test
    public void testCaseMC12a() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC12a.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Croncrinox'\n" +
                "Father: Diorgepox'\n" +
                "Lands: 11553", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }



    @Test
    public void testCaseMC12b() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC12b.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Morgverstiflex'\n" +
                "Father: Crieeulagex'\n" +
                "Lands: 10893", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }


    @Test
    public void testCaseMC14a() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC14a.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Jocbloverrarix'\n" +
                "Father: Preptoscaptix'\n" +
                "Lands: 12384", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }

    @Test
    public void testCaseMC14b() throws IOException {
        File file = new File(this.getClass().getClassLoader().getResource("cases/casoMC14b.txt").getFile());
        long startTime = System.currentTimeMillis();
        List<String> family = Files.readAllLines(file.toPath());
        Warriors warriors = new Warriors(family);
        assertEquals("\n" +
                "Warrior\n" +
                "Name: Gortriplikix'\n" +
                "Father: Croncapmancynax'\n" +
                "Lands: 12086", warriors.getWarriorWithMostLands().toString());
        long endTime = System.currentTimeMillis();
        System.out.println("\nExecution time: " + (endTime - startTime));
    }

}