package com.mipssimulator;

public class Trash {

    int pc = 0;
    String[] registradores = new String[32];
    String[] memoria = new String[64];
    String opUla;

    private String blocoMemoria(int endereco) {
        return memoria[endereco];
    }

    private String[] blocoRegistradorInstrucao(String instrucao) {

        String[] registradores;
        if (instrucao.startsWith("addu")) {
            opUla = "addu";
            instrucao.replace("addu", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("addiu")) {
            opUla = "addiu";
            instrucao.replace("addiu", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("and")) {
            opUla = "and";
            instrucao.replace("and", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("andi")) {
            opUla = "andi";
            instrucao.replace("andi", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("xor")) {
            opUla = "xor";
            instrucao.replace("xor", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("sll")) {
            opUla = "sll";
            instrucao.replace("sll", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("srl")) {
            opUla = "srl";
            instrucao.replace("srl", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("beq")) {
            opUla = "beq";
            instrucao.replace("beq", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("bne")) {
            opUla = "bne";
            instrucao.replace("bne", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("lui")) {
            opUla = "lui";
            instrucao.replace("lui", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("ori")) {
            opUla = "ori";
            instrucao.replace("ori", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("slt")) {
            opUla = "slt";
            instrucao.replace("slt", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("sw")) {
            opUla = "sw";
            instrucao.replace("sw", "");
            registradores = instrucao.split(",");

            return registradores;
        } else if (instrucao.startsWith("lw")) {
            opUla = "lw";
            instrucao.replace("lw", "");
            registradores = instrucao.split(",");

            return registradores;
        }
        return null;
    }

    private String[] blocoRegistradores(String[] registradores) {
        String lidoA, lidoB, escreveC;
        String[] regs = new String[2];
        escreveC = registradores[0];
        int posA, posB;
        posA = Integer.parseInt(registradores[2].replace("$", ""));
        posB = Integer.parseInt(registradores[1].replace("$", ""));
        lidoA = registradores[posA];
        lidoB = registradores[posB];
        regs[0] = lidoA;
        regs[1] = lidoB;
        return regs;
    }

    private String blocoUla(int a, int b) {
        if (opUla.equals("addu") || opUla.startsWith("addiu")) {
            int res = a + b;
            return Integer.toString(res);
        } else if (opUla.startsWith("xor")) {
            int res = a ^ b;
            return Integer.toString(res);
        } else if (opUla.startsWith("and") || opUla.startsWith("andi")) {
            int res = a & b;
            return Integer.toString(res);
        } else if (opUla.startsWith("slt")) {
            return Integer.toString(Integer.compare(a, b));
        } 
        return null;
    }
}
