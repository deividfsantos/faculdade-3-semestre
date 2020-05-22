package com.trabalhomips.business;

import com.trabalhomips.type.HexadecimalTable;
import com.trabalhomips.type.Registers;

import java.util.ArrayList;
import java.util.List;

public class Encoder {

    public List<String> encodeFile(List<String> allLines) {
        List<String> linesWithoutBlank = removeBlankLines(allLines);
        List<String> encodedLines = new ArrayList<>();
        for (int i = 0; i < linesWithoutBlank.size(); i++) {
            String encodedLine = encodeLineLowerCase(linesWithoutBlank.get(i), i, linesWithoutBlank);
            encodedLines.add(encodedLine);
        }
        return removeBlankLines(encodedLines);
    }

    private List<String> removeBlankLines(List<String> allLines) {
        List<String> linesWithoutBlank = new ArrayList<>();
        for (String allLine : allLines) {
            if (allLine != null && !allLine.isBlank()) {
                linesWithoutBlank.add(allLine);
            }
        }
        return linesWithoutBlank;
    }

    protected String encodeLineLowerCase(String line, Integer lineNumber, List<String> allLines) {
        if (line.contains(".text") || line.contains(".globl main")) {
            return null;
        }
        String encodeLine = encodeLine(line, lineNumber, allLines);
        return encodeLine == null ? null : encodeLine.toLowerCase();
    }

    private String encodeLine(String line, Integer lineNumber, List<String> allLines) {
        String lineWithoutSpaces = removeUnnecessaryWhiteSpaces(line);// deixa a linha sem excessos de espacos
        String[] instructionParts = lineWithoutSpaces.split(" ");// separa as partes da linha
        if (instructionParts.length == 1) {
            return null;
        }
        String instruction = instructionParts[0];// instrucao
        String[] registersAndValues = removeUnnecessaryWhiteSpaces(instructionParts[1]).split(",");// registradores e valores
        //casos com todas as instrucoes possiveis
        if (instruction.equals("addu")) {
            return encodeRType(registersAndValues, 33, lineNumber);
        }

        if (instruction.equals("and")) {
            return encodeRType(registersAndValues, 36, lineNumber);
        }

        if (instruction.equals("xor")) {
            return encodeRType(registersAndValues, 38, lineNumber);
        }

        if (instruction.equals("slt")) {
            return encodeRType(registersAndValues, 42, lineNumber);
        }

        if (instruction.equals("sll")) {
            return encodeRType2(registersAndValues, 0, lineNumber);
        }

        if (instruction.equals("srl")) {
            return encodeRType2(registersAndValues, 2, lineNumber);
        }

        if (instruction.equals("addiu")) {
            return encodeIType1(registersAndValues, 9, lineNumber);
        }

        if (instruction.equals("andi")) {
            return encodeIType1(registersAndValues, 12, lineNumber);
        }

        if (instruction.equals("ori")) {
            return encodeIType1(registersAndValues, 13, lineNumber);
        }

        if (instruction.equals("beq")) {
            return encodeBranchType(registersAndValues, 4, lineNumber, allLines, lineNumber);
        }

        if (instruction.equals("bne")) {
            return encodeBranchType(registersAndValues, 5, lineNumber, allLines, lineNumber);
        }

        if (instruction.equals("lui")) {
            return encodeIType2(registersAndValues, 15, lineNumber);
        }

        if (instruction.equals("lw")) {
            return encodeStoreType(registersAndValues, 35, lineNumber);
        }

        if (instruction.equals("sw")) {
            return encodeStoreType(registersAndValues, 43, lineNumber);
        }

        if (instruction.equals("j")) {
            return encodeJType(registersAndValues, allLines, lineNumber);
        }

        if (instruction.equals("jr")) {
            return encodeJType2(registersAndValues, lineNumber);
        }

        return encodeLineLowerCase(line.replace(instruction, ""), lineNumber, allLines);
    }

    private String removeUnnecessaryWhiteSpaces(String line) { // elimina espacos inuteis
        while (line.contains("  ")) {// se tiver mais de um espaco ele deleta um em loop
            line = line.replaceAll("  ", " ");
        }
        while (line.contains(", ")) {// se tiver mais de um espaco ele deleta um em loop
            line = line.replaceAll(", ", ",");
        }
        return line.trim();
    }

    private String encodeBranchType(String[] instructionParts, int opCode, Integer instructionLine, List<String> allLines, Integer lineNumber) {// caso se a instrucao for um beq ou bne
        validateInstructionSize(instructionParts, 3, lineNumber); // confere se a instrucao ta certa
        String destinyRegister = instructionParts[0];// pega o registrador destino
        String sourceRegister = instructionParts[1];// pega o registrador fonte
        String label = instructionParts[2];// pega o label
        if (isAnyBlank(destinyRegister, sourceRegister, label)) {// se faltar algo, da uma excessao/erro
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }

        int displacement = calculateDisplacement(instructionLine, allLines, label, lineNumber);//calcular o deslocamento do branch

        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();// pega o numero do registrador destino
        Integer sourceRegisterNumber = Registers.valueOf(sourceRegister.trim().replace(",", "")).getRegisterNumber();// pega o numero do registrador fonte

        String binaryResult = typeIToBinaryConverter(opCode, sourceRegisterNumber, destinyRegisterNumber, displacement);// converte os valores para binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult);// retorna o codigo codificado
    }

    private String encodeStoreType(String[] instructionParts, int opCode, Integer lineNumber) {// caso se a instrucao for um sw
        validateInstructionSize(instructionParts, 2, lineNumber);// confere se a instrucao ta certa
        String source1Register = instructionParts[0];// registrador que passara o conteudo
        String source2 = instructionParts[1];// registrador que guardara o conteudo
        if (isAnyBlank(source1Register, source2)) {// se faltar algo, da uma excessao/erro
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }

        // cria um array para separar o offset do registrador em si:
        String[] offsetAndRegister = source2.split("\\(");
        String source2Register = offsetAndRegister[1].replace(")", "");
        // end
        Integer source1RegisterNumber = Registers.valueOf(source1Register.trim().replace(",", "")).getRegisterNumber(); // pega o numero do registrador fonte
        Integer source2RegisterNumber = Registers.valueOf(source2Register.trim().replace(",", "")).getRegisterNumber(); // pega o numero do registrador destino
        Integer offset = getValueFromHexString(offsetAndRegister[0]);// pega o valor do offset

        String binaryResult = typeIToBinaryConverter(opCode, source1RegisterNumber, source2RegisterNumber, offset);// resultado em binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult);// resultado em hexadecimal
    }

    private String encodeJType(String[] instructionParts, List<String> allLines, Integer lineNumber) { // caso se a instrucao for um j
        validateInstructionSize(instructionParts, 1, lineNumber);// confere se a instrucao ta certa
        String label = instructionParts[0];// pega o label
        if (isAnyBlank(label)) {// se faltar algo, da uma excessao/erro
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }
        int jumpAddress = calculateJumpAddress(allLines, label, lineNumber);// pega onde deve chegar

        String opCodeBinary = decimalToBinaryConverter(2, 6);// opcode binario do j
        String jumpAddressBinary32 = decimalToBinaryConverter(jumpAddress, 32);// jumpAdress em binario de 32 bits

        String cleanJumpAddressBinary26 = removeUnnecessaryBits(jumpAddressBinary32); // endereco do j sem os bits inuteis
        return "0x" + binary32ToHexadecimalConverter(opCodeBinary + cleanJumpAddressBinary26);// resultado em hexa
    }

    private String removeUnnecessaryBits(String binaryResult) { // remove os bits inuteis
        return binaryResult.substring(4, binaryResult.length() - 2);
    }

    private int calculateJumpAddress(List<String> allLines, String label, Integer lineNumber) { // calcula a distancia do pulo do j
        for (int i = 0; i < allLines.size(); i++) { // procura pelo label
            if (allLines.get(i).trim().startsWith(label)) { // ao achar ele retorna o endereco
                return 0x400000 + Integer.parseInt(Integer.toHexString(i * 4), 16);
            }
        }// senao, retorna erro
        throw new RuntimeException("Label not found. Line: " + lineNumber);
    }

    private String encodeJType2(String[] instructionParts, Integer lineNumber) { // caso se a instrucao for um j
        validateInstructionSize(instructionParts, 1, lineNumber);// confere se a instrucao ta certa
        String destinyRegister = instructionParts[0]; // pega o registrador destino
        if (isAnyBlank(destinyRegister)) { // se faltar algo, da um erro/excesso
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }

        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();// pega o numero do registrador

        String binaryResult = typeJToBinaryConverter(destinyRegisterNumber); // resultado em binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult); // resultado em hexadecimal
    }

    private String encodeIType1(String[] instructionParts, Integer opCode, Integer lineNumber) { // caso a instrucao for ori
        validateInstructionSize(instructionParts, 3, lineNumber);// confere se a instrucao ta certa
        String destinyRegister = instructionParts[0];// pega o registrador destino
        String sourceRegister = instructionParts[1];// pega o registrador fonte
        String immediate = instructionParts[2];// pega o numero
        if (isAnyBlank(destinyRegister, sourceRegister, immediate)) {// se faltar algo, da um erro/excesso
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber(); // pega o numero do registrador destino
        Integer sourceRegisterNumber = Registers.valueOf(sourceRegister.trim().replace(",", "")).getRegisterNumber(); // pega o numero do registrador fonte
        Integer immediateNumber = getValueFromHexString(immediate.trim().replace(",", "")); // pega o numero

        String binaryResult = typeIToBinaryConverter(opCode, destinyRegisterNumber, sourceRegisterNumber, immediateNumber);// resultado em binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult); // resultado em hexadecimal
    }

    private String encodeIType2(String[] instructionParts, Integer opCode, Integer lineNumber) { // caso a instrucao seja um lui
        validateInstructionSize(instructionParts, 2, lineNumber); // confere se a instrucao ta certa
        String destinyRegister = instructionParts[0]; // registrador destino
        String immediate = instructionParts[1]; // numero
        if (isAnyBlank(destinyRegister, immediate)) {// se faltar algo, da erro/excessao
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();// pega o numero do registrador destino
        Integer immediateNumber = getValueFromHexString(immediate.trim().replace(",", ""));// pega o numero

        String binaryResult = typeIToBinaryConverter(opCode, destinyRegisterNumber, 0, immediateNumber);// resultado em binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult);// resultado em hexa
    }

    private Integer getValueFromHexString(String hexValue) {
        return Integer.parseInt(hexValue.replace("0x", ""), 16);
    }

    private String encodeRType(String[] instructionParts, Integer funct, Integer lineNumber) { // caso a instrucao for addu, and, slt, xor
        validateInstructionSize(instructionParts, 3, lineNumber);// confere se a instrucao ta certo
        String destinyRegister = instructionParts[0];// pega o registrador destino
        String source1Register = instructionParts[1];// pega o primeiro registrador fonte
        String source2Register = instructionParts[2];// pega o segundo registrador fonte
        if (isAnyBlank(destinyRegister, source1Register, source2Register)) { // se faltar algo, da erro
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }
        // numeros dos registradores
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer source1RegisterNumber = Registers.valueOf(source1Register.trim().replace(",", "")).getRegisterNumber();
        Integer source2RegisterNumber = Registers.valueOf(source2Register.trim().replace(",", "")).getRegisterNumber();
        //
        String binaryResult = typeRToBinaryConverter(0, destinyRegisterNumber, source1RegisterNumber, source2RegisterNumber, 0, funct);// res em binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult);// res em hexa
    }

    private String encodeRType2(String[] instructionParts, int funct, Integer lineNumber) { // caso a instrucao seja sll ou srl
        validateInstructionSize(instructionParts, 3, lineNumber);// confere se a instrucao ta certa
        String destinyRegister = instructionParts[0]; // registrador destino
        String sourceRegister = instructionParts[1]; // registrador fonte
        String shamtRegister = instructionParts[2]; // shamt
        if (isAnyBlank(destinyRegister, sourceRegister, shamtRegister)) {// se faltar algo, da erro
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }
        // numeros dos registradores e shamt
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer sourceRegisterNumber = Registers.valueOf(sourceRegister.trim().replace(",", "")).getRegisterNumber();
        Integer shamtNumber = getValueFromHexString(shamtRegister.trim().replace(",", ""));
        //
        String binaryResult = typeRToBinaryConverter(0, destinyRegisterNumber, 0, sourceRegisterNumber, shamtNumber, funct); // res em binario
        return "0x" + binary32ToHexadecimalConverter(binaryResult); // res em hexa
    }

    private int calculateDisplacement(Integer instructionLine, List<String> allLines, String label, Integer lineNumber) {// calcula o deslocamento
        int displacement = -1;
        for (int i = 0; i < allLines.size(); i++) { // loop para achar o label
            if (allLines.get(i).trim().startsWith(label)) {// se achar ele marca o deslocamento
                displacement = i - instructionLine;
            }
        }
        if (displacement == -1) {// se nao achar, da erro/excessao
            throw new RuntimeException("Label not found. Line: " + lineNumber);
        }
        return displacement;// retorna o deslocamento
    }

    private void validateInstructionSize(String[] instructionParts, Integer size, Integer lineNumber) { // confere se tem todos os parametros estao preenchidos
        if (instructionParts.length != size) {
            throw new RuntimeException("Incorrect instruction format. Line: " + lineNumber);
        }
    }

    // conversores referente ao tipo e ocasiao
    private String typeJToBinaryConverter(Integer destiny) {
        return decimalToBinaryConverter(0, 6)
                + decimalToBinaryConverter(destiny, 5)
                + decimalToBinaryConverter(0, 16)
                + decimalToBinaryConverter(8, 5);
    }

    private String typeIToBinaryConverter(Integer opCode, Integer source2, Integer source1, Integer immediate) {
        return decimalToBinaryConverter(opCode, 6)
                + decimalToBinaryConverter(source1, 5)
                + decimalToBinaryConverter(source2, 5)
                + decimalToBinaryConverter(immediate, 16);
    }

    private String typeRToBinaryConverter(Integer opCode, Integer destiny, Integer source1, Integer source2, Integer shamt, Integer funct) {
        return decimalToBinaryConverter(opCode, 6)
                + decimalToBinaryConverter(source1, 5)
                + decimalToBinaryConverter(source2, 5)
                + decimalToBinaryConverter(destiny, 5)
                + decimalToBinaryConverter(shamt, 5)
                + decimalToBinaryConverter(funct, 6);
    }

    private String binary32ToHexadecimalConverter(String binary) {
        StringBuilder hexResult = new StringBuilder();
        for (int i = 0; i < 32; i += 4) {
            String binaryPartWith4Digits = binary.substring(i, i + 4);
            hexResult.append(HexadecimalTable.getHexValueFromBinary(binaryPartWith4Digits));
        }
        return hexResult.toString();
    }

    private String decimalToBinaryConverter(Integer value, Integer size) {
        StringBuilder binaryResult = new StringBuilder(Integer.toBinaryString(value));

        int length = binaryResult.length();
        for (int i = length; i < size; i++) {
            if (value >= 0) {
                binaryResult.insert(0, "0");
            }
        }

        if (value < 0) {
            binaryResult.delete(0, binaryResult.length() - size);
        }

        return binaryResult.toString();
    }

    private Boolean isAnyBlank(String... vars) {
        for (String var : vars) {
            if (var == null || var.isBlank()) {
                return true;
            }
        }
        return false;
    }
}
