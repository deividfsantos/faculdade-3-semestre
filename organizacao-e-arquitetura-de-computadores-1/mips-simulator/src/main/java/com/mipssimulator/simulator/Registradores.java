package com.mipssimulator.simulator;

public class Registradores {
    private final int[] registradores;

    public Registradores() {
        this.registradores = new int[32];
        registradores[8] = 0x400004;
        registradores[11] = 5;
        registradores[1] = 16;
        registradores[16] = 7;
        registradores[17] = 0x400010;
        registradores[21] = 9;
        registradores[9] = 20;
    }

    public Integer busca(String registrador) {// procura o registrador
        final int regCode = Integer.parseInt(registrador, 2);
        return registradores[regCode];
    }

    public void escreve(Integer valor, String reg1, BlocoControle blocoControle) {// escreve em um registrador
        if (blocoControle.getEscReg().equals("1")) {
            final int regCode = Integer.parseInt(reg1, 2);
            registradores[regCode] = valor;
        }
        printRegistradores();
    }

    private void printRegistradores() {// imprime todos os registradores
        System.out.println("\nRegistradores:");
        for (int i = 0; i < registradores.length; i++) {
            System.out.print("$" + i + ": " + registradores[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
}