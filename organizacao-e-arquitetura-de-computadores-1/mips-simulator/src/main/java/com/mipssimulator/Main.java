package com.mipssimulator;

import com.mipssimulator.simulator.BlocoControle;
import com.mipssimulator.simulator.Memoria;
import com.mipssimulator.simulator.Registradores;
import com.mipssimulator.simulator.Ula;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

//        Reader reader = new Reader();
//        String path = "";
//        List<String> programa = reader.readFile(path);
        //for (int i = 0; i < programa.size(); i++) {

        List<String> allLines = Arrays.asList(("0x25490001\n" +
                "0x256a0002\n" +
                "0x01495821\n" +
                "0x01696026\n" +
                "0xae300000\n" +
                "0x8e310000\n" +
                "0x012b6824\n" +
                "0x012a282a").split("\n"));

        allLines.removeIf(line -> line.startsWith("#"));
        allLines.removeIf(line -> line == null || line.trim().isEmpty());
        Ula ula = new Ula();
        Registradores registradores = new Registradores();
        Memoria memoria = new Memoria();
        memoria.carregarInstrucoes(allLines);

        Integer pc = 0x400000;
        for (int i = 0; i < allLines.size(); i++) {
            System.out.println("Executando linha: " + (i + 1));
            BlocoControle blocoControle = new BlocoControle();
            //Etapa 1

            final Integer enderecoPC = muxPC(0, pc, blocoControle);
            final Integer valorMemoriaPC = memoria.executar(enderecoPC, 0, blocoControle);

            Integer muxPCA = muxA(blocoControle, 0, pc);// Guarda endereco de Pc que eh passado pelo mux para avancar Pc
            Integer muxPCB = muxB(blocoControle, 0, "");// Guarda o valor 4 para a ula somar com o endereco de Pc

            final String ulaOP = ula.operacaoUla("", "", blocoControle);// q faz isso?
            pc = ula.calcular(muxPCA, muxPCB, ulaOP);// faz pc +=4 para avancar no programa

            //Etapa 2
            String instructionBin = Long.toBinaryString(Integer.toUnsignedLong(valorMemoriaPC) | 0x100000000L).substring(1);
            //divisão de partes do opcode
            String opCode = instructionBin.substring(0, 6);// opcode da instrucao
            String reg1 = instructionBin.substring(6, 11);// rd
            String reg2 = instructionBin.substring(11, 16);// rt
            String reg3 = instructionBin.substring(16, 21);// rs
            String func = instructionBin.substring(16, 32);// ultimos 16 bits (imediato/func)

            blocoControle.defineOpcode(opCode);// define os sinais do bloco de controle

            final Integer A = registradores.busca(reg1);// bloco A
            final Integer B = registradores.busca(reg2);// bloco B

            String valorEstendido = extensaoSinal(func);// extende o valor

            //Etapa 2-3
            Integer muxA = muxA(blocoControle, A, pc);// Guarda a saida do multiplexador entre A e a ula
            Integer muxB = muxB(blocoControle, B, valorEstendido);// Guarda a saida do multiplexador entre B e a ula

            final String opUla = ula.operacaoUla(func.substring(10, 16), opCode, blocoControle);
            final Integer resultadoUla = ula.calcular(muxA, muxB, opUla);// guarda a saida da operacao da ula

            //Etapa 3
            final String regEscrita = muxRegistradorEscrito(blocoControle, reg2, reg3);// registrador que sera escrito
            registradores.escreve(resultadoUla, regEscrita, blocoControle);// escreve sobre regEscrita

            defineOpCodeEtapa3(blocoControle, opCode);

            final Integer endereco = muxPC(resultadoUla, pc, blocoControle);
            final Integer registradorDadosMemoria = memoria.executar(endereco, B, blocoControle);

            final Integer dadoEscrita = muxDadoEscrito(blocoControle, registradorDadosMemoria, resultadoUla);// Guarda a saida do mux entre a bloco de dados da memoria e o bloco de registradores

            final String regEscrita1 = muxRegistradorEscrito(blocoControle, reg2, reg3);// Guarda a saida do mux entre o registrador de instrucoes e o bloco de registradores
            registradores.escreve(dadoEscrita, regEscrita1, blocoControle);// escreve no dado a ser escrita
        }
    }

    private static void defineOpCodeEtapa3(BlocoControle blocoControle, String opCode) {
        final String tipoAddiuOpCode = "001001";
        final String tipoAndiOpCode = "001100";
        final String tipoLuiOpCode = "001111";
        final String tipoOriOpCode = "001101";
        if (opCode.equals(tipoAddiuOpCode) || opCode.equals(tipoAndiOpCode) || opCode.equals(tipoLuiOpCode)
                || opCode.equals(tipoOriOpCode)) {
            blocoControle.setEscReg("1");
            blocoControle.setRegDst("1");
        }
    }

    //implementação do bloco extensão de sinal, adiciona 16 bits à esquerda do valor recebido
    private static String extensaoSinal(String func) {
        if (func.startsWith("111111")) {
            return "1111111111111111" + func;
        }
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
    private static Integer muxPC(Integer valorUla, Integer valorPC, BlocoControle blocoControle) {
        if (blocoControle.getIouD().equals("1")) {
            return valorUla;
        } else {
            return valorPC;
        }
    }

    //implementação do multiplexador que recebe uma entrada do bloco B
    //retorna o valor de B caso ULAFonteB do BC seja 00, 4 se 01, o valor de sinal estendido se 10 e o valor com 2 bits desligados se 11
    private static Integer muxB(BlocoControle blocoControle, Integer b, String valorEstendido) {
        if (blocoControle.getUlaFonteB().equals("00")) {
            return b;
        } else if (blocoControle.getUlaFonteB().equals("01")) {
            return 4;
        } else if (blocoControle.getUlaFonteB().equals("10")) {
            return Integer.parseInt(valorEstendido, 2);
        }
        return null;
    }

    //implementação do multiplexador que recebe uma entrada do bloco A
    //retorna o valor de A caso ULAFonteA seja 1, caso contrário retorna PC
    private static Integer muxA(BlocoControle blocoControle, Integer a, Integer valorPc) {
        if (blocoControle.getUlaFonteA().equals("1")) {
            return a;
        } else {
            return valorPc;
        }
    }
}
