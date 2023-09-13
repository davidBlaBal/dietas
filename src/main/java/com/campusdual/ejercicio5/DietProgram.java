package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

public class DietProgram {

    private Diet diet=null;

    private List<Food> foodList;
    private HashMap<String, Diet> dietList = new HashMap<>();
    private HashMap<String, Patient> patientList = new HashMap<>();

    public DietProgram(){
        foodList = new ArrayList<>();
    }

    private Integer getOption(Integer min,Integer max){
        Integer option = 0;
        Boolean notvalid = true;
        do{
            try {
                option = Kb.forceNextInt();
                notvalid = option<min || option>max;
            }catch (InputMismatchException e){
                System.out.println("La opción debe ser un número");
                Kb.nextLine();
            }
            if(notvalid){
                System.out.println("Opción no valida, se requiere un número entre "+min+" y "+max);
            }
        }while(notvalid);
        return option;
    }

    public void dietManagerMenu(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Crear dieta");
            System.out.println("2-Mostrar información de la dieta");
            System.out.println("3-Eliminar dieta");
            System.out.println("4-Salir del programa");
            option = getOption(1,4);
            switch (option){
                case 1:
                    createDiet();
                    break;
                case 2:
                    showDietDetailsMenu();
                    break;
                case 3:
                    removeDiet();
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        }while(option != 4);
    }

    public void patientManagerMenu(){
        System.out.println("########################################################");
        System.out.println("################ Gestión de pacientes ##################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Dar de alta paciente");
            System.out.println("2-Listar/Mostrar detalles pacientes");
            System.out.println("3-Asignar dieta");
            System.out.println("4-Dar de baja ");
            System.out.println("5-Salir");
            option = getOption(1,5);
            switch (option){
                case 1:
                    createPatient();
                    break;
                case 2:
                    showPatient();
                    break;
                case 3:
                    addDietPatient();
                    break;
                case 4:
                    removePatient();
                    break;
                case 5:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        }while(option != 5);
    }

    private void createPatient() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Dar de alta paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        boolean close = false;
        String patientName, patientSecondName, patientKey;
        do {
            System.out.println("Introduzca el nombre del paciente:");
            patientName = Kb.nextLine();
            System.out.println("Introduzca el primer apellido:");
            patientSecondName = Kb.nextLine();
            patientKey = patientName + " " + patientSecondName;
            if(patientName =="s" || patientSecondName == "s"){
                System.out.println("Saliendo...");
                return;
            }
            if (!patientList.containsKey(patientKey)){
                close= true;
            }else{
                System.out.println("Ya existe un paciente con ese nombre y apellido. Cambiese el nombre :)");
            }
        }while (!close);
        System.out.println("Introduzca el peso:");
        int patientWeight = Kb.forceNextInt();
        System.out.println("Introduzca la altura:");
        int patientHeight = Kb.forceNextInt();
        System.out.println("Introduzca edad:");
        int patientAge = Kb.forceNextInt();
        System.out.println("Introduzca sexo:(Hombre = h / Mujer = m");
        String patientSex = Kb.nextLine();
        Patient patient = new Patient(patientName,patientSecondName,patientWeight,patientHeight,patientAge,patientSex);
        patientList.put(patientKey,patient);
        System.out.println("Paciente "+ patientKey + " dado de alta.");

    }

    private void showPatient() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Mostrar paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    private void addDietPatient() {
    }

    private void removePatient() {
        //TODO comprobar que no la tenga asignada un paciente
        System.out.println("Dar de baja paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Seleccione un paciente una opción:");
        System.out.println("===================================");
        String option = null;
        boolean salir = false;
        do {
            int i = 1;
            for (String key : patientList.keySet()) {
                System.out.println(i + " - " + key);
            }
            System.out.println("Teclea el nombre y apellido del paciente o s para salir");

            option = Kb.nextLine();
            if ("s".equalsIgnoreCase(option)){
                System.out.println("Saliendo...");
                return;
            }
            if (patientList.containsKey(option)){
                System.out.println("Se va a borrar el paciente: " + option);
                dietList.remove(option);
                salir = true; // probar break
            }else{
                System.out.println("No se encuentra el paciente: " + option);
            }
        }while(!salir);
    }


    private void removeDiet() {
        //TODO comprobar que no la tenga asignada un paciente
        System.out.println("Eliminar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        String option = null;
        boolean salir = false;
        do {


            int i = 1;
            for (String key : dietList.keySet()) {
                System.out.println(i + " - " + key);
            }
            System.out.println("Teclea el nombre de la dieta o s para salir");

            option = Kb.nextLine();
            if ("s".equalsIgnoreCase(option)){
                System.out.println("Saliendo...");
                break;
            }
            if (dietList.containsKey(option)){
                System.out.println("Se va a borrar la dieta: " + option);
                dietList.remove(option);
                salir = true; // probar break
            }else{
                System.out.println("No se encuentra la dieta: " + option);
            }
        }while(!salir);
    }

    private void addFoodMenu() {
        if(this.diet==null){
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = getOption(1,2);
        switch (option){
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(newFood,grams);
                foodList.add(newFood);
                break;
            case 2:
                if(foodList.size()==0){
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foodList){
                    System.out.println(i+"- "+food.getName());
                    i++;
                }
                System.out.println(i+"- Cancelar");
                Integer element = getOption(1,i);
                if(element==i){
                    System.out.println("Cancelando alimento");
                    return;
                }
                Food storedFood = foodList.get(element-1);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(storedFood,foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Food food, Integer grams){
        try{
            this.diet.addFood(food,grams);
        }catch (MaxCaloriesReachedException ecal){
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
        }catch (MaxCarbsReachedException ecar){
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
        }catch (MaxFatsReachedException efat){
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
        }catch (MaxProteinsReachedException epro){
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createDiet() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String dietName = null;
        System.out.println("Introduce un nombre para la nueva dieta:");
        do{
            //TODO validar si no se repite el nombre1
            dietName = Kb.nextLine();
        }while (dietName==null);

        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = getOption(1,4);
        Diet diet = null;
        switch (option){
            case 1:
                diet = new Diet();
                dietList.put(dietName,diet);
                System.out.println("Se ha añadido una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                diet = new Diet(calories);
                dietList.put(dietName,diet);
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                diet = new Diet(fats,carbs,proteins);
                dietList.put(dietName,diet);
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                diet = new Diet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                dietList.put(dietName,diet);
                System.out.println("Se ha creado una dieta de "+this.diet.getMaxCalories()+" calorías máximas");
                break;
        }
    }

    private void showDietDetailsMenu() {
        Diet diet = null;
        Boolean close = false;
        do {
            int i = 1;
            for (String key : dietList.keySet()) {
                System.out.println(i + " - " + key);
            }
            System.out.println("Teclea el nombre de la dieta que quieres ver o s para salir:");
            String option = Kb.nextLine();
            diet = dietList.get(option);
            if ("s".equalsIgnoreCase(option)){
                System.out.println("Saliendo...");
                return;
            }
            if (dietList.containsKey(option)){
                System.out.println("Se va a mostrar la dieta: " + option);
                diet = dietList.get(option);
                close = true; // probar break
            }else{
                System.out.println("No se encuentra la dieta: " + option);
            }
        }while(!close);

        if(diet!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if(diet.getMaxCalories()!=null){
                System.out.println("El número máximo de calorías es:"+ diet.getMaxCalories());
            }
            if(diet.getMaxCarbs() != null || diet.getMaxFats() != null || diet.getMaxProteins() != null){
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+ diet.getMaxCarbs()+" , Grasas:"+ diet.getMaxFats()+" , Proteinas:"+ diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:"+ diet.getFoodNumber());
            System.out.println("Calorías:"+ diet.getTotalCalories());
            System.out.println("Carbohidratos:"+ diet.getTotalCarbs());
            System.out.println("Grasas:"+ diet.getTotalFats());
            System.out.println("Proteínas:"+ diet.getTotalProteins());
            System.out.println("Alimentos de la dieta:"+ diet.getDietIntakes());
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }
}
