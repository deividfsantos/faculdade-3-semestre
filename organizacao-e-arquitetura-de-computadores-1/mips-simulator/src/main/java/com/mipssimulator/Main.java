package com.mipssimulator;

import com.mipssimulator.file.Reader;
import com.mipssimulator.simulator.BlocoControle;
import com.mipssimulator.simulator.Memoria;
import com.mipssimulator.simulator.Registradores;
import com.mipssimulator.simulator.Ula;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


//        0x00400000  0x3c011001  lui $1,4097           1    la $s0, A
//        0x00400004  0x34300000  ori $16,$1,0
//        0x00400008  0x25490001  addiu $9,$10,1        2    addiu $t1,$t2,1
//        0x0040000c  0x256a0002  addiu $10,$11,2       3    addiu $t2,$t3,2
//        0x00400010  0x01495821  addu $11,$10,$9       4    addu $t3,$t2,$t1
//        0x00400014  0x01696026  xor $12,$11,$9        5    xor $t4,$t3,$t1
//        0x00400018  0xae0b0000  sw $11,0($16)         6    sw $t3,0($s0)
//        0x0040001c  0x8e110000  lw $17,0($16)         7    lw $s1,0($s0)
//        0x00400020  0x312d0001  andi $13,$9,1         8    and $t5,$t1,1
//        0x00400024  0x0129702a  slt $14,$9,$9         9    slt $t6,$t1,$t1


//        0x00400000  0x3c011001  lui $1,4097           1    la $s0, A
//        0x00400004  0x34300000  ori $16,$1,0
//        0x00400008  0x3c011001  lui $1,4097           2    la $t7, B
//        0x0040000c  0x342f0004  ori $15,$1,4
//        0x00400010  0x3c011001  lui $1,4097           3    la $t8, C
//        0x00400014  0x34380008  ori $24,$1,8
//        0x00400018  0x25490001  addiu $9,$10,1        4    addiu $t1,$t2,1
//        0x0040001c  0x256a0002  addiu $10,$11,2       5    addiu $t2,$t3,2
//        0x00400020  0x01495821  addu $11,$10,$9       6    addu $t3,$t2,$t1
//        0x00400024  0x01696026  xor $12,$11,$9        7    xor $t4,$t3,$t1
//        0x00400028  0xae0b0000  sw $11,0($16)         8    sw $t3,0($s0)
//        0x0040002c  0xae040004  sw $4,4($16)          9    sw $a0,4($s0)
//        0x00400030  0xaf050000  sw $5,0($24)          10   sw $a1,0($t8)
//        0x00400034  0x8e110000  lw $17,0($16)         11   lw $s1,0($s0)
//        0x00400038  0x312d0001  andi $13,$9,1         12   and $t5,$t1,1
//        0x0040003c  0x0129702a  slt $14,$9,$9         13   slt $t6,$t1,$t1


