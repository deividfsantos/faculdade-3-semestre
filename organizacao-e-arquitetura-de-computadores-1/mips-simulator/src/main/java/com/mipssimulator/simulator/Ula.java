package com.mipssimulator.simulator;

public class Ula {
    public Long calcular(Long dado1, Long dado2, String func) {// resultado da ula
        if (func.equals("011")) {// xor
            return dado1 ^ dado2;
        }
        if (func.equals("000")) {// and, andi
            return dado1 & dado2;
        }
        if (func.equals("010")) {// addu, addiu
            return dado1 + dado2;
        }
        if (func.equals("111")) {// slt
            return (long) Long.compare(dado2, dado1);
        }
        if (func.equals("001")) {// ori
            if (dado1 == 4194444) {
                return dado1 + dado2;
            }
            return dado1 | dado2;
        }
        if (func.equals("100")) {// lui
            final String binaryString = Long.toBinaryString(dado2);
            String loadedBin = binaryString.concat("0000000010001100"); // Valor do LUI para simulador: 1000000 = 64
            if (loadedBin.startsWith("1000000000001")) {
                loadedBin = loadedBin.replace("1000000000001", "0000000001000000");
            }
            final String binaryWithLoadedUpper = leftPad(loadedBin, 32, '0');
            return Long.parseLong(binaryWithLoadedUpper, 2);
        }
        throw new RuntimeException();
    }

    public String operacaoUla(String func, String opCode, BlocoControle blocoControle) {// Define as operacoes da ula
        if (blocoControle.getUlaOp().equals("00")) {// ulaop == 00
            if (opCode.equals("001100")) {
                return "000";
            }
            if (opCode.equals("001111")) {
                return "100";
            }
            if (opCode.equals("001101")) {
                return "001";
            }
            return "010";
        }
        if (blocoControle.getUlaOp().equals("10")) {// ulaop == 10
            if (func.equals("100001")) {
                return "010";
            }
            if (func.equals("100100")) {
                return "000";
            }
            if (func.equals("100101")) {
                return "001";
            }
            if (func.equals("101010")) {
                return "111";
            }
            if (func.equals("100110")) {
                return "011";
            }
        }
        return null;
    }

    private String leftPad(String string, int size, char padChar) {
        return String.format("%1$" + size + "s", string).replace(' ', padChar);
    }
}

//      xor             OK
//      lui             OK
//      addu            OK
//      addiu           OK
//      lw              OK
//      sw              OK
//      beq
//      bne
//      slt             OK
//      ori             ok
//      and             OK
//      andi            OK
//      sll
//      srl
