package com.mipssimulator.simulator;

public class Ula {
    private int[] registradores;

    public Ula() {
        this.registradores = new int[32];
    }

    private int calcular(int dado1, int dado2, String operacao) {
        if (operacao.equals("01")) {
            return dado1 + dado2;
        }
        if (operacao.equals("11")) {
            return dado1 & dado2;
        }
        if (operacao.equals("10")) {
            return dado1 | dado2;
        }
        throw new RuntimeException();
    }
}
