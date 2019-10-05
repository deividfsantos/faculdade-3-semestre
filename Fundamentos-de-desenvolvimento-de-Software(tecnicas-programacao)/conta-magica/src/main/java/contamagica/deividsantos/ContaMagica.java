package contamagica.deividsantos;

import java.math.BigDecimal;

public class ContaMagica {

    private String nome;
    private BigDecimal saldo;
    private Categorias categorias;

    public ContaMagica(String nome) {
        this.nome = nome;
        this.categorias = Categorias.SILVER;
        this.saldo = BigDecimal.ZERO;
    }

    public String getNomeCliente() {
        return nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Categorias getStatus() {
        return categorias;
    }

    public void deposito(BigDecimal valor) {
        if (categorias.equals(Categorias.SILVER)) {
            saldo = saldo.add(valor);
        }
        if (categorias.equals(Categorias.GOLD)) {
            BigDecimal valorExtra = valor.multiply(BigDecimal.valueOf(0.01));
            saldo = saldo.add(valor.add(valorExtra));
        }
        if (categorias.equals(Categorias.PLATINUM)) {
            BigDecimal valorExtra = valor.multiply(BigDecimal.valueOf(0.025));
            saldo = saldo.add(valor.add(valorExtra));
        }
        if (saldo.compareTo(BigDecimal.valueOf(50000)) > 0) {
            categorias = Categorias.GOLD;
        }

        if (saldo.compareTo(BigDecimal.valueOf(200000)) > 0) {
            categorias = Categorias.PLATINUM;
        }
    }

    public void retirada(BigDecimal valor) {
        saldo = saldo.subtract(valor);
        if (saldo.compareTo(BigDecimal.valueOf(100000)) < 0 && categorias.equals(Categorias.PLATINUM)) {
            categorias = Categorias.GOLD;
        } else if (saldo.compareTo(BigDecimal.valueOf(25000)) < 0 && categorias.equals(Categorias.GOLD)) {
            categorias = Categorias.SILVER;
        }
    }
}
