package com.mipssimulator.simulator;

public class Memoria {

    private final Integer[] memoria;

    public Memoria() {
        this.memoria = new Integer[50];
        memoria[2] = 15;
    }

    public Integer executa(Integer endereco, Integer dadosEscrito, BlocoControle blocoControle) {
        escreve(endereco, dadosEscrito, blocoControle);
        final Integer ler = ler(endereco, blocoControle);
        printMemoria();
        return ler;
    }

    public Integer ler(Integer endereco, BlocoControle blocoControle) {
        if (blocoControle.getLerMem().equals("1")) {
            final int enderecoAdaptado = (endereco - 0x400000) / 4;
            return memoria[enderecoAdaptado];
        }
        return endereco;
    }

    public void escreve(Integer valor, Integer endereco, BlocoControle blocoControle) {
        if (blocoControle.getEscMem().equals("1")) {
            memoria[endereco] = valor;
        }
    }

    private void printMemoria() {
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
}
