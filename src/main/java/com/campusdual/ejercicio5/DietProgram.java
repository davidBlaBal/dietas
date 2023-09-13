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

    //private Diet diet=null;

    private List<Food> foodList;
    private HashMap<String, Diet> dietList = new HashMap<>();
    //private HashMap<String, Patient> patientList = new HashMap<>();
    private List<Patient> patientList = new ArrayList<>();

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
    public void clinicMenu(){
        System.out.println("########################################################");
        System.out.println("################### Menú principal #####################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestion de dietas");
            System.out.println("2-Gestion de pacientes");
            System.out.println("3-Salir del programa");
            option = getOption(1,4);
            switch (option){
                case 1:
                    dietManagerMenu();
                    break;
                case 2:
                    patientManagerMenu();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
                case 4:
                    testData();
                    break;
            }
        }while(option != 3);
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
            System.out.println("4-Volver");
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
            System.out.println("5-Volver");
            option = getOption(1,5);
            switch (option){
                case 1:
                    createPatient();
                    break;
                case 2:
                    showPatientMenu();
                    break;
                case 3:
                    addDietPatient();
                    break;
                case 4:
                    removePatient();
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal");
                    break;
            }
        }while(option != 5);
    }

    private void createPatient() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@ Dar de alta paciente @@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Introduzca el nombre del paciente:");
        String patientName = Kb.nextLine();
        System.out.println("Introduzca el primer apellido:");
        String patientSecondName = Kb.nextLine();
/*        boolean close = false;
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
        }while (!close);*/
        System.out.println("Introduzca el peso:");
        int patientWeight = Kb.forceNextInt();
        System.out.println("Introduzca la altura:");
        int patientHeight = Kb.forceNextInt();
        System.out.println("Introduzca edad:");
        int patientAge = Kb.forceNextInt();
        System.out.println("Introduzca sexo:(Hombre = h / Mujer = m");
        String patientSex = Kb.nextLine();
        Patient patient = new Patient(patientName,patientSecondName,patientWeight,patientHeight,patientAge,patientSex);
        patientList.add(patient);
        System.out.println("Paciente "+ patientName + " " + patientSecondName + " dado de alta.");

    }

    private void showPatientMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@ Mostrar paciente @@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        int selectedPatient = selectPatient();
        showPatient(selectedPatient);
/*        String option = null;
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
                System.out.println("Se va a mostrar el paciente: " + option);
                showPatient(option);
                salir = true; // probar break
            }else{
                System.out.println("No se encuentra el paciente: " + option);
            }
        }while(!salir);*/
    }

    private void showPatient(int patientKey) {
        Patient patient = patientList.get(patientKey);
        System.out.println("Nombre: " + patient.getName());
        System.out.println("Apellido: " + patient.getSecondName());
        System.out.println("Peso: " + patient.getWeight());
        System.out.println("Altura: " + patient.getHeight());
        System.out.println("Edad: " + patient.getAge());
        System.out.println("Sexo: " + patient.getSex());
        System.out.println("Dietas:");

        for(String key:patient.getDietListWeek().keySet()){
            System.out.println(key + " " + patient.getDietListWeek().get(key));
        }
    }

    private void addDietPatient() {
        System.out.println("########################################################");
        System.out.println("############### Agregar dieta a paciente ###############:");
        System.out.println("########################################################");
        System.out.println("Seleccione un paciente:");
        System.out.println("===================================");
        int selectedPatient = selectPatient();

/*        boolean salir = false;
        do {
            int i = 1;
            for (String key : patientList.keySet()) {
                System.out.println(i + " - " + key);
            }
            System.out.println("Teclea el nombre y apellido del paciente o s para salir");

            patientName = Kb.nextLine();
            if ("s".equalsIgnoreCase(patientName)){
                System.out.println("Saliendo...");
                return;
            }
            if (patientList.containsKey(patientName)){
                System.out.println("Accediendo a: " + patientName);
                salir = true; // probar break
            }else{
                System.out.println("No se encuentra el paciente: " + patientName);
            }
        }while(!salir);*/
        String dietName = selectDiet();
        String daySelected = selectDay();
        if (daySelected != null){
            patientList.get(selectedPatient).getDietListWeek().put(daySelected,dietName);
            System.out.println(patientList.get(selectedPatient).getName() + " Dieta: " + dietName + " agregada al " + daySelected);
        }else{
            System.out.println("Saliendo ...");
        }


    }
    private String selectDay(){
        System.out.println("Selecciona un dia de la semana:");
        System.out.println("1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miercoles");
        System.out.println("4-Jueves");
        System.out.println("5-Viernes");
        System.out.println("6-Sabado");
        System.out.println("7-Domingo");
        System.out.println("8-Salir");
        int day = Kb.forceNextInt();
        String dayLetter = null;
        switch (day){
            case 1:
                dayLetter = "m";
                break;
            case 2:
                dayLetter = "t";
                break;
            case 3:
                dayLetter = "w";
                break;
            case 4:
                dayLetter = "th";
                break;
            case 5:
                dayLetter = "f";
                break;
            case 6:
                dayLetter = "s";
                break;
            case 7:
                dayLetter = "su";
                break;
            case 8:
            return null;
        }
        return dayLetter;
    }


    private void removePatient() {
        System.out.println("Dar de baja paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        int selectedPatient = selectPatient();
        if(selectedPatient>-1){
            System.out.println("Paciente " + patientList.get(selectedPatient).getName() + " " + patientList.get(selectedPatient).getSecondName() + " borrado");
            patientList.remove(selectedPatient);
        }else{
            System.out.println("Operacion cancelada");
        }

/*        System.out.println("Seleccione un paciente:");
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
                patientList.remove(option);
                salir = true; // probar break
            }else{
                System.out.println("No se encuentra el paciente: " + option);
            }
        }while(!salir);*/
    }

    private int selectPatient(){
            System.out.println("Elige una opcion");
            int i = 1;
            for (Patient patient:patientList) {
                System.out.println(i + " - " + patient.getName() + " " + patient.getSecondName());
                i++;
            }
            System.out.println(i + " - Salir");
            Integer element = getOption(1,i);
            if(element==i){
                System.out.println("Saliendo...");
                return -1;
            }
            return element-1;
    }


    private void removeDiet() {

        System.out.println("Eliminar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String dietUsers = "";
        boolean deleteConfirm = true;
        String selectedDiet = selectDiet();
        if(selectedDiet != null){
            for(Patient patient:patientList) {
                if (patient.getDietListWeek().containsValue(selectedDiet)) {
                    deleteConfirm = false;
                    dietUsers = dietUsers + patient.getName() + " ";
                }
            }
            if (deleteConfirm) {
                System.out.println("Dieta: " + selectedDiet + " eliminada.");
                dietList.remove(selectedDiet);
            } else System.out.println("No se pudo eliminar dieta. Esta siendo utilizada por: " + dietUsers);
        }
    }
/*        String option = null;
        boolean salir = false;
        do {


            int i = 1;
            for (String key : dietList.keySet()) {
                System.out.println(i + " - " + key);
                i++;
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
        }while(!salir);*/

    private String selectDiet(){

        List<String> dietKeys = new ArrayList<>();
        int i = 1;
        for (String key : dietList.keySet()) {
            dietKeys.add(key);
            System.out.println(i + " - " + key);
            i++;
        }
        System.out.println(i + " - Salir");
        Integer element = getOption(1,i);
        if (element==i){
            System.out.println("Saliendo...");
            return null;
        }else{
            return dietKeys.get(element-1);
        }
    }

    private void addFoodMenu(String dietKey) {
        //String dietKey = selectDiet();
        Diet diet = dietList.get(dietKey);
        if(diet==null){
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
                validateAndAddFoodToDiet(diet,newFood,grams);
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
                validateAndAddFoodToDiet(diet,storedFood,foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Diet diet, Food food, Integer grams){
        try{
            diet.addFood(food,grams);
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
            if (dietList.containsKey(dietName)){
                System.out.println("El nombre de dieta ya existe");
                dietName= null;
            }
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
                System.out.println("Se ha creado una dieta de "+ diet.getMaxCalories()+" calorías máximas");
                break;
        }
    }

    private void showDietDetailsMenu() {
/*        Diet diet = null;
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
        }while(!close);*/
        String dietName = selectDiet();
        Diet diet = dietList.get(dietName);

        if(diet!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta " + dietName );
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
            System.out.println("Desea añadir alimentos? (Si = s / No = n");
            String opcion = Kb.nextLine();
            if ("s".equalsIgnoreCase(opcion)){
                addFoodMenu(dietName);
            }else{
                System.out.println("Ok, saliendo...");
            }
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }
    private void testData(){
        System.out.println("Creando datos de prueba");
        foodList.add(new Food("Patata",50,5,0));
        foodList.add(new Food("Filete",6,10,60));
        foodList.add(new Food("Manzana",5,1,2));
        dietList.put("Ligera",new Diet());
        dietList.put("Media",new Diet(2000));
        dietList.put("Fuerte",new Diet(400,100,300));
        Patient davidDemo = new Patient("David","Blanco",60,170,29,"h");
        davidDemo.getDietListWeek().put("m","Ligera");
        davidDemo.getDietListWeek().put("t","Media");
        davidDemo.getDietListWeek().put("th","Fuerte");
        davidDemo.getDietListWeek().put("s","Ligera");
        davidDemo.getDietListWeek().put("su","Media");
        patientList.add(davidDemo);
        patientList.add(new Patient("Juan","Negro",60,150,40,"h"));
        patientList.add(new Patient("Sara","Azul",75,190,26,"m"));

    }
}
