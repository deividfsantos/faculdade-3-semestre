package com.mipssimulator.simulator;

public class Registradores {
    private int[] registradores;

    public Registradores() {
        this.registradores = new int[32];
        registradores[11] = 10;
        registradores[9] = 8;
    }

    public Integer busca(String registrador) {
        final int regCode = Integer.parseInt(registrador, 2);
        return registradores[regCode];
    }

    public void escreve(int valor, String reg1, String escReg) {
        if (escReg.equals("1")) {
            final int regCode = Integer.parseInt(reg1, 2);
            registradores[regCode] = valor;
        }
        printRegistradores();
    }

    private void printRegistradores() {
        System.out.println("Registradores:");
        for (int i = 0; i < registradores.length; i++) {
            System.out.print("$" + i + ": " + registradores[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
