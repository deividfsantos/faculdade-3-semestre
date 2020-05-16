package com.trabalhomips.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncoderTest {

    private Encoder encoder;

    public EncoderTest() {
        this.encoder = new Encoder();
    }

    @Test
    public void encodeLuiLine() {
        String lui = encoder.encodeLineLowerCase("lui $1,0x00001001", 1, new ArrayList<>());
        assertEquals("0x3c011001", lui);
    }

    @Test
    public void encodeOriLine() {
        String lui = encoder.encodeLineLowerCase("ori $8,$1,0x00000000", 1, new ArrayList<>());
        assertEquals("0x34280000", lui);
    }

    @Test
    public void encodeLwLine() {
        String lui = encoder.encodeLineLowerCase("lw $9,0x00000000($8)", 1, new ArrayList<>());
        assertEquals("0x8d090000", lui);
    }

    @Test
    public void encodeSwLine() {
        String lui = encoder.encodeLineLowerCase("sw $11,0x00000000($13)", 1, new ArrayList<>());
        assertEquals("0xadab0000", lui);
    }
}