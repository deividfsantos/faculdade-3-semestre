package com.mipssimulator;

import com.mipssimulator.simulator.BlocoControle;
import com.mipssimulator.simulator.Memoria;
import com.mipssimulator.simulator.Registradores;
import com.mipssimulator.simulator.Ula;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

//        Reader reader = new Reader();
//        String path = "";
//        List<String> programa = reader.readFile(path);
        //for (int i = 0; i < programa.size(); i++) {

        Integer line = 0x01618821;
        //Integer line = Integer.parseInt(programa.get(i));
        Ula ula = new Ula();
        Registradores registradores = new Registradores();
        BlocoControle blocoControle = new BlocoControle();
        Memoria memoria = new Memoria();
        String instructionBin = Long.toBinaryString(Integer.toUnsignedLong(line) | 0x100000000L).substring(1);
        //divisão de partes do opcode
        String opCode = instructionBin.substring(0, 6);// opcode da instrucao
        String reg1 = instructionBin.substring(6, 11);// rs
        String reg2 = instructionBin.substring(11, 16);// rt
        String reg3 = instructionBin.substring(16, 21);// rd
        String func = instructionBin.substring(16, 32);// func da funcao

        blocoControle.defineOpcode(opCode);// define os sinais do bloco de controle

        final Integer A = registradores.busca(reg1);// bloco A
        final Integer B = registradores.busca(reg2);// bloco B

        String valorEstendido = extensaoSinal(func);// extende o valor

        Integer muxA = muxA(blocoControle, A);// Guarda a saida do multiplexador entre A e a ula
        Integer muxB = muxB(blocoControle, B, valorEstendido);// Guarda a saida do multiplexador entre B e a ula

        final Integer valorUla = ula.calcular(muxA, muxB, func.substring(10, 16), blocoControle.getUlaOp());// guarda a saida da operacao da ula

        final String regEscrita = muxRegistradorEscrito(blocoControle, reg2, reg3);// registrador que sera escrito
        registradores.escreve(valorUla, regEscrita, blocoControle);// escreve sobre regEscrita

        final Integer endereco = muxPC(blocoControle, valorUla);
        final Integer registradorDadosMemoria = memoria.executar(endereco, B, blocoControle);

        final Integer dadoEscrita = muxDadoEscrito(blocoControle, registradorDadosMemoria, valorUla);// Guarda a saida do mux entre a bloco de dados da memoria e o bloco de registradores


        final String regEscrita1 = muxRegistradorEscrito(blocoControle, reg2, reg3);// Guarda a saida do mux entre o registrador de instrucoes e o bloco de registradores
        registradores.escreve(dadoEscrita, regEscrita1, blocoControle);// escreve no dado a ser escrita

        //}
    }

    //implementação do bloco extensão de sinal, adiciona 16 bits à esquerda do valor recebido
    private static String extensaoSinal(String func) {
        return "0000000000000000" + func;
    }

    //implementação do multiplexador que da entrada no bloco de registradores como "Reg a ser escrito"
    //retorna reg0(n sei oq é) se RegDst estiver ligado e reg1(n sei tb) caso contrário
    private static String muxRegistradorEscrito(BlocoControle blocoControle, String reg0, String reg1) {
        if (blocoControle.getRegDst().equals("1")) {
            return reg0;
        } else if (blocoControle.getRegDst().equals("0")) {
            return reg1;
        }
        return null;
    }

    //implementação do multiplexador que da entrada no bloco de registradores como "Dado a ser escrito"
    //retorna registrador de dados da memória caso MemParaReg do BC seja 1, caso contrário, retorna o valor da Ula Saída
    private static Integer muxDadoEscrito(BlocoControle blocoControle, Integer valorRegistradorDadosMemoria, Integer valorUla) {
        if (blocoControle.getMemParaReg().equals("1")) {
            return valorRegistradorDadosMemoria;
        } else if (blocoControle.getMemParaReg().equals("0")) {
            return valorUla;
        }
        return null;
    }

    //implementação do multiplexador que recebe do PC
    //caso louD esteja ligado, passa o valor da ula saída para a memória, caso contrário passará um valor novo
    private static Integer muxPC(BlocoControle blocoControle, Integer valorUla) {
        if (blocoControle.getLouD().equals("1")) {
            return valorUla;
        }
        return null;
    }

    //implementação do multiplexador que recebe uma entrada do bloco B
    //retorna o valor de B caso ULAFonteB do BC seja 00, 4 se 01, o valor de sinal estendido se 10 e o valor com 2 bits desligados se 11
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

    //implementação do multiplexador que recebe uma entrada do bloco A
    //retorna o valor de A caso ULAFonteA seja 1, caso contrário retorna PC
    private static Integer muxA(BlocoControle blocoControle, Integer a) {
        if (blocoControle.getUlaFonteA().equals("1")) {
            return a;
        }
        return null;
    }
}