package com.mipssimulator.simulator;

public class Memoria {

    private final int[] memoria;
    // private int pc;

    public Memoria() {
        this.memoria = new int[50];
        memoria[2] = 15;
        memoria[0] = 0x35280064;
        //this.pc = 0x00400000;
    }

    public Integer executar(Integer endereco, Integer dadosEscrito, BlocoControle blocoControle) {
        escreve(endereco, dadosEscrito, blocoControle);
        final Integer ler = ler(endereco, blocoControle);
        printMemoria();
        return ler;
    }

    public Integer ler(Integer endereco, BlocoControle blocoControle) {
        if (blocoControle.getLerMem().equals("1")) {
            final int enderecoAdaptado = (endereco - 0x400000) / 0x4;
            return memoria[enderecoAdaptado];
        }
        return endereco;// caso contrario, ele retornara o endereco do input
    }

    public void escreve(Integer endereco, Integer valor, BlocoControle blocoControle) {
        if (blocoControle.getEscMem().equals("1")) {
            final int enderecoAdaptado = (endereco - 0x400000) / 0x4;
            memoria[enderecoAdaptado] = valor;
        }
    }

    private void printMemoria() {
        System.out.println("\nMemoria:");
        for (int i = 0; i < memoria.length; i++) {
            final int endereco = 0x400000 + i * 0x4;
            System.out.print(Integer.toHexString(endereco) + ": " + memoria[i] + "\t\t\t\t\t");
            if (i % 4 == 3) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
