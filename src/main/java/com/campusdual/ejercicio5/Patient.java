package com.campusdual.ejercicio5;

import java.util.HashMap;

public class Patient {
    private String name;
    private String secondName;
    private int weight;
    private int height;
    private int age;
    private Gender gender;

    private HashMap<String , String> dietListWeek;
    public Patient() {
    }
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

    @Override
    public String toString() {
        String genderSTR = Gender.FEMALE.equals(gender) ? "Mujer" : "Hombre";
        String result = name +";"+ secondName+";"+weight+";"+height+";"+age +";"+ genderSTR + ";";
        Boolean first = true;
        for(String day :dietListWeek.keySet()){
            if(!"".equals(result)){
                if(first){
                    result = result + day +":"+getDietListWeek().get(day);
                    first = false;
                }else result = result + "," + day +":"+getDietListWeek().get(day);
            }

        }
        return result;
    }
    public Patient parsePatient(String patientStr){
        String[] arrPatient = patientStr.split(";");
        Patient result = new Patient(arrPatient[0],
                arrPatient[1],
                Integer.parseInt(arrPatient[2]),
                Integer.parseInt(arrPatient[3]),
                Integer.parseInt(arrPatient[4]),
                arrPatient[5]);
        if(arrPatient.length > 6){
            String[] dietDays = arrPatient[6].split(",");
            for(String days :dietDays){
                String[] dayDiet = days.split(":");
                //System.out.println(dayDiet[0]+ " " + dayDiet[1]);
                result.getDietListWeek().put(dayDiet[0],dayDiet[1]);
           }
        }
        return result;
    }
}

