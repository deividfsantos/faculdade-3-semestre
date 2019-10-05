package com.deividsantos.t1;

import com.deividsantos.t1.exception.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParquimetroTest {

    private Parquimetro parq;

    @BeforeEach
    public void setUp() throws Exception {
        parq = new Parquimetro();
        parq.insereMoeda(100);
    }

    @Test
    public void testInsereMoeda() throws InvalidValueException {
        parq.insereMoeda(1);
        parq.insereMoeda(5);
        parq.insereMoeda(10);
        parq.insereMoeda(25);
        parq.insereMoeda(50);
        parq.insereMoeda(1001);
        assertEquals(291, parq.getSaldo());
    }

    @Test
    public void testGetSaldo() {
        double actual = parq.getSaldo();
        assertEquals(100, actual);
    }

    @Test
    public void testEmiteTicket() throws InvalidValueException {
        parq.insereMoeda(50);
        parq.insereMoeda(50);
        parq.insereMoeda(100);
        boolean actual = parq.emiteTicket();
        assertTrue(actual);
    }

    @Test
    public void testDevolve() throws InvalidValueException {
        parq.insereMoeda(50);
        parq.insereMoeda(50);
        parq.insereMoeda(100);
        parq.emiteTicket();
        int actual = parq.devolve();
        assertEquals(100, actual);
    }

    @Test
    public void testInsereValorInvalido() {
        assertThrows(InvalidValueException.class, () -> parq.insereMoeda(22));
    }
}