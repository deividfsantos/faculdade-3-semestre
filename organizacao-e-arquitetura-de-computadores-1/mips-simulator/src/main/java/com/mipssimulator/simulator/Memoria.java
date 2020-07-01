package com.mipssimulator.simulator;

import java.util.List;

public class Memoria {

    private final int[] memoria;

    public Memoria() {
        this.memoria = new int[50];
    }

    public void carregarInstrucoes(List<String> allLines) {
        int data = 0;
        for (int i = 0; i < allLines.size(); i++) {
            memoria[i] = Long.valueOf(Long.parseLong(allLines.get(i).replace("0x", "").toUpperCase(), 16)).intValue();
            if (allLines.get(i).contains(".data")) {
                data = i;
                return;
            }
        }
        for (int i = data + 1; i < allLines.size(); i++) {
            final int inicioMemoria = 35;
            final String[] pontoData = allLines.get(i).trim().split(" ");
            memoria[i + inicioMemoria] = Integer.parseInt(pontoData[pontoData.length - 1]);
        }
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
