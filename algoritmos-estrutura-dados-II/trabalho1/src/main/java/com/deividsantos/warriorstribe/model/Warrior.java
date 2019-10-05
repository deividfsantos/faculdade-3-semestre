package com.deividsantos.warriorstribe.model;

public class Warrior {
    private String name;
    private String father;
    private Integer lands;

    public Warrior(String name, String father, Integer lands) {
        this.name = name;
        this.father = father;
        this.lands = lands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public Integer getLands() {
        return lands;
    }

    public void setLands(Integer lands) {
        this.lands = lands;
    }

    @Override
    public String toString() {
        return "\nWarrior" +
                "\nName: " + name + '\'' +
                "\nFather: " + father + '\'' +
                "\nLands: " + lands;
    }
}
