package com.mipssimulator.simulator;

public class Registradores {
    private int[] registradores;
    private String r1;
    private String r2;
    private String r3;

    public Registradores() {
        this.registradores = new int[32];
    }

    private int busca(String registrador) {
        int reg = Integer.parseInt(registrador.replace("$", ""));
        return registradores[reg];
    }

    private void escreve(int valor, String registrador) {
        int reg = Integer.parseInt(registrador.replace("$", ""));
        registradores[reg] = valor;
    }
}
