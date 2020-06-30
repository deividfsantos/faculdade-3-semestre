package com.mipssimulator.simulator;

public class Memoria {

    private final Integer[] memoria;
   // private int pc;

    public Memoria() {
        this.memoria = new Integer[50];
        memoria[2] = 15;
        //this.pc = 0x00400000;
    }

    public Integer ler(Integer endereco, BlocoControle blocoControle) {// Esta funcao ira pegar e retornar o conteudo da memoria
        if (blocoControle.getLerMem().equals("1")) {// Se o sinal de lerMem estiver ligado ele lera e retornara o conteuda da memoria
            final int enderecoAdaptado = (endereco - 0x400000) / 4;
            return memoria[enderecoAdaptado];
        }
        return endereco;// caso contrario, ele retornara o endereco do input
    }

    public void escreve(Integer valor, int endereco) {
        memoria[endereco] = valor;
    }// escreve o valor na memoria

//    public void incrementaPc() {
//        this.pc += 4;
//    }

    private static void printMemoria(int[] memoria) {// imprime a memoria
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
