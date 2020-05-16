package com.trabalhomips.type;

public enum HexadecimalTable {
    zero("0", "0000", 0),
    one("1", "0001", 1),
    two("2", "0010", 2),
    three("3", "0011", 3),
    four("4", "0100", 4),
    five("5", "0101", 5),
    six("6", "0110", 6),
    seven("7", "0111", 7),
    eight("8", "1000", 8),
    nine("9", "1001", 9),
    A("A", "1010", 10),
    B("B", "1011", 11),
    C("C", "1100", 12),
    D("D", "1101", 13),
    E("E", "1110", 14),
    F("F", "1111", 15);

    private String hexValue;
    private String binary;
    private Integer decimal;

    HexadecimalTable(String hexValue, String binary, Integer decimal) {
        this.hexValue = hexValue;
        this.binary = binary;
        this.decimal = decimal;
    }

    public String getHexValue() {
        return hexValue;
    }

    public String getBinary() {
        return binary;
    }

    public Integer getDecimal() {
        return decimal;
    }

    public static String getHexValueFromBinary(String binaryValue) {
        for (HexadecimalTable table : HexadecimalTable.values()) {
            if (table.binary.equals(binaryValue)) return table.getHexValue();
        }
        throw new RuntimeException("Incorrect hex value.");
    }

    public static String getHexValueFromDecimal(Integer decimalValue) {
        for (HexadecimalTable table : HexadecimalTable.values()) {
            if (table.decimal.equals(decimalValue)) return table.getHexValue();
        }
        throw new RuntimeException("Incorrect hex value.");
    }
}
