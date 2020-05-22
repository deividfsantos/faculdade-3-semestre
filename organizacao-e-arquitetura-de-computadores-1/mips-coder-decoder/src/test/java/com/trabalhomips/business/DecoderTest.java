package com.trabalhomips.business;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecoderTest {

    private final Decoder decoder;

    public DecoderTest() {
        this.decoder = new Decoder();
    }

    @Test
    void decodeAdduLine() {
        String addu = decoder.decodeLineLowerCase("0x012b4021", 0, new ArrayList<>());
        assertEquals("addu $8,$9,$11", addu);
        String addu1 = decoder.decodeLineLowerCase("0x010d4821", 0, new ArrayList<>());
        assertEquals("addu $9,$8,$13", addu1);
    }

    @Test
    void decodeXorLine() {
        String xor = decoder.decodeLineLowerCase("0x014bc026", 0, new ArrayList<>());
        assertEquals("xor $24,$10,$11", xor);
    }

    @Test
    void decodeSltLine() {
        String slt = decoder.decodeLineLowerCase("0x014d482a", 0, new ArrayList<>());
        assertEquals("slt $9,$10,$13", slt);
    }

    @Test
    void decodeAndLine() {
        String and = decoder.decodeLineLowerCase("0x01494824", 0, new ArrayList<>());
        assertEquals("and $9,$10,$9", and);
    }

    @Test
    void decodeSrlLine() {
        String srl = decoder.decodeLineLowerCase("0x000a5042", 0, new ArrayList<>());
        assertEquals("srl $10,$10,0x00000001", srl);
    }

    @Test
    void decodeSllLine() {
        String sll = decoder.decodeLineLowerCase("0x00094840", 0, new ArrayList<>());
        assertEquals("sll $9,$9,0x00000001", sll);
    }

    @Test
    void decodeLuiLine() {
        String lui = decoder.decodeLineLowerCase("0x3c011001", 0, new ArrayList<>());
        assertEquals("lui $1,0x00001001", lui);
    }

    @Test
    void decodeOriLine() {
        String ori = decoder.decodeLineLowerCase("0x34280004", 0, new ArrayList<>());
        assertEquals("ori $8,$1,0x00000004", ori);
    }

    @Test
    void decodeAddiuLine() {
        String addiu = decoder.decodeLineLowerCase("0x2402000a", 0, new ArrayList<>());
        assertEquals("addiu $2,$0,0x0000000a", addiu);
    }

    @Test
    void decodeAndiLine() {
        String andiu = decoder.decodeLineLowerCase("0x31290064", 0, new ArrayList<>());
        assertEquals("andi $9,$9,0x00000064", andiu);
    }

    @Test
    void decodeLwLine() {
        String lw = decoder.decodeLineLowerCase("0x8d090000", 0, new ArrayList<>());
        assertEquals("lw $9,0x00000000($8)", lw);
    }

    @Test
    void decodeSwLine() {
        String sw = decoder.decodeLineLowerCase("0xad490000", 0, new ArrayList<>());
        assertEquals("sw $9,0x00000000($10)", sw);
    }

    @Test
    void decodeJrLine() {
        String jr = decoder.decodeLineLowerCase("0x01200008", 0, new ArrayList<>());
        assertEquals("jr $9", jr);
    }

    @Test
    public void decodeJLine() {
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
                "addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        final List<String> allLines = Arrays.asList(files);
        String j = decoder.decodeLineLowerCase("0x0810000c", 12, allLines);
        assertEquals("j label_line_12", j);
        assertEquals("label_line_12: addiu $2,$0,0x0000000a", allLines.get(12));
    }

    @Test
    public void decodeBeqLineDown() {
        String[] files = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000  \n" +
                "lw $9,0x00000000($8)  \n" +
                "lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000004  \n" +
                "lw $10,0x00000000($8) \n" +
                "beq $9,$10,0x00000001 \n" +
                "bne $9,$10,0x00000002 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               \n" +
                "erro:                 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        final List<String> allLines = Arrays.asList(files);
        String beq = decoder.decodeLineLowerCase("0x112a0001", 6, allLines);
        assertEquals("beq $9,$10,label_line_8", beq);
        assertEquals("label_line_8: addiu $2,$0,0x0000000a", allLines.get(8));
    }

    @Test
    public void decodeBeqLineUp() {
        String[] files = ("lui $1,0x00001001     \n" +
                "ori $8,$1,0x00000000  \n" +
                "lw $9,0x00000000($8)  \n" +
                "fim: lui $1,0x00001001\n" +
                "ori $8,$1,0x00000004  \n" +
                "lw $10,0x00000000($8) \n" +
                "beq $9,$10,0x00000001 \n" +
                "bne $9,$10,0x00000002 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               \n" +
                "erro:                 \n" +
                "addiu $2,$0,0x0000000a\n" +
                "syscall               ").split("\n");

        final List<String> allLines = Arrays.asList(files);
        String beq = decoder.decodeLineLowerCase("0x112afffc", 6, allLines);
        assertEquals("beq $9,$10,label_line_3", beq);
        assertEquals("label_line_3: fim: lui $1,0x00001001", allLines.get(3));
    }

    @Test
    public void decodeBneLine() {
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

        final List<String> allLines = Arrays.asList(files);
        String bne = decoder.decodeLineLowerCase("0x152a0002", 7, Arrays.asList(files));
        String bne2 = decoder.decodeLineLowerCase("0x152a0002", 7, Arrays.asList(files));
        assertEquals("bne $9,$10,label_line_10", bne);
        assertEquals("bne $9,$10,label_line_10", bne2);
        assertEquals("label_line_10: erro:                 ", allLines.get(10));
    }

    @Test
    public void decodeFileTest() {
        String[] file = ("0x3c011001\n" +
                "0x34280000\n" +
                "0x8d090000     \n" +
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
                "0x01200008\n").split("\n");
        List<String> decodedFile = decoder.decodeFile(Arrays.asList(file));
        final String[] decodedLines = (".text\n" +
                ".globl main\n" +
                "main:\n" +
                "lui $1,0x00001001\n" +
                "ori $8,$1,0x00000000\n" +
                "lw $9,0x00000000($8)\n" +
                "lui $1,0x00001001\n" +
                "ori $8,$1,0x00000004\n" +
                "lw $10,0x00000000($8)\n" +
                "and $9,$10,$9\n" +
                "andi $9,$9,0x00000064\n" +
                "slt $9,$10,$9\n" +
                "lw $9,0x00000000($8)\n" +
                "lw $10,0x00000000($8)\n" +
                "beq $9,$10,label_line_14\n" +
                "bne $9,$10,label_line_15\n" +
                "lw $9,0x00000000($8)\n" +
                "label_line_14: lw $10,0x00000000($8)\n" +
                "label_line_15: jr $9").split("\n");
        assertEquals(Arrays.asList(decodedLines), decodedFile);
    }
}