package com.trabalhomips.business;

import com.trabalhomips.type.HexadecimalTable;
import com.trabalhomips.type.Registers;

import java.util.ArrayList;
import java.util.List;

public class Encoder {

    public String encodeLineLowerCase(String line, Integer lineNumber, List<String> allLines) {
        if (line == null || line.isBlank()) {
            return "";
        }
        return encodeLine(line, lineNumber, allLines).toLowerCase();
    }

    private String encodeLine(String line, Integer lineNumber, List<String> allLines) {
        String lineWithoutSpaces = removeUnnecessaryWhiteSpaces(line);
        String[] instructionParts = lineWithoutSpaces.split(" ");
        String instruction = instructionParts[0];
        String[] registersAndValues = removeUnnecessaryWhiteSpaces(instructionParts[1]).split(",");
        if (instruction.equals("addu")) {
            return encodeRType(registersAndValues, 33);
        }

        if (instruction.equals("and")) {
            return encodeRType(registersAndValues, 36);
        }

        if (instruction.equals("xor")) {
            return encodeRType(registersAndValues, 38);
        }

        if (instruction.equals("slt")) {
            return encodeRType(registersAndValues, 42);
        }

        if (instruction.equals("sll")) {
            return encodeRType2(registersAndValues, 0);
        }

        if (instruction.equals("srl")) {
            return encodeRType2(registersAndValues, 2);
        }

        if (instruction.equals("addiu")) {
            return encodeIType1(registersAndValues, 9);
        }

        if (instruction.equals("andi")) {
            return encodeIType1(registersAndValues, 12);
        }

        if (instruction.equals("ori")) {
            return encodeIType1(registersAndValues, 13);
        }

        if (instruction.equals("beq")) {
            return encodeBranchType(registersAndValues, 4, lineNumber, allLines);
        }

        if (instruction.equals("bne")) {
            return encodeBranchType(registersAndValues, 5, lineNumber, allLines);
        }

        if (instruction.equals("lui")) {
            return encodeIType2(registersAndValues, 15);
        }

        if (instruction.equals("lw")) {
            return encodeStoreType(registersAndValues, 35);
        }

        if (instruction.equals("sw")) {
            return encodeStoreType(registersAndValues, 43);
        }

        if (instruction.equals("j")) {
            return encodeJType(registersAndValues, allLines);
        }

        if (instruction.equals("jr")) {
            return encodeJType2(registersAndValues);
        }

        return null;
    }

    private String removeUnnecessaryWhiteSpaces(String line) {
        while (line.contains("  ")) {
            line = line.trim().replaceAll("  ", " ");
        }
        return line;
    }

    private String encodeBranchType(String[] instructionParts, int opCode, Integer instructionLine, List<String> allLines) {
        validateInstructionSize(instructionParts, 3);
        String destinyRegister = instructionParts[0];
        String sourceRegister = instructionParts[1];
        String label = instructionParts[2];
        if (isAnyBlank(destinyRegister, sourceRegister, label)) {
            throw new RuntimeException("Incorrect instruction format.");
        }

        int displacement = calculateDisplacement(instructionLine, allLines, label);

        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer sourceRegisterNumber = Registers.valueOf(sourceRegister.trim().replace(",", "")).getRegisterNumber();

        String binaryResult = typeIToBinaryConverter(opCode, destinyRegisterNumber, sourceRegisterNumber, displacement);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private String encodeStoreType(String[] instructionParts, int opCode) {
        validateInstructionSize(instructionParts, 2);
        String source1Register = instructionParts[0];
        String source2 = instructionParts[1];
        if (isAnyBlank(source1Register, source2)) {
            throw new RuntimeException("Incorrect instruction format.");
        }

        String[] offsetPlusRegister = source2.split("\\(");
        String source2Register = offsetPlusRegister[1].replace(")", "");

        Integer source1RegisterNumber = Registers.valueOf(source1Register.trim().replace(",", "")).getRegisterNumber();
        Integer source2RegisterNumber = Registers.valueOf(source2Register.trim().replace(",", "")).getRegisterNumber();
        Integer offset = getValueFromHexString(offsetPlusRegister[0]);

        String binaryResult = typeIToBinaryConverter(opCode, source1RegisterNumber, source2RegisterNumber, offset);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private String encodeJType(String[] instructionParts, List<String> allLines) {
        validateInstructionSize(instructionParts, 1);
        String label = instructionParts[0];
        if (isAnyBlank(label)) {
            throw new RuntimeException("Incorrect instruction format.");
        }
        int jumpAddress = calculateJumpAddress(allLines, label);
        String binaryResult = typeJToBinaryConverter(2, jumpAddress);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private int calculateJumpAddress(List<String> allLines, String label) {
        int address = 0x400000;
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).contains(label)) {
                address += Integer.parseInt(Integer.toHexString((i + 1) * 4), 16);
            }
        }
        return address;
    }

