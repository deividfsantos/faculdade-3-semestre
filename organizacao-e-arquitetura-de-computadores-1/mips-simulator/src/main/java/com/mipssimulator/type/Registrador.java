package com.mipssimulator.type;

public class Registrador {

    public int id;
    public int valor;

    public Registrador(int id) {
        this.id = id;
        this.valor = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.getId()+" = "+this.getValor();
    }
}