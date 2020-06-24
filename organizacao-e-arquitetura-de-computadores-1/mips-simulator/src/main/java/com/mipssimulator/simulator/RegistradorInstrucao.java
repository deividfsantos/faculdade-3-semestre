package com.mipssimulator.simulator;

public class RegistradorInstrucao {
    private Registradores registradores;
    private BlocoControle blocoControle;

    public RegistradorInstrucao(BlocoControle blocoControle, Registradores registradores) {
        this.registradores = registradores;
        this.blocoControle = blocoControle;
    }

    private int busca(String instrucao) {
        blocoControle = new BlocoControle();
        if (instrucao.startsWith("addu")) {
            blocoControle.setUlaOp("10");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");

        }
        if (instrucao.startsWith("beq")) {
            blocoControle.setFontePC("01");
            blocoControle.setUlaOp("01");
            blocoControle.setUlaFonteB("00");
            blocoControle.setUlaFonteA("1");
            blocoControle.setPcEscCond("1");
            blocoControle.setPcEsc("0");
        }
        if (instrucao.startsWith("xor")){}
        if (instrucao.startsWith("lui")){}
        if (instrucao.startsWith("addiu")){}
        if (instrucao.startsWith("lw")){}
        if (instrucao.startsWith("sw")){}
        if (instrucao.startsWith("beq")){}
        if (instrucao.startsWith("bne")){}
        if (instrucao.startsWith("slt")){}
        if (instrucao.startsWith("ori")){}
        if (instrucao.startsWith("and")){}
        if (instrucao.startsWith("andi")){}
        if (instrucao.startsWith("sll")){}
        if (instrucao.startsWith("srl")){}
        return 0;
    }
}