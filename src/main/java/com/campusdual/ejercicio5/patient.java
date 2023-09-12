package com.campusdual.ejercicio5;

import java.util.HashMap;

public class patient {
    private String name;
    private String secondName;
    private int weight;
    private int height;
    private int age;
    private char sex;

    private HashMap<String , Diet> dietListWeek;

    public patient(String name, String secondName, int weight, int height, int age, char sex, HashMap<String, Diet> dietListWeek) {
        this.name = name;
        this.secondName = secondName;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.dietListWeek = dietListWeek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public HashMap<String, Diet> getDietListWeek() {
        return dietListWeek;
    }

    public void setDietListWeek(HashMap<String, Diet> dietListWeek) {
        this.dietListWeek = dietListWeek;
    }
}
