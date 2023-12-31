package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.io.*;
import java.util.*;

public class DietProgram {
    private List<Food> foodList;
    private HashMap<String, Diet> dietList;
    private List<Patient> patientList;
    private File backupFile;


    public DietProgram(){
        foodList = new ArrayList<>();
        dietList = new HashMap<>();
        patientList = new ArrayList<>();
        backupFile = new File ("src/main/java/com/campusdual/ejercicio6/backupFile.txt");

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
        readPatient();
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

    private void createDiet() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        String dietName = null;
        System.out.println("Introduce un nombre para la nueva dieta:");
        do{
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
        System.out.println("5-Dieta por datos personales automatica");
        System.out.println("6-Cancelar");
        Integer option = getOption(1,6);
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
                diet = new Diet(Gender.getByString(sexCharacter),age,height,weight);
                dietList.put(dietName,diet);
                System.out.println("Se ha creado una dieta de "+ diet.getMaxCalories()+" calorías máximas");
                break;
            case 5:
                //Se queda por seaca pero no pinta nada aqui
                int selectedPatient = selectPatient();
                Patient patient = patientList.get(selectedPatient);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Creando dieta");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                diet = new Diet(patient.getGender(),patient.getAge(),patient.getHeight(),patient.getWeight());
                dietName = dietName + "(" + patient.getName() + " " + patient.getSecondName() + ")";
                dietList.put(dietName,diet);
                System.out.println("Se ha creado la dieta personalizada: -" + dietName + "-");
                break;
            case 6:
                System.out.println("Cancelando...");
                break;
        }
    }

