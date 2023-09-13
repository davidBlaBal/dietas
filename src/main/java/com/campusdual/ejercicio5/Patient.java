package com.campusdual.ejercicio5;

import java.util.HashMap;

public class Patient {
    private String name;
    private String secondName;
    private int weight;
    private int height;
    private int age;
    private String sex;

    private HashMap<String , String> dietListWeek;

    public Patient(String name, String secondName, int weight, int height, int age, String sex) {
        this.name = name;
        this.secondName = secondName;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
        this.dietListWeek = new HashMap<>();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public HashMap<String, String> getDietListWeek() {
        return dietListWeek;
    }

    public void setDietListWeek(HashMap<String, String> dietListWeek) {
        this.dietListWeek = dietListWeek;
    }
}
