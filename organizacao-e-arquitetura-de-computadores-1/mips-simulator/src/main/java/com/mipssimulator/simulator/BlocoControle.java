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
    private String regDat;

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
        this.regDat = "0";
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

    public String getRegDat() {
        return regDat;
    }

    public void setRegDat(String regDat) {
        this.regDat = regDat;
    }

    @Override
    public String toString() {
        return "BlocoControle{" +
                "pcEscCond='" + pcEscCond + '\'' +
                ", pcEsc='" + pcEsc + '\'' +
                ", louD='" + louD + '\'' +
                ", lerMem='" + lerMem + '\'' +
                ", escMem='" + escMem + '\'' +
                ", memParaReg='" + memParaReg + '\'' +
                ", iREsc='" + iREsc + '\'' + "\n" +
                ", fontePC='" + fontePC + '\'' +
                ", ulaOp='" + ulaOp + '\'' +
                ", ulaFonteB='" + ulaFonteB + '\'' +
                ", ulaFonteA='" + ulaFonteA + '\'' +
                ", escReg='" + escReg + '\'' +
                ", regDat='" + regDat + '\'' +
                '}';
    }
}
