package com.mipssimulator.simulator;

public class Ula {
    public int calcular(int dado1, int dado2, String funct) {
        if (funct.equals("000000")) {
            return dado1 ^ dado2;
        }
        if (funct.equals("100100")) {
            return dado1 & dado2;
        }
        if (funct.equals("100001")) {
            return dado1 + dado2;
        }
        if (funct.equals("101010")) {
            return Integer.compare(dado1, dado2);
        }
        throw new RuntimeException();
    }
}