//        4194304  0x3c011001  lui $1,4097           1    la $s0, A
//        4194308  0x34300000  ori $16,$1,0
//        4194312  0x3c011001  lui $1,4097           2    la $t7, B
//        4194316  0x342f0004  ori $15,$1,4
//        4194320  0x3c011001  lui $1,4097           3    la $t8, C
//        4194324  0x34380008  ori $24,$1,8
//        4194328  0x25490001  addiu $9,$10,1        4    addiu $t1,$t2,1
//        4194332  0x256a0002  addiu $10,$11,2       5    addiu $t2,$t3,2
//        4194336  0x01495821  addu $11,$10,$9       6    addu $t3,$t2,$t1
//        4194340  0x01696026  xor $12,$11,$9        7    xor $t4,$t3,$t1
//        4194344  0xae0b0000  sw $11,0($16)         8    sw $t3,0($s0)
//        4194348  0x8e040004  lw $4,4($16)          9    lw $a0,4($s0)
//        4194352  0x8f050000  lw $5,0($24)          10   lw $a1,0($t8)
//        4194356  0x8e110000  lw $17,0($16)         11   lw $s1,0($s0)
//        4194360  0x312d0001  andi $13,$9,1         12   and $t5,$t1,1
//        4194364  0x0129702a  slt $14,$9,$9         13   slt $t6,$t1,$t1

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        Scanner sc = new Scanner(System.in);
        Ula ula = new Ula();
        Registradores registradores = new Registradores();
        Memoria memoria = new Memoria();

        System.out.println("Digite o caminho do arquivo: ");
        String filePath = sc.nextLine();
        List<String> linhas = reader.readFile(filePath);

        linhas.removeIf(line -> line.startsWith("#"));
        linhas.removeIf(line -> line == null || line.trim().isEmpty());

        memoria.carregar(linhas);

        Long pc = 0x400000L;
        boolean printMenu = true;
        for (int i = 0; i < linhas.size() && !linhas.get(i).contains(".data"); i++) {
            System.out.println("\n\u001B[34m" + "Executando linha: " + (i + 1) + "\u001B[0m");
            BlocoControle blocoControle = new BlocoControle();

            //Etapa 1
            final Long enderecoPC = muxPC(0L, pc, blocoControle);// Saida do mux do pc que eh o proprio endereco de pc
            final Long valorMemoriaPC = memoria.executar(enderecoPC, 0L, blocoControle);// leitura e escrita na memoria

            Long muxPCA = muxA(blocoControle, 0L, 0L, pc, "");// Guarda endereco de Pc que eh passado pelo mux para avancar Pc
            Long muxPCB = muxB(blocoControle, 0L, "", "");// Guarda o valor 4 para a ula somar com o endereco de Pc

            final String ulaOP = ula.operacaoUla("", "", blocoControle);// Define operacao da ula
            pc = ula.calcular(muxPCA, muxPCB, ulaOP);// faz pc +=4 para avancar no programa

            //Etapa 2
            String instructionBin = Long.toBinaryString(Integer.toUnsignedLong(valorMemoriaPC.intValue()) | 0x100000000L).substring(1);
            //divisão de partes do opcode
            String opCode = instructionBin.substring(0, 6);// opcode da instrucao
            String reg1 = instructionBin.substring(6, 11);// rs
            String reg2 = instructionBin.substring(11, 16);// rt
            String reg3 = instructionBin.substring(16, 21);// rd
            String immediate = instructionBin.substring(16, 32);// ultimos 16 bits (imediato/func)

            final String funct = immediate.substring(10, 16);
            blocoControle.defineOpcode(opCode, funct);// define os sinais do bloco de controle

            final Long A = registradores.busca(reg1);// bloco A
            final Long B = registradores.busca(reg2);// bloco B

            String valorEstendido = extensaoSinal(immediate);// estende o valor

            //Etapa 2-3
            Long muxA = muxA(blocoControle, A, B, pc, funct);// Guarda a saida do multiplexador entre A e a ula
            Long muxB = muxB(blocoControle, B, valorEstendido, immediate.substring(5, 10));// Guarda a saida do multiplexador entre B e a ula

            final String opUla = ula.operacaoUla(funct, opCode, blocoControle);// define o que a ula fara
            final Long resultadoUla = ula.calcular(muxA, muxB, opUla);// guarda a saida da operacao da ula

            //Etapa 3
            final String regEscrita = muxRegistradorEscrito(blocoControle, reg2, reg3);// registrador que sera escrito
            registradores.escreve(resultadoUla, regEscrita, blocoControle);// escreve sobre regEscrita

            final Long endereco = muxPC(resultadoUla, pc, blocoControle);// guarda a saida da ula ou o pc dependendo do controle
            final Long registradorDadosMemoria = memoria.executar(endereco, B, blocoControle);// ler e escrever endereco na memoria

            final Long dadoEscrita = muxDadoEscrito(blocoControle, registradorDadosMemoria, resultadoUla);// Guarda a saida do mux entre a bloco de dados da memoria e o bloco de registradores

            final String regEscrita1 = muxRegistradorEscrito(blocoControle, reg2, reg3);// Guarda a saida do mux entre o registrador de instrucoes e o bloco de registradores
            registradores.escreve(dadoEscrita, regEscrita1, blocoControle);// escreve no dado a ser escrita

            if (printMenu) {
                System.out.println("1 - Proxima Instrução");
                System.out.println("2 - Avançar para o Final");
                final Integer next = sc.nextInt();
                printMenu = next.equals(1);
            }
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
    //retorna reg1 se RegDst estiver ligado e reg2 caso contrário
    private static String muxRegistradorEscrito(BlocoControle blocoControle, String reg2, String reg3) {
        if (blocoControle.getRegDst().equals("0")) {
            return reg2;
        } else if (blocoControle.getRegDst().equals("1")) {
            return reg3;
        }
        return null;
    }

    //implementação do multiplexador que da entrada no bloco de registradores como "Dado a ser escrito"
    //retorna registrador de dados da memória caso MemParaReg do BC seja 1, caso contrário, retorna o valor da Ula Saída
    private static Long muxDadoEscrito(BlocoControle blocoControle, Long valorRegistradorDadosMemoria, Long valorUla) {
        if (blocoControle.getMemParaReg().equals("1")) {
            return valorRegistradorDadosMemoria;
        } else if (blocoControle.getMemParaReg().equals("0")) {
            return valorUla;
        }
        return null;
    }

    //implementação do multiplexador que recebe do PC
    //caso louD esteja ligado, passa o valor da ula saída para a memória, caso contrário passará um valor novo
    private static Long muxPC(Long valorUla, Long valorPC, BlocoControle blocoControle) {
        if (blocoControle.getIouD().equals("1")) {
            return valorUla;
        } else {
            return valorPC;
        }
    }

    //implementação do multiplexador que recebe uma entrada do bloco B
    //retorna o valor de B caso ULAFonteB do BC seja 00, 4 se 01, o valor de sinal estendido se 10 e o valor com 2 bits desligados se 11
    private static Long muxB(BlocoControle blocoControle, Long b, String valorEstendido, String shamt) {
        if (blocoControle.getUlaFonteB().equals("00")) {
            return b;
        } else if (blocoControle.getUlaFonteB().equals("01")) {
            return 4L;
        } else if (blocoControle.getUlaFonteB().equals("10")) {
            return Long.parseLong(valorEstendido, 2);
        } else if (blocoControle.getUlaFonteB().equals("11")) {
            return Long.parseLong(shamt, 2);
        }
        return null;
    }

    //implementação do multiplexador que recebe uma entrada do bloco A
    //retorna o valor de A caso ULAFonteA seja 1, caso contrário retorna PC
    private static Long muxA(BlocoControle blocoControle, Long a, Long b, Long valorPc, String funct) {
        if (blocoControle.getUlaFonteA().equals("1")) {
            return a;
        } else if (blocoControle.getUlaFonteA().equals("0")) {
            return valorPc;
        } else if (funct.equals("000000") || funct.equals("000010")) {
            return b;
        }
        return null;
    }
}