package com.trabalhomips.business;

import com.trabalhomips.type.HexadecimalTable;
import com.trabalhomips.type.Registers;

import java.util.ArrayList;
import java.util.List;

public class Decoder {
    // instrucoes em binario
    private static final String ADDU_FUNCT = "100001";
    private static final String XOR_FUNCT = "100110";
    private static final String SLT_FUNCT = "101010";
    private static final String AND_FUNCT = "100100";
    private static final String SRL_FUNCT = "000010";
    private static final String SLL_FUNCT = "000000";
    private static final String JR_FUNCT = "001000";
    private static final String LUI_OPCODE = "001111";
    private static final String ORI_OPCODE = "001101";
    private static final String ADDIU_OPCODE = "001001";
    private static final String ANDI_OPCODE = "001100";
    private static final String LW_OPCODE = "100011";
    private static final String SW_OPCODE = "101011";
    private static final String J_OPCODE = "000010";
    private static final String BEQ_OPCODE = "000100";
    private static final String BNE_OPCODE = "000101";

    //.
    //Comeca o processo de decode deixando todas as instrucoes em minusculo
    public List<String> decodeFile(List<String> allLines) {
        List<String> decodedLines = new ArrayList<>();
        for (int i = 0; i < allLines.size(); i++) {
            String decodedLine = decodeLineLowerCase(allLines.get(i), i, allLines);
            decodedLines.add(decodedLine);
        }
        setLabels(allLines, decodedLines);
        decodedLines.add(0, "main:");
        decodedLines.add(0, ".globl main");
        decodedLines.add(0, ".text");
        return decodedLines;
    }

    private void setLabels(List<String> allLines, List<String> decodedLines) {
        for (int i = 0; i < decodedLines.size(); i++) {
            if (allLines.get(i).startsWith("label")) {
                decodedLines.set(i, "label_line_" + i + ": " + decodedLines.get(i));
            }
        }
    }

    protected String decodeLineLowerCase(String line, Integer lineNumber, List<String> allLines) {
        if (line == null || line.isBlank()) {
            return "";
        }
        return decodeLine(line, allLines, lineNumber).toLowerCase();
    }

    // processo que faz o decode do codigo
    private String decodeLine(String line, List<String> allLines, Integer instructionNumber) {
        String lineWithoutSpaces = removeWhiteSpaces(line);
        String hexCode = lineWithoutSpaces.replace("0x", "");
        String binaryLine = hexToBinaryConverter(hexCode);
        if (binaryLine.length() != 32) { // se o codigo nao tiver 32 bits da um erro
            throw new RuntimeException("Incorrect hex instruction code. Line: " + instructionNumber);
        }
        String opCode = binaryLine.substring(0, 6);// pega o opcode
        // todas as instrucoes possiveis
        if (opCode.equalsIgnoreCase("000000")) {// caso a funcao for de tipo r
            String funct = binaryLine.substring(26, 32);
            if (funct.equalsIgnoreCase(JR_FUNCT))
                return decodeJrType(binaryLine);
            return decodeRType(binaryLine);
        }

        if (opCode.equalsIgnoreCase(LUI_OPCODE)) {
            String registerBinary = binaryLine.substring(11, 16);
            String immediateBinary = binaryLine.substring(16, 32);
            String register = Registers.getRegisterByDecimal(binaryToDecimalConverter(registerBinary));
            String immediate = convertBinaryToHex(immediateBinary, 8);
            return "lui " + register + "," + immediate;
        }

        if (opCode.equalsIgnoreCase(ORI_OPCODE)) {
            return decodeIType(binaryLine, "ori ");
        }

        if (opCode.equalsIgnoreCase(ADDIU_OPCODE)) {
            return decodeIType(binaryLine, "addiu ");
        }

        if (opCode.equalsIgnoreCase(ANDI_OPCODE)) {
            return decodeIType(binaryLine, "andi ");
        }

        if (opCode.equalsIgnoreCase(LW_OPCODE)) {
            return decodeStoreType(binaryLine, "lw ");
        }


        if (opCode.equalsIgnoreCase(SW_OPCODE)) {
            return decodeStoreType(binaryLine, "sw ");
        }

        if (opCode.equalsIgnoreCase(J_OPCODE)) {
            String target = binaryLine.substring(6, 32);

            Integer lineToJump = calculateLineToJump(target);
            String labelToJumpName = createAndSetLabel(allLines, lineToJump);
            return "j " + labelToJumpName;
        }

        if (opCode.equalsIgnoreCase(BEQ_OPCODE)) {
            return decodeBranchType(allLines, instructionNumber, binaryLine, "beq ");
        }

        if (opCode.equalsIgnoreCase(BNE_OPCODE)) {
            return decodeBranchType(allLines, instructionNumber, binaryLine, "bne ");
        }

        return null;
    }
    //.

    private String removeWhiteSpaces(String line) {
        String[] labelAndHexCode = line.split(" ");
        if (labelAndHexCode.length == 2) {
            return labelAndHexCode[1].trim();
        }
        return labelAndHexCode[0].trim();
    }

    private String decodeBranchType(List<String> allLines, Integer instructionNumber, String binaryLine, String operator) {// caso for bne ou beq
        String register1Binary = binaryLine.substring(6, 11); // pega o registrador 1
        String register2Binary = binaryLine.substring(11, 16); // pega o registrador 2
        String immediateBinary = binaryLine.substring(16, 32); // pega a quantidade de linhas deve ser pulado ate o label
        String register1 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register1Binary));// pega o numero do registrador 1
        String register2 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register2Binary));// pega o numero do registrador 2
        int lineToJump = calculateLineToJump(immediateBinary, instructionNumber); // Pega a linha que tem q chegar
        String labelToJumpName = createAndSetLabel(allLines, lineToJump);// pega o label pela linha do lineToJump
        return operator + register1 + "," + register2 + "," + labelToJumpName;// retorna a instrucao decodificada
    }

    private int calculateLineToJump(String immediateBinary, Integer instructionLine) { // retorna o numero da linha do label(beq ou bne)
        char positiveOrNegative = immediateBinary.charAt(0);
        StringBuilder immediateFull = new StringBuilder(immediateBinary);
        for (int i = immediateBinary.length(); i < 32; i++) {
            immediateFull.insert(0, positiveOrNegative);
        }
        int distanceToInstruction = (int) Long.parseLong(immediateFull.toString(), 2);
        return distanceToInstruction + instructionLine + 1;
    }

    private Integer calculateLineToJump(String target) { // retorna o numero da linha do label(j ou jr)
        if (target.length() == 26) {
            target = "0000" + target + "00";
        }
        String hexTarget = convertBinaryToHex(target, 32);
        int linesHex = Integer.parseInt(hexTarget.replace("0x", ""), 16) - 0x00400000;
        return linesHex / 4;
    }

    private String createAndSetLabel(List<String> allLines, Integer lineToJump) { // da um nome para um label
        String labelToJumpName = "label_line_" + lineToJump;
        String lineWithoutLabel = allLines.get(lineToJump);
        String newLabel = lineWithoutLabel.startsWith(labelToJumpName) ? lineWithoutLabel : labelToJumpName + ": " + lineWithoutLabel;
        allLines.set(lineToJump, newLabel);
        return labelToJumpName;
    }

    private String decodeJrType(String binaryLine) { // caso a instrucao for um jr
        String registerBinary = binaryLine.substring(6, 11);// pega o registrador
        String register = Registers.getRegisterByDecimal(binaryToDecimalConverter(registerBinary)); // pega o numero do registrador
        return "jr " + register;
    }

    private String decodeIType(String binaryLine, String operator) { // caso a insturcao for um andi, addiu, ori
        String register1Binary = binaryLine.substring(6, 11);// pega o registrador 1
        String register2Binary = binaryLine.substring(11, 16);// pega o registrador 2
        String immediateBinary = binaryLine.substring(16, 32);// pega o valor inteiro
        String register1 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register1Binary));// pega o numero do registrador 1
        String register2 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register2Binary));// pega o numero do registrador 2
        String immediate = convertBinaryToHex(immediateBinary, 8);// converte o valor inteiro de binario para hexa
        return operator + register2 + "," + register1 + "," + immediate;
    }

    private String decodeStoreType(String binaryLine, String operator) { // caso a instrucao for um lw ou sw
        String register1Binary = binaryLine.substring(6, 11);// pega o registrador 1
        String register2Binary = binaryLine.substring(11, 16);// pega o registrador 2
        String immediateBinary = binaryLine.substring(16, 32);// pega o valor offset
        String register1 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register1Binary));// pega o numero do registrador 1
        String register2 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register2Binary));// pega o numero do registrador 2
        String immediate = convertBinaryToHex(immediateBinary, 8);// converte o valor inteiro de binario para hexa
        return operator + register2 + "," + immediate + "(" + register1 + ")";
    }

    private String decodeRType(String binaryInstruction) {
        String register1Binary = binaryInstruction.substring(6, 11);// pega o registrador 1
        String register2Binary = binaryInstruction.substring(11, 16);// pega o registrador 2
        String register3Binary = binaryInstruction.substring(16, 21);// pega o registrador 3
        String offsetBinary = binaryInstruction.substring(21, 26);// pega o offset
        String functBinary = binaryInstruction.substring(26, 32);// pega a instrucao em binario

        String registerSource1 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register1Binary));// pega o numero do registrador 1
        String registerSource2 = Registers.getRegisterByDecimal(binaryToDecimalConverter(register2Binary));// pega o numero do registrador 2
        String registerDestiny = Registers.getRegisterByDecimal(binaryToDecimalConverter(register3Binary));// pega o numero do registrador 3
        String offset = convertBinaryToHex(offsetBinary, 8); // converte o offset
        //troca o valor binario do funct
        if (functBinary.equalsIgnoreCase(ADDU_FUNCT)) {
            return "addu " + registerDestiny + "," + registerSource1 + "," + registerSource2;
        }

        if (functBinary.equalsIgnoreCase(XOR_FUNCT)) {
            return "xor " + registerDestiny + "," + registerSource1 + "," + registerSource2;
        }

        if (functBinary.equalsIgnoreCase(SLT_FUNCT)) {
            return "slt " + registerDestiny + "," + registerSource1 + "," + registerSource2;
        }

        if (functBinary.equalsIgnoreCase(AND_FUNCT)) {
            return "and " + registerDestiny + "," + registerSource1 + "," + registerSource2;
        }

        if (functBinary.equalsIgnoreCase(SRL_FUNCT)) {
            return "srl " + registerDestiny + "," + registerSource2 + "," + offset;
        }

        if (functBinary.equalsIgnoreCase(SLL_FUNCT)) {
            return "sll " + registerDestiny + "," + registerSource2 + "," + offset;
        }
        return null;
        //.
    }

    //conversoes
    private String convertBinaryToHex(String offsetBinary, Integer size) {
        int offset = binaryToDecimalConverter(offsetBinary);
        String hexOffset = Integer.toHexString(offset);
        StringBuilder fullHexOffset = new StringBuilder(hexOffset);
        if (offset >= 0) {
            for (int i = hexOffset.length(); i < size; i++) {
                fullHexOffset.insert(0, "0");
            }
            return "0x" + fullHexOffset.toString();
        }
        for (int i = hexOffset.length(); i < size; i++) {
            fullHexOffset.insert(0, "f");
        }
        return "0x" + fullHexOffset.toString();
    }

    private String hexToBinaryConverter(String hexCode) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < hexCode.length(); i++) {
            final String hex = String.valueOf(hexCode.charAt(i));
            binary.append(HexadecimalTable.getBinaryValueFromHex(hex));
        }
        return binary.toString();
    }

    private Integer binaryToDecimalConverter(String binary) {
        return Integer.parseInt(binary, 2);
    }
    //.
}
