package com.campusdual.ejercicio6;

import java.util.HashMap;

public class Patient {
    private String name;
    private String secondName;
    private int weight;
    private int height;
    private int age;
    private Gender gender;

    private HashMap<String , String> dietListWeek;

    public Patient(String name, String secondName, int weight, int height, int age, String gender) {
        this.name = name;
        this.secondName = secondName;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = Gender.getByString(gender);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public HashMap<String, String> getDietListWeek() {
        return dietListWeek;
    }

    public void setDietListWeek(HashMap<String, String> dietListWeek) {
        this.dietListWeek = dietListWeek;
    }
}