    private String encodeJType2(String[] instructionParts) {
        validateInstructionSize(instructionParts, 1);
        String destinyRegister = instructionParts[0];
        if (isAnyBlank(destinyRegister)) {
            throw new RuntimeException("Incorrect instruction format.");
        }

        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();

        String binaryResult = typeJ2ToBinaryConverter(destinyRegisterNumber);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private String encodeIType1(String[] instructionParts, Integer opCode) {
        validateInstructionSize(instructionParts, 3);
        String destinyRegister = instructionParts[0];
        String sourceRegister = instructionParts[1];
        String immediate = instructionParts[2];
        if (isAnyBlank(destinyRegister, sourceRegister, immediate)) {
            throw new RuntimeException("Incorrect instruction format.");
        }
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer sourceRegisterNumber = Registers.valueOf(sourceRegister.trim().replace(",", "")).getRegisterNumber();
        Integer immediateNumber = getValueFromHexString(immediate.trim().replace(",", ""));

        String binaryResult = typeIToBinaryConverter(opCode, destinyRegisterNumber, sourceRegisterNumber, immediateNumber);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private String encodeIType2(String[] instructionParts, Integer opCode) {
        validateInstructionSize(instructionParts, 2);
        String destinyRegister = instructionParts[0];
        String immediate = instructionParts[1];
        if (isAnyBlank(destinyRegister, immediate)) {
            throw new RuntimeException("Incorrect instruction format.");
        }
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer immediateNumber = getValueFromHexString(immediate.trim().replace(",", ""));

        String binaryResult = typeIToBinaryConverter(opCode, destinyRegisterNumber, 0, immediateNumber);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private Integer getValueFromHexString(String hexValue) {
        return Integer.parseInt(hexValue.replace("0x", ""), 16);
    }

    private String encodeRType(String[] instructionParts, Integer funct) {
        validateInstructionSize(instructionParts, 3);
        String destinyRegister = instructionParts[0];
        String source1Register = instructionParts[1];
        String source2Register = instructionParts[2];
        if (isAnyBlank(destinyRegister, source1Register, source2Register)) {
            throw new RuntimeException("Incorrect instruction format.");
        }
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer source1RegisterNumber = Registers.valueOf(source1Register.trim().replace(",", "")).getRegisterNumber();
        Integer source2RegisterNumber = Registers.valueOf(source2Register.trim().replace(",", "")).getRegisterNumber();

        String binaryResult = typeRToBinaryConverter(0, destinyRegisterNumber, source1RegisterNumber, source2RegisterNumber, 0, funct);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private String encodeRType2(String[] instructionParts, int funct) {
        validateInstructionSize(instructionParts, 3);
        String destinyRegister = instructionParts[0];
        String sourceRegister = instructionParts[1];
        String shamtRegister = instructionParts[2];
        if (isAnyBlank(destinyRegister, sourceRegister, shamtRegister)) {
            throw new RuntimeException("Incorrect instruction format.");
        }
        Integer destinyRegisterNumber = Registers.valueOf(destinyRegister.trim().replace(",", "")).getRegisterNumber();
        Integer sourceRegisterNumber = Registers.valueOf(sourceRegister.trim().replace(",", "")).getRegisterNumber();
        Integer shamtNumber = getValueFromHexString(shamtRegister.trim().replace(",", ""));

        String binaryResult = typeRToBinaryConverter(0, destinyRegisterNumber, 0, sourceRegisterNumber, shamtNumber, funct);
        return "0x" + binary32ToHexadecimalConverter(binaryResult);
    }

    private int calculateDisplacement(Integer instructionLine, List<String> allLines, String label) {
        int displacement = -1;
        for (int i = 0; i < allLines.size(); i++) {
            if (allLines.get(i).contains(label)) {
                displacement = (i + 1) - instructionLine;
            }
        }
        if (displacement == -1) {
            throw new RuntimeException("Label not found.");
        }
        return displacement;
    }

    private void validateInstructionSize(String[] instructionParts, Integer size) {
        if (instructionParts.length != size) {
            throw new RuntimeException("Incorrect instruction format.");
        }
    }

    private String typeJToBinaryConverter(Integer opCode, Integer target) {
        return decimalToBinaryConverter(opCode, 6)
                + decimalToBinaryConverter(target, 26);
    }

    private String typeJ2ToBinaryConverter(Integer destiny) {
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
        StringBuilder binaryInvertedResult = new StringBuilder();
        Integer dividedValue = value;
        while (dividedValue > 0) {
            binaryInvertedResult.append(dividedValue % 2);
            dividedValue = dividedValue / 2;
        }

        StringBuilder binaryResult = binaryInvertedResult.reverse();
        int length = binaryResult.length();
        for (int i = length; i < size; i++) {
            binaryResult.insert(0, "0");
        }

        if (binaryResult.length() > size) {
            throw new RuntimeException("Size overflow in value conversion.");
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

    public static void main(String[] args) {
//        final int s = Integer.parseInt("00001001", 16);
        final String s = new Encoder().encodeLine("lui $1,0x00001001", 2, new ArrayList<>());
        final String s2 = new Encoder().encodeLine("ori $8,$1,0x00000000", 2, new ArrayList<>());
        System.out.println(s2.toLowerCase());
    }
}