    private void showDietDetailsMenu() {

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

    private void addFoodMenu(String dietKey) {

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
                if(foodList.isEmpty()){
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
                if(Objects.equals(element, i)){
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

    private void removeDiet() {

        System.out.println("Eliminar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        StringBuilder dietUsers = new StringBuilder();
        boolean deleteConfirm = true;
        String selectedDiet = selectDiet();
        if(selectedDiet != null){
            for(Patient patient:patientList) {
                if (patient.getDietListWeek().containsValue(selectedDiet)) {
                    deleteConfirm = false;
                    dietUsers.append(patient.getName()).append(" / ");
                }
            }
            if (deleteConfirm) {
                System.out.println("Dieta: " + selectedDiet + " eliminada.");
                dietList.remove(selectedDiet);
            } else System.out.println("No se pudo eliminar dieta. Esta siendo utilizada por: " + dietUsers);
        }
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

    private int selectPatient(){
        System.out.println("Selecciona un paciente:");
        System.out.println("=================");
        int i = 1;
        for (Patient patient:patientList) {
            System.out.println(i + " - " + patient.getName() + " " + patient.getSecondName());
            i++;
        }
        System.out.println("=================");
        System.out.println(i + " - Salir");
        Integer element = getOption(1,i);
        if(element==i){
            System.out.println("Saliendo...");
            return -1;
        }
        return element-1;
    }

    private void createPatient() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@ Dar de alta paciente @@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Introduzca el nombre del paciente:");
        String patientName = Kb.nextLine();
        System.out.println("Introduzca el primer apellido:");
        String patientSecondName = Kb.nextLine();
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
        updatePatients();
        System.out.println("Paciente "+ patientName + " " + patientSecondName + " dado de alta.");
    }
    private void updatePatients(){
        try(PrintWriter pw = new PrintWriter(new FileWriter(backupFile))){
            for(Patient patientFor :patientList){
                pw.println(patientFor.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al escribir");
        }
    }
    private void readPatient(){
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(backupFile))){
            while((line = br.readLine()) != null) {
                //System.out.println(line);
                Patient patient = new Patient();
                patientList.add(patient.parsePatient(line));
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showPatientMenu() {

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@ Mostrar paciente @@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        int selectedPatient = selectPatient();
        if(selectedPatient != -1){
            showPatient(selectedPatient);
            System.out.println("Desea modificar paciente? ( Si = s / No = n");
            String option = Kb.nextLine();
            if("s".equalsIgnoreCase(option)){
                modifyPatient(selectedPatient);
            }
        }
        System.out.println("Saliendo...");
    }

    private void showPatient(int patientKey) {
        Patient patient = patientList.get(patientKey);
        System.out.println("Nombre: " + patient.getName());
        System.out.println("Apellido: " + patient.getSecondName());
        System.out.println("Peso: " + patient.getWeight());
        System.out.println("Altura: " + patient.getHeight());
        System.out.println("Edad: " + patient.getAge());
        System.out.println("Sexo: " + (Gender.FEMALE.equals(patient.getGender()) ? "Mujer" : "Hombre"));
        System.out.println("Dietas:");
        System.out.println("==========================");
        for(WeekDays days : WeekDays.values()){
            String currentDay = days.getName();
            if(patient.getDietListWeek().containsKey(currentDay)){
                System.out.println(currentDay + " - " + patient.getDietListWeek().get(currentDay));
            }else System.out.println(currentDay + " - NO ASIGNADA");
        }
        System.out.println("==========================");
    }

    private void modifyPatient(int patientIndex) {
        System.out.println("Que atributo desea modificar?");
        System.out.println("1-Nombre");
        System.out.println("2-Apellido");
        System.out.println("3-Peso");
        System.out.println("4-Altura");
        System.out.println("5-Edad");
        System.out.println("6-Sexo");
        System.out.println("7-Salir");
        Integer option = getOption(1,7);
        switch (option){
            case 1:
                System.out.println("introduce nuevo nombre:");
                String newName = Kb.nextLine();
                patientList.get(patientIndex).setName(newName);
                break;
            case 2:
                System.out.println("introduce nuevo Apellido:");
                String newSecondName = Kb.nextLine();
                patientList.get(patientIndex).setSecondName(newSecondName);
                break;
            case 3:
                System.out.println("introduce nuevo peso:");
                int newWeight = Kb.forceNextInt();
                patientList.get(patientIndex).setWeight(newWeight);
                break;
            case 4:
                System.out.println("introduce nueva altura:");
                int newHeight = Kb.forceNextInt();
                patientList.get(patientIndex).setHeight(newHeight);
                break;
            case 5:
                System.out.println("introduce nueva edad:");
                int newAge = Kb.forceNextInt();
                patientList.get(patientIndex).setAge(newAge);
                break;
            case 6:
                System.out.println("introduce nuevo sexo: (Hombre = h / Mujer = m)");
                String newGender = Kb.nextLine();
                patientList.get(patientIndex).setGender(Gender.getByString(newGender));
                break;
            case 7:
                System.out.println("Saliendo...");
                return;
        }
        updatePatients();
    }

    private void addDietPatient() {
        System.out.println("########################################################");
        System.out.println("############### Agregar dieta a paciente ###############:");
        System.out.println("########################################################");
        int selectedPatient = selectPatient();
        System.out.println("Selecciona tipo de dieta:");
        System.out.println("1-Elegir una de la lista");
        System.out.println("2-Personalizada");
        System.out.println("3-Cancelar");
        int option = getOption(1,3);
        String dietName = null;
        switch (option){
            case 1:
                dietName = selectDiet();
                break;
            case 2:
                Patient patient = patientList.get(selectedPatient);
                System.out.println("Creando dieta personalizada:");
                Diet diet = new Diet(patient.getGender(),patient.getAge(),patient.getHeight(),patient.getWeight());
                dietName =  patient.getName() + " " + patient.getSecondName();
                dietList.put(dietName,diet);
                break;
            case 3:
                break;
        }
        String daySelected = WeekDays.getDayFromPosition(selectDay()).getName();
        if (dietName!=null) {
            if (daySelected != null) {

                patientList.get(selectedPatient).getDietListWeek().put(daySelected, dietName);
                updatePatients();
                System.out.println("Dieta con nombre: " + dietName + " -agregada al-" + daySelected);
            } else {
                System.out.println("Algo ha ido mal con el dia");
            }
        }else System.out.println("Algo ha ido mal con el nombre");
    }

    private void removePatient() {
        System.out.println("Dar de baja paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        int selectedPatient = selectPatient();
        if(selectedPatient >= 0){
            System.out.println("Paciente " + patientList.get(selectedPatient).getName() + " " + patientList.get(selectedPatient).getSecondName() + " borrado");
            patientList.remove(selectedPatient);
            updatePatients();
        }else{
            System.out.println("Operacion cancelada");
        }
    }

    private Integer selectDay(){
        System.out.println("Selecciona un dia de la semana:");
        System.out.println("===============================");
        System.out.println("1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miercoles");
        System.out.println("4-Jueves");
        System.out.println("5-Viernes");
        System.out.println("6-Sabado");
        System.out.println("7-Domingo");
        System.out.println("===============================");
        System.out.println("8-Salir");
        int day = getOption(1,8);
        if(day!=8){
            return day;
        }else return null;
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
        davidDemo.getDietListWeek().put(WeekDays.TUESDAY.getName(), "Ligera");
        davidDemo.getDietListWeek().put(WeekDays.THURSDAY.getName(),"Media");
        davidDemo.getDietListWeek().put(WeekDays.MONDAY.getName(),"Fuerte");
        davidDemo.getDietListWeek().put(WeekDays.SUNDAY.getName(),"Ligera");
        davidDemo.getDietListWeek().put(WeekDays.FRIDAY.getName(),"Media");
        patientList.add(davidDemo);
        patientList.add(new Patient("Juan","Negro",60,150,40,"h"));
        patientList.add(new Patient("Sara","Azul",75,190,26,"m"));

    }
}
