package com.mipssimulator.simulator;

public class RegistradorInstrucao {
    private int[] registradores;
    private BlocoControle blocoControle;

    public RegistradorInstrucao(BlocoControle blocoControle) {
        this.registradores = new int[32];
        this.blocoControle = blocoControle;
    }

    private int busca(String instrucao) {
        blocoControle = new BlocoControle();
        if (instrucao.startsWith("addu")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");

        }
        return 0;
    }
}