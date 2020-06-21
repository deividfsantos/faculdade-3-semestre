package com.mipssimulator.simulator;

public class Memoria {

    private String[] memoria;

    public Memoria() {
        this.memoria = new String[50];
    }

    private String busca(int endereco) {
        final int enderecoAdaptado = (endereco - 0x400000) / 4;
        return memoria[enderecoAdaptado];
    }

    private void escreve(String valor, int endereco) {
        memoria[endereco] = valor;
    }
}
