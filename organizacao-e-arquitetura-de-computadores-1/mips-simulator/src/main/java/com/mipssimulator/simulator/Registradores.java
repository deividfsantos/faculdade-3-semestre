package com.mipssimulator.simulator;

public class Registradores {
    private final long[] registradores;

    public Registradores() {
        this.registradores = new long[32];
    }

    public Long busca(String registrador) {// procura o registrador
        final int regCode = Integer.parseInt(registrador, 2);
        return registradores[regCode];
    }

    public void escreve(Long valor, String reg1, BlocoControle blocoControle) {// escreve em um registrador
        if (blocoControle.getEscReg().equals("1")) {
            final int regCode = Integer.parseInt(reg1, 2);
            registradores[regCode] = valor;
        }
        printRegistradores();
    }

    private void printRegistradores() {
        System.out.println("\nRegistradores:");
        for (int i = 0; i < registradores.length; i += 4) {
            if (i < 35) {
                System.out.printf("$%-2.10s: %-20.20s", i, registradores[i]);
                System.out.printf("$%-2.10s: %-20.20s", i + 1, registradores[i + 1]);
                System.out.printf("$%-2.10s: %-20.20s", i + 2, registradores[i + 2]);
                System.out.printf("$%-2.10s: %-20.20s", i + 3, registradores[i + 3]);
                System.out.println();
            }
        }
        System.out.println();
    }
}
