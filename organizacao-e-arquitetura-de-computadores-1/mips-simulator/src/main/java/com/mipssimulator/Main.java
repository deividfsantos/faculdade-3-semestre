package com.mipssimulator;

import com.mipssimulator.simulator.BlocoControle;
import com.mipssimulator.simulator.Registradores;
import com.mipssimulator.simulator.Ula;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer line = 0x01698824;
        Ula ula = new Ula();
        Registradores registradores = new Registradores();
        BlocoControle blocoControle = new BlocoControle();

        String instructionBin =  Long.toBinaryString( Integer.toUnsignedLong(line) | 0x100000000L ).substring(1);
        String opCode = instructionBin.substring(0, 6);
        String reg1 = instructionBin.substring(6, 11);
        String reg2 = instructionBin.substring(11, 16);
        String reg3 = instructionBin.substring(16, 21);
        String func = instructionBin.substring(16, 32);

        blocoControle.defineOpcode(opCode);

        final Integer dadoRegA = registradores.busca(reg1);
        final Integer dadoRegB = registradores.busca(reg2);

        final int valor = ula.calcular(dadoRegA, dadoRegB, func.substring(10, 16));

        if (blocoControle.getRegDst().equals("0")) {
            registradores.escreve(valor, reg2, blocoControle.getEscReg());
        } else if (blocoControle.getRegDst().equals("1")) {
            registradores.escreve(valor, reg3, blocoControle.getEscReg());
        }
    }

    private static int calculateDisplacement(Integer instructionLine, List<String> allLines, String label) {
        int displacement = -1;
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).trim().startsWith(label)) {
                displacement = i - instructionLine;
            }
        }
        if (displacement == -1) {
            throw new RuntimeException("Label not found. Line: " + instructionLine);
        }
        return displacement;
    }


    private static void printMemoria(int[] memoria) {
        System.out.println("Memoria:");
        for (int i = 0; i < memoria.length; i++) {
            final int endereco = 0x400000 + i * 0x4;
            System.out.print(Integer.toHexString(endereco) + ": " + memoria[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
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