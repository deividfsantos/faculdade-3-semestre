package com.mipssimulator;

import com.mipssimulator.simulator.BlocoControle;

public class Main {
    public static void main(String[] args) {
        String line = "addu $7, $9, $10";
        BlocoControle blocoControle = new BlocoControle();
        int[] bancoDeRegistradores = new int[32];
        printRegistradores(bancoDeRegistradores);
        System.out.println(blocoControle);
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
    }

    private static void printRegistradores(int[] bancoDeRegistradores) {
        System.out.println("Registradores:");
        for (int i = 0; i < bancoDeRegistradores.length; i++) {
            System.out.print("$" + i + ": " + bancoDeRegistradores[i] + "\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
}