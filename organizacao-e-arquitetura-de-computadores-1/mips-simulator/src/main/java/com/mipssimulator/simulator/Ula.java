package com.mipssimulator.simulator;

public class Ula {
    public int calcular(Integer dado1, Integer dado2, String funct, String op) {
        if (funct.equals("000000")) {
            return dado1 ^ dado2;
        }
        if (funct.equals("100100")) {
            return dado1 & dado2;
        }
        if (funct.equals("100001") || "00".equals(op)) {
            return dado1 + dado2;
        }
        if (funct.equals("101010")) {
            return Integer.compare(dado1, dado2);
        }
        throw new RuntimeException();
    }
}

//      lw $9, 4($8)

//      xor             OK
//      lui
//      addu            OK
//      addiu
//      lw              OK
//      sw
//      beq
//      bne
//      slt             OK
//      ori
//      and             OK
//      andi
//      sll
//      srl
