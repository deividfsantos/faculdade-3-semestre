package com.deividsantos.trabalhoii;

public class Castle {

    private Integer number;
    private Integer armySize;

    public Castle(Integer number, Integer armySize) {
        this.number = number;
        this.armySize = armySize;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getArmySize() {
        return armySize;
    }

    public void setArmySize(Integer armySize) {
        this.armySize = armySize;
    }

    @Override
    public String toString() {
        return "Castle{" +
                "number=" + number +
                ", armySize=" + armySize +
                '}';
    }
}
