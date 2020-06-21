package com.mipssimulator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String line = "sw $10, 4($8)";
        List<String> lines = Arrays.asList("sw $10, 4($8)", "beq $10, $9, teste");
        int[] memoria = new int[10];
        memoria[1] = 123;
        BlocoControle blocoControle = new BlocoControle();
        int[] bancoDeRegistradores = new int[32];
        int pc = 0x400000;

        bancoDeRegistradores[8] = 0x400000;
        bancoDeRegistradores[10] = 6;
        printRegistradores(bancoDeRegistradores);
        printMemoria(memoria);
        System.out.println(blocoControle);

        pc += 0x4;
        if (line.startsWith("addu")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("addu ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource1 = Integer.parseInt(registradores[1]);
            int registerSource2 = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = bancoDeRegistradores[registerSource1] + bancoDeRegistradores[registerSource2];

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("and")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("and ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource1 = Integer.parseInt(registradores[1]);
            int registerSource2 = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = bancoDeRegistradores[registerSource1] & bancoDeRegistradores[registerSource2];

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("xor")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("xor ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource1 = Integer.parseInt(registradores[1]);
            int registerSource2 = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = bancoDeRegistradores[registerSource1] ^ bancoDeRegistradores[registerSource2];

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("slt")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("slt ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource1 = Integer.parseInt(registradores[1]);
            int registerSource2 = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = Integer.compare(bancoDeRegistradores[registerSource1], bancoDeRegistradores[registerSource2]);

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }

        if (line.startsWith("lui")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("lui ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int sourceValue = Integer.parseInt(registradores[1]);

            bancoDeRegistradores[registerDestino] = sourceValue;

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("ori")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("ori ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource = Integer.parseInt(registradores[1]);
            int sourceValue = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = bancoDeRegistradores[registerSource] | sourceValue;

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("addiu")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("addiu ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource = Integer.parseInt(registradores[1]);
            int sourceValue = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = bancoDeRegistradores[registerSource] + sourceValue;

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("andi")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            final String registradoresConcat = line
                    .replace("andi ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource = Integer.parseInt(registradores[1]);
            int sourceValue = Integer.parseInt(registradores[2]);

            bancoDeRegistradores[registerDestino] = bancoDeRegistradores[registerSource] & sourceValue;

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("lw")) {
            blocoControle.setLerMem("1");
            blocoControle.setLouD("1");
            final String registradoresConcat = line
                    .replace("lw ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);

            String origem = registradores[1];
            int registradorOrigem = Integer.parseInt(origem.substring(origem.indexOf('(') + 1, origem.indexOf(')')));
            int offset = Integer.parseInt(origem.substring(0, origem.indexOf('(')), 16);
            final int enderecoMemoriaLocal = (bancoDeRegistradores[registradorOrigem] - 0x400000) + offset / 4;
            bancoDeRegistradores[registerDestino] = memoria[enderecoMemoriaLocal];

            printRegistradores(bancoDeRegistradores);
            System.out.println(blocoControle);
        }
        if (line.startsWith("sw")) {
            blocoControle.setEscMem("1");
            blocoControle.setLouD("1");
            final String registradoresConcat = line
                    .replace("sw ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registradorOrigem = Integer.parseInt(registradores[0]);

            String destino = registradores[1];
            int registradorDestino = Integer.parseInt(destino.substring(destino.indexOf('(') + 1, destino.indexOf(')')));
            int offset = Integer.parseInt(destino.substring(0, destino.indexOf('(')), 16);
            final int enderecoMemoriaLocal = (bancoDeRegistradores[registradorDestino] - 0x400000) + offset / 4;

            memoria[enderecoMemoriaLocal] = bancoDeRegistradores[registradorOrigem];

            System.out.println();
            printRegistradores(bancoDeRegistradores);
            printMemoria(memoria);
            System.out.println(blocoControle);
        }

        if (line.startsWith("beq")) {
            blocoControle.setEscMem("1");
            blocoControle.setLouD("1");
            final String registradoresConcat = line
                    .replace("beq ", "")
                    .replace(" ", "")
                    .replace("$", "");
            final String[] registradores = registradoresConcat.split(",");
            int registerDestino = Integer.parseInt(registradores[0]);
            int registerSource = Integer.parseInt(registradores[1]);
            String label = registradores[2];

            if (bancoDeRegistradores[registerDestino] == bancoDeRegistradores[registerSource]) {
                final int displacement = calculateDisplacement(0, lines, label);
                pc += displacement * 0x4;
            }

            System.out.println();
            printRegistradores(bancoDeRegistradores);
            printMemoria(memoria);
            System.out.println(blocoControle);
        }
    }

    private static int calculateDisplacement(Integer instructionLine, List<String> allLines, String label) {
        int displacement = -1;
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).trim().startsWith(label)) {
                displacement = i - instructionLine;
            }
        }
        if (displacement == -1) {
            throw new RuntimeException("Label not found. Line: " + instructionLine);
        }
        return displacement;
    }


    private static void printMemoria(int[] memoria) {
        System.out.println("Memoria:");
        for (int i = 0; i < memoria.length; i++) {
            final int endereco = 0x400000 + i * 0x4;
            System.out.print(Integer.toHexString(endereco) + ": " + memoria[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }

    private static void printRegistradores(int[] bancoDeRegistradores) {
        System.out.println("Registradores:");
        for (int i = 0; i < bancoDeRegistradores.length; i++) {
            System.out.print("$" + i + ": " + bancoDeRegistradores[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
}