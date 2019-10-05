package contamagica.deividsantos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContaMagicaTest {

    private ContaMagica contaMagica;

    @BeforeEach
    public void setup() {
        contaMagica = new ContaMagica("Testes");
    }

    @Test
    void getNomeClienteTest() {
        assertEquals("Testes", contaMagica.getNomeCliente());
    }

    @Test
    void getSaldo() {
        assertEquals(BigDecimal.ZERO, contaMagica.getSaldo());
    }

    @Test
    void getStatus() {
        assertEquals(Categorias.SILVER, contaMagica.getStatus());

        contaMagica.deposito(BigDecimal.valueOf(55000));
        assertEquals(Categorias.GOLD, contaMagica.getStatus());

        contaMagica.deposito(BigDecimal.valueOf(1560000));
        assertEquals(Categorias.PLATINUM, contaMagica.getStatus());
    }

    @Test
    void deposito() {
        contaMagica.deposito(BigDecimal.valueOf(10000));

        assertEquals(BigDecimal.valueOf(10000), contaMagica.getSaldo());
    }

    @Test
    void retirada() {
        contaMagica.deposito(BigDecimal.valueOf(250000));
        assertEquals(Categorias.PLATINUM, contaMagica.getStatus());

        contaMagica.retirada(BigDecimal.valueOf(2500));
        assertEquals(Categorias.PLATINUM, contaMagica.getStatus());

        contaMagica.retirada(BigDecimal.valueOf(150000));
        assertEquals(Categorias.GOLD, contaMagica.getStatus());

        contaMagica.retirada(BigDecimal.valueOf(80000));
        assertEquals(Categorias.SILVER, contaMagica.getStatus());
    }

    @Test
    void retiradaTest() {
        contaMagica.deposito(BigDecimal.valueOf(250000));
        assertEquals(Categorias.PLATINUM, contaMagica.getStatus());

        contaMagica.retirada(BigDecimal.valueOf(2500));
        assertEquals(Categorias.PLATINUM, contaMagica.getStatus());

        contaMagica.retirada(BigDecimal.valueOf(240000));
        assertEquals(Categorias.GOLD, contaMagica.getStatus());

    }
}