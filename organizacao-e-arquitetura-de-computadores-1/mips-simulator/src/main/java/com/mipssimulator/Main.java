package com.mipssimulator;

import com.mipssimulator.simulator.BlocoControle;
import com.mipssimulator.simulator.Memoria;
import com.mipssimulator.simulator.Registradores;
import com.mipssimulator.simulator.Ula;

public class Main {
    public static void main(String[] args) {
        Integer line = 0x8d090004;
        Ula ula = new Ula();
        Registradores registradores = new Registradores();
        BlocoControle blocoControle = new BlocoControle();
        Memoria memoria = new Memoria();

        String instructionBin = Long.toBinaryString(Integer.toUnsignedLong(line) | 0x100000000L).substring(1);
        String opCode = instructionBin.substring(0, 6);
        String reg1 = instructionBin.substring(6, 11);
        String reg2 = instructionBin.substring(11, 16);
        String reg3 = instructionBin.substring(16, 21);
        String func = instructionBin.substring(16, 32);

        blocoControle.defineOpcode(opCode);

        final Integer A = registradores.busca(reg1);
        final Integer B = registradores.busca(reg2);

        String valorEstendido = extensaoSinal(func);

        Integer muxA = muxA(blocoControle, A);
        Integer muxB = muxB(blocoControle, B, valorEstendido);

        final Integer valorUla = ula.calcular(muxA, muxB, func.substring(10, 16), blocoControle.getUlaOp());

        final String regEscrita = muxRegistradorEscrito(blocoControle, reg2, reg3);
        registradores.escreve(valorUla, regEscrita, blocoControle);

        final Integer endereco = muxPC(blocoControle, valorUla);
        final Integer registradorDadosMemoria = memoria.ler(endereco, blocoControle);

        final Integer dadoEscrita = muxDadoEscrito(blocoControle, registradorDadosMemoria, valorUla);

        final String regEscrita1 = muxRegistradorEscrito(blocoControle, reg2, reg3);
        registradores.escreve(dadoEscrita, regEscrita1, blocoControle);
    }

    private static String extensaoSinal(String func) {
        return "0000000000000000" + func;
    }

    private static String muxRegistradorEscrito(BlocoControle blocoControle, String reg0, String reg1) {
        if (blocoControle.getRegDst().equals("1")) {
            return reg0;
        } else if (blocoControle.getRegDst().equals("0")) {
            return reg1;
        }
        return null;
    }

    private static Integer muxDadoEscrito(BlocoControle blocoControle, Integer valorRegistradorDadosMemoria, Integer valorUla) {
        if (blocoControle.getMemParaReg().equals("1")) {
            return valorRegistradorDadosMemoria;
        } else if (blocoControle.getMemParaReg().equals("0")) {
            return valorUla;
        }
        return null;
    }

    private static Integer muxPC(BlocoControle blocoControle, Integer valorUla) {
        if (blocoControle.getLouD().equals("1")) {
            return valorUla;
        }
        return null;
    }

    private static Integer muxB(BlocoControle blocoControle, Integer b, String valorEstendido) {
        if (blocoControle.getUlaFonteB().equals("00")) {
            return b;
        } else if (blocoControle.getUlaFonteB().equals("01")) {
            return 4;
        } else if (blocoControle.getUlaFonteB().equals("10")) {
            return Integer.valueOf(valorEstendido, 2);
        }
        return null;
    }

    private static Integer muxA(BlocoControle blocoControle, Integer a) {
        if (blocoControle.getUlaFonteA().equals("1")) {
            return a;
        }
        return null;
    }



    private static void printRegistradores(int[] bancoDeRegistradores) {
        System.out.println("Registradores:");
        for (int i = 0; i < bancoDeRegistradores.length; i++) {
            System.out.print("$" + i + ": " + bancoDeRegistradores[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
}