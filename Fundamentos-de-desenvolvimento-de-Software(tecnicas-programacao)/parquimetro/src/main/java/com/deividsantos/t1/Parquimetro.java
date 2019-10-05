package com.deividsantos.t1;

import com.deividsantos.t1.exception.InvalidValueException;

public class Parquimetro {

    private int saldo;

    public Parquimetro() {
        saldo = 0;
    }

    // Permite inserir moedas no parquimetro (soma no saldo)
    // Valores possíveis: 1, 5, 10, 25, 50 e 100 (1 real)
    // Gera InvalidValueException no caso de valor inválido
    public void insereMoeda(int valor) throws InvalidValueException {
        switch (valor) {
            case 1:
            case 5:
            case 10:
            case 25:
            case 50:
            case 100:
                saldo += valor;
                break;
            default:
                throw new InvalidValueException();
        }
    }

    // Retorna o saldo acumulado no parquimetro
    public double getSaldo() {
        return saldo;
    }

    // Emite um ticket de 2 reais se houver saldo disponível.
    // Retorna true se a operação foi possível
    public boolean emiteTicket() {
        if (saldo < 200) return (false);
        saldo -= 200;
        return (true);
    }

    // Devolve o saldo existente. Retorna o valor devolvido
    public int devolve() {
        int tmp = saldo;
        saldo = 0;
        return (tmp);
    }
}