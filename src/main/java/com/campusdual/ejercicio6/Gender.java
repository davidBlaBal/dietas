package com.campusdual.ejercicio6;

public enum Gender {
    FEMALE,
    MALE;
    public static Gender getByString(String genderKey){
        if("m".equalsIgnoreCase(genderKey) || "mujer".equalsIgnoreCase(genderKey)){
            return Gender.FEMALE;
        }
        else if("h".equalsIgnoreCase(genderKey) || "hombre".equalsIgnoreCase(genderKey)){
            return Gender.MALE;
        }
        return null;
    }
}
