package com.campusdual.ejercicio5;

public enum WeekDays {
    MONDAY(1,"Lunes"),
    TUESDAY(2,"Martes"),
    WEDNESDAY(3,"Miercoles"),
    THURSDAY(4,"Jueves"),
    FRIDAY(5,"Viernes"),
    SATURDAY(6,"Sabado"),
    SUNDAY(5,"Domingo");

    private Integer position;
    private String Name;

    WeekDays(Integer position, String name) {
        this.position = position;
        Name = name;
    }

    public Integer getPosition() {
        return position;
    }

    public String getName() {
        return Name;
    }
    public static WeekDays getDayFromPosition(Integer position){
        for(WeekDays day :values()){
            if(day.getPosition()==position){
                return day;
            }
        }
        return null;
    }
}
