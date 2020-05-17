package com.trabalhomips.type;

public enum Registers {
    $0(0),
    $1(1),
    $2(2),
    $3(3),
    $4(4),
    $5(5),
    $6(6),
    $7(7),
    $8(8),
    $9(9),
    $10(10),
    $11(11),
    $12(12),
    $13(13),
    $14(14),
    $15(15),
    $16(16),
    $17(17),
    $18(18),
    $19(19),
    $20(20),
    $21(21),
    $22(22),
    $24(24),
    $25(25),
    $26(26),
    $27(27),
    $28(28),
    $29(29),
    $30(30),
    $31(31);

    private final Integer registerNumber;

    Registers(Integer registerNumber) {
        this.registerNumber = registerNumber;
    }

    public Integer getRegisterNumber() {
        return registerNumber;
    }

    public static String getRegisterByDecimal(Integer value) {
        for (Registers register : Registers.values()) {
            if (register.getRegisterNumber().equals(value)) return register.name();
        }
        throw new RuntimeException("Incorrect hex value.");
    }
}
