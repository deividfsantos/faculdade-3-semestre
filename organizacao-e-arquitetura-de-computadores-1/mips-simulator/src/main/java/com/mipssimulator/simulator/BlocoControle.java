package com.mipssimulator.simulator;

public class BlocoControle {
    private String pcEscCond;
    private String pcEsc;
    private String louD;
    private String lerMem;
    private String escMem;
    private String memParaReg;
    private String iREsc;
    private String fontePC;
    private String ulaOp;
    private String ulaFonteB;
    private String ulaFonteA;
    private String escReg;
    private String regDst;

    public BlocoControle() {
        this.pcEscCond = "0";
        this.pcEsc = "0";
        this.louD = "0";
        this.lerMem = "0";
        this.escMem = "0";
        this.memParaReg = "0";
        this.iREsc = "0";
        this.fontePC = "0";
        this.ulaOp = "0";
        this.ulaFonteB = "0";
        this.ulaFonteA = "0";
        this.escReg = "0";
        this.regDst = "0";
    }

    public String getPcEscCond() {
        return pcEscCond;
    }

    public void setPcEscCond(String pcEscCond) {
        this.pcEscCond = pcEscCond;
    }

    public String getPcEsc() {
        return pcEsc;
    }

    public void setPcEsc(String pcEsc) {
        this.pcEsc = pcEsc;
    }

    public String getLouD() {
        return louD;
    }

    public void setLouD(String louD) {
        this.louD = louD;
    }

    public String getLerMem() {
        return lerMem;
    }

    public void setLerMem(String lerMem) {
        this.lerMem = lerMem;
    }

    public String getEscMem() {
        return escMem;
    }

    public void setEscMem(String escMem) {
        this.escMem = escMem;
    }

    public String getMemParaReg() {
        return memParaReg;
    }

    public void setMemParaReg(String memParaReg) {
        this.memParaReg = memParaReg;
    }

    public String getiREsc() {
        return iREsc;
    }

    public void setiREsc(String iREsc) {
        this.iREsc = iREsc;
    }

    public String getFontePC() {
        return fontePC;
    }

    public void setFontePC(String fontePC) {
        this.fontePC = fontePC;
    }

    public String getUlaOp() {
        return ulaOp;
    }

    public void setUlaOp(String ulaOp) {
        this.ulaOp = ulaOp;
    }

    public String getUlaFonteB() {
        return ulaFonteB;
    }

    public void setUlaFonteB(String ulaFonteB) {
        this.ulaFonteB = ulaFonteB;
    }

    public String getUlaFonteA() {
        return ulaFonteA;
    }

    public void setUlaFonteA(String ulaFonteA) {
        this.ulaFonteA = ulaFonteA;
    }

    public String getEscReg() {
        return escReg;
    }

    public void setEscReg(String escReg) {
        this.escReg = escReg;
    }

    public String getRegDst() {
        return regDst;
    }

    public void setRegDst(String regDst) {
        this.regDst = regDst;
    }

    public void defineOpcode(String opCode) {
        setUlaFonteB("00");
        setUlaFonteA("1");
        final String tipoROpCode = "000000"; // opcode dos tipos R
        if (opCode.equals(tipoROpCode)) {// se o opcode encotrado for o opcode de um tipo R
            setUlaOp("00");
            setUlaOp("10");
            setRegDst("0");
            setEscReg("1");
            setMemParaReg("0");
        }

        final String swOpcode = "101011";// opcode sw
        final String lwOpcode = "100011";// opcode lw
        if (opCode.equals(swOpcode) || opCode.equals(lwOpcode)) {// se o opcode encotrado for o opcode de um sw/lw
            setUlaOp("00");
            setUlaFonteB("10");
            if (opCode.equals(lwOpcode)) {
                setLouD("1");
                setMemParaReg("1");
                setLerMem("1");
                setRegDst("1");
                setEscReg("1");
            }
            if (opCode.equals(swOpcode)) {
                setLouD("1");
                setEscReg("1");
                setRegDst("0");
                setEscReg("1");
                setEscMem("1");
            }
        }
    }

    @Override
    public String toString() {
        return "\nBlocoControle: \n" +
                "pcEscCond: " + pcEscCond + "\t\t\t\t" +
                "pcEsc: " + pcEsc + "\t\t\t\t" +
                "louD: " + louD + "\t\t\t\t" +
                "lerMem: " + lerMem + "\t\t\t\t" +
                "escMem: " + escMem + "\n" +
                "memParaReg: " + memParaReg + "\t\t\t\t" +
                "iREsc: " + iREsc + "\t\t\t\t" +
                "fontePC: " + fontePC + "\t\t\t" +
                "ulaOp: " + ulaOp + "\n" +
                "ulaFonteB: " + ulaFonteB + "\t\t\t\t" +
                "ulaFonteA: " + ulaFonteA + "\t\t\t" +
                "escReg: " + escReg + "\t\t\t" +
                "regDat: " + regDst + "\n";
    }
}

