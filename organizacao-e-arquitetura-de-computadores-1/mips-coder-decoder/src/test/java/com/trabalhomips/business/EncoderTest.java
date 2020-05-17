package com.trabalhomips.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String ori = encoder.encodeLineLowerCase("ori $8,$1,0x00000000", 1, new ArrayList<>());
        assertEquals("0x34280000", ori);
    }

    @Test
    public void encodeLwLine() {
        String lw = encoder.encodeLineLowerCase("lw $9,0x00000000($8)", 1, new ArrayList<>());
        assertEquals("0x8d090000", lw);
    }

    @Test
    public void encodeSwLine() {
        String sw = encoder.encodeLineLowerCase("sw $11,0x00000000($13)", 1, new ArrayList<>());
        assertEquals("0xadab0000", sw);
    }

    @Test
    public void encodeAdduLine() {
        String addu = encoder.encodeLineLowerCase("addu $8,$9,$11", 1, new ArrayList<>());
        assertEquals("0x012b4021", addu);
    }

    @Test
    public void encodeAddiuLine() {
        String addiu = encoder.encodeLineLowerCase("addiu $2,$0,0x0000000a", 1, new ArrayList<>());
        assertEquals("0x2402000a", addiu);
    }

    @Test
    public void encodeXorLine() {
        String xor = encoder.encodeLineLowerCase("xor $9,$10,$9", 1, new ArrayList<>());
        assertEquals("0x01494826", xor);
    }

    @Test
    public void encodeSllLine() {
        String sll = encoder.encodeLineLowerCase("sll $9,$9,0x00000001", 1, new ArrayList<>());
        assertEquals("0x00094840", sll);
    }

    @Test
    public void encodeSrlLine() {
        String srl = encoder.encodeLineLowerCase("srl $10,$10,0x00000001", 1, new ArrayList<>());
        assertEquals("0x000a5042", srl);
    }

    @Test
    public void encodeJLine() {
        String[] files = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000  \n" +
                "lui $1,0x00001001     \n" +
                "ori $10,$1,0x00000004 \n" +
                "lw $9,0x00000000($8)  \n" +
                "addiu $9,$9,0x00000001\n" +
                "addu $9,$9,$9         \n" +
                "sw $9,0x00000000($10) \n" +
                "xor $9,$10,$9         \n" +
                "sll $9,$9,0x00000001  \n" +
                "srl $10,$10,0x00000001\n" +
                "j fim          \n" +
                "fim: addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        String j = encoder.encodeLineLowerCase("j fim", 12, Arrays.asList(files));
        assertEquals("0x0810000c", j);
    }

    @Test
    public void encodeBeqLineDown() {
        String[] files = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000  \n" +
                "lw $9,0x00000000($8)  \n" +
                "lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000004  \n" +
                "lw $10,0x00000000($8) \n" +
                "beq $9,$10,0x00000001 \n" +
                "bne $9,$10,0x00000002 \n" +
                "fim: addiu $2,$0,0x0000000a\n" +
                "syscall               \n" +
                "erro:                 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        String beq = encoder.encodeLineLowerCase("beq $9,$10,fim", 7, Arrays.asList(files));
        assertEquals("0x112a0001", beq);
    }

    @Test
    public void encodeBeqLineUp() {
        String[] files = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000  \n" +
                "lw $9,0x00000000($8)  \n" +
                "fim: lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000004  \n" +
                "lw $10,0x00000000($8) \n" +
                "beq $9,$10,0x00000001 \n" +
                "bne $9,$10,0x00000002 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               \n" +
                "erro:                 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        String beq = encoder.encodeLineLowerCase("beq $9,$10,fim", 7, Arrays.asList(files));
        assertEquals("0x112afffc", beq);
    }

    @Test
    public void encodeBneLine() {
        String[] files = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000  \n" +
                "lw $9,0x00000000($8)  \n" +
                "fim: lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000004  \n" +
                "lw $10,0x00000000($8) \n" +
                "beq $9,$10,0x00000001 \n" +
                "bne $9,$10,0x00000002 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               \n" +
                "erro:                 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        String beq = encoder.encodeLineLowerCase("bne $9,$10,erro", 8, Arrays.asList(files));
        assertEquals("0x152a0002", beq);
    }

    @Test
    public void encodeJrLine() {
        String jr = encoder.encodeLineLowerCase("jr $9", 1, new ArrayList<>());
        assertEquals("0x01200008", jr);
    }

    @Test
    public void encodeAndiLine() {
        String andi = encoder.encodeLineLowerCase(" andi $9,$9,0x00000064", 1, new ArrayList<>());
        assertEquals("0x31290064", andi);
    }


    @Test
    public void encodeAndLine() {
        String and = encoder.encodeLineLowerCase(" and $9,$10,$9", 1, new ArrayList<>());
        assertEquals("0x01494824", and);
    }

    @Test
    public void encodeSltLine() {
        String slt = encoder.encodeLineLowerCase("slt $9,$10,$9", 1, new ArrayList<>());
        assertEquals("0x0149482a", slt);
    }

    @Test
    public void encodeFileTest() {
        String[] file = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000\n" +
                "lw $9,0x00000000($8)  \n" +
                "lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000004\n" +
                "lw $10,0x00000000($8) \n" +
                "and $9,$10,$9         \n" +
                "andi $9,$9,0x00000064 \n" +
                "slt $9,$10,$9         \n" +
                "lw $9,0x00000000($8)  \n" +
                "lw $10,0x00000000($8) \n" +
                "beq $9,$10,fim\n" +
                "bne $9,$10,erro \n" +
                "fim lw $9,0x00000000($8)  \n" +
                "erro lw $10,0x00000000($8) \n" +
                "jr $9                 \n").split("\n");
        List<String> encodedFile = encoder.encodeFile(Arrays.asList(file));
        final String[] encodedLines = ("0x3c011001\n" +
                "0x34280000\n" +
                "0x8d090000\n" +
                "0x3c011001\n" +
                "0x34280004\n" +
                "0x8d0a0000\n" +
                "0x01494824\n" +
                "0x31290064\n" +
                "0x0149482a\n" +
                "0x8d090000\n" +
                "0x8d0a0000\n" +
                "0x112a0002\n" +
                "0x152a0002\n" +
                "0x8d090000\n" +
                "0x8d0a0000\n" +
                "0x01200008").split("\n");
        assertEquals(Arrays.asList(encodedLines), encodedFile);
    }
}