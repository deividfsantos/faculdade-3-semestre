package com.mipssimulator.simulator;

import java.util.List;

public class Memoria {

    // Valor do LUI para simulador: 1000000 = 64 (4097 no Mars)
    // Memoria do mips = memoria do codigo + 264306548
    private final long[] memoria;

    public Memoria() {
        this.memoria = new long[52];
    }

    public void carregar(List<String> allLines) {
        int data = 0;
        data = carregarInstrucoes(allLines, data);
        carregarDados(allLines.subList(data + 1, allLines.size()));
    }

    private int carregarInstrucoes(List<String> allLines, int data) {
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).contains(".data")) {
                data = i;
                break;
            }
            memoria[i] = Long.parseLong(allLines.get(i).replace("0x", "").toUpperCase(), 16);
        }
        return data;
    }

    private void carregarDados(List<String> allLines) {
        for (int i = 0; i < allLines.size(); i++) {
            final int inicioMemoriaDados = 35;
            final String[] pontoData = allLines.get(i).trim().split(" ");
            memoria[i + inicioMemoriaDados] = Integer.parseInt(pontoData[pontoData.length - 1]);
        }
    }

    public Long executar(Long endereco, Long dadosEscrito, BlocoControle blocoControle) {// pega e escreve na memoria
        escreve(endereco, dadosEscrito, blocoControle);
        final Long ler = ler(endereco, blocoControle);
        printMemoria();
        return ler;
    }

    public Long ler(Long endereco, BlocoControle blocoControle) {
        if (blocoControle.getLerMem().equals("1")) {
            final Long enderecoAdaptado = (endereco - 0x400000) / 0x4;
            return memoria[enderecoAdaptado.intValue()];
        }
        return endereco;// caso contrario, ele retornara o endereco do input
    }

    public void escreve(Long endereco, Long valor, BlocoControle blocoControle) {
        if (blocoControle.getEscMem().equals("1")) {
            final Long enderecoAdaptado = (endereco - 0x400000) / 0x4;
            memoria[enderecoAdaptado.intValue()] = valor;
        }
    }

    private void printMemoria() {
        System.out.println("\nMemoria:");
        for (int i = 0; i < memoria.length; i += 4) {
            final int endereco = 0x400000 + i * 0x4;
            final int endereco1 = 0x400000 + (i + 1) * 0x4;
            final int endereco2 = 0x400000 + (i + 2) * 0x4;
            final int endereco3 = 0x400000 + (i + 3) * 0x4;
            if (i < 35) {
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco), memoria[i]);
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco1), memoria[i + 1]);
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco2), memoria[i + 2]);
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco3), memoria[i + 3]);
                System.out.println();
            } else {
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco), memoria[i]);
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco1), memoria[i + 1]);
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco2), memoria[i + 2]);
                System.out.printf("%-5.10s: %-20.20s", Integer.toHexString(endereco3), memoria[i + 3]);            // + 264306548
                System.out.println();
            }
        }
    }
}
