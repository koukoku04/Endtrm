package org.example.View;


import org.example.Methods.CarController;
import org.example.Models.Car;


import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CarView {private static final AtomicInteger count = new AtomicInteger(0);

    public static final HashMap<String, ArrayList<String>> cars = new HashMap<>();

    public CarView() throws SQLException {
    }
    public static void CarService() {
        cars.put("KIA", new ArrayList<>(Arrays.asList("Ceed","Cerato","K3","Picanto","Ray","Rio","K5","K7","K8","K9","Proceed","Soul","EV5","EV6","EV9","Mohave","Niro","Seltos","Sorento","Sportage","Telluride")));
        cars.put("Hyuinday", new ArrayList<>(Arrays.asList("Accent","i10","i20","i30","Elantra","Grandeur","Sonata","Bayon","Palisade","Tucson","Staria")));
        cars.put("Toyota", new ArrayList<>(Arrays.asList("Supra","Camry","Alphard","Land Cruiser","RAV4","Avalon","Corolla","Mark","Mark2","Century","Crown","Yaris","C-HR","Highlander","Fortuner")));
        cars.put("Chevrolet", new ArrayList<>(Arrays.asList("Blazer","Bolt" ,"Chevrolet Camaro","Chevrolet Captiva"," Cobalt","Colorado","Corvette","Cruze","Damas","Equinox","Express", "Groov", "Impala", "Lacetti", "Malibu","Menlo", "Monza", "Nexia", "Niva","\n" +
                "Onix","Orlando","Silverado","Spark" ,"Tahoe","Tracker","TrailBlazer","Traverse")));
        cars.put("LADA", new ArrayList<>(Arrays.asList("Vesta","Largus","X-cross","Granta","Niva","2101","2103","2105","2107","Samara","110","Kalina","Priora","Xray")));
        cars.put("AUDI", new ArrayList<>(Arrays.asList("A8","A10","A6","A4","A2","A1","rs6","rs7","RS3","Q3","Q5","Q7")));
        cars.put("Mercedes", new ArrayList<>(Arrays.asList("A-class","CLA","C-class","E-class","EQE","EQS","S-class","G-class","GLE","GLS","AMG-GT","B-class","Sprinter")));
        cars.put("BMW", new ArrayList<>(Arrays.asList("X1","X2","X3","X4","X5","X6","X7","XM","Z4","I4","1-series","2-series","3-series","4-series","5-series","6-series","7-series")));
        cars.put("Ford", new ArrayList<>(Arrays.asList("Focus","Mustang","Bronco","Edge","Equator","Escape","Everest","Expedition","Explorer","F-series","Ranger","Transit")));
        cars.put("Renault", new ArrayList<>(Arrays.asList("Klio","Kwid","Megane","Kardian","Sandero","Twingo","Arkana","Captur","Duster","Dokkeralaskan")));
        addCar();
    }
    public static void addCar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car brand:");
        int i = 1;
        for(String brand : cars.keySet()) {
            System.out.println(i + ". " + brand);
            i++;
        }
        int brandChoice = scanner.nextInt();
        String brand = (String) cars.keySet().toArray()[brandChoice - 1];

        System.out.println("Enter car model:");
        ArrayList<String> models = cars.get(brand);
        int j = 1;
        for(String model : models) {
            System.out.println(j + ". " + model);
            j++;
        }
        int modelChoice = scanner.nextInt();
        String model = models.get(modelChoice - 1);

        Car newCar = new Car();
        newCar.setBrand(brand);
        newCar.setModel(model);
        System.out.println("Enter car condition:");
        scanner.nextLine();
        newCar.setCondition(scanner.nextLine());
        System.out.println("Enter car year:");
        newCar.setYear(scanner.nextInt());
        System.out.println("Enter car price:");
        newCar.setPrice(scanner.nextInt());
        System.out.println("Enter the contact number of owner:");
        scanner.nextLine();
        newCar.setNumber(scanner.nextLine());
        CarController.save(newCar);
        System.out.println("New car saved: " + newCar);

    }

    public static void updateExistingCar() {
        Scanner scanner = new Scanner(System.in);
        Car existingCar = new Car();
        System.out.println("Enter car id to update:");
        existingCar.setId(scanner.nextInt());
        Scanner bam = new Scanner(System.in);
        System.out.println("Enter updated model:");
        existingCar.setModel(bam.nextLine());
        Scanner tam = new Scanner(System.in);
        System.out.println("Enter updated brand:");
        existingCar.setBrand(tam.nextLine());
        Scanner am = new Scanner(System.in);
        System.out.println("Enter updated condition:");
        existingCar.setCondition(am.nextLine());
        Scanner vam = new Scanner(System.in);
        System.out.println("Enter updated price:");
        existingCar.setPrice(vam.nextInt());
        Scanner tak = new Scanner(System.in);
        System.out.println("Enter updated contact number:");
        existingCar.setNumber(tak.nextLine());
        CarView.updateCar(existingCar);
        System.out.println("Existing car updated: " + existingCar);
    }
    public static void displayAllCars() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the sorting option:");
        System.out.println("1. Sort by year");
        System.out.println("2. Sort by price");
        System.out.println("3. No sorting");
        int option = scanner.nextInt();
        String sortBy = getSortByOption(option);
        List<Car> allCars = CarView.getAllCars(sortBy);
        System.out.println("All cars:");
        allCars.forEach(System.out::println);
    }

    private static String getSortByOption(int option) {
        switch (option) {
            case 1:
                return "year";
            case 2:
                return "price";
            case 3:
                return "id";
            default:
                throw new IllegalArgumentException("Invalid sorting option");
        }
    }



    public static void deleteCarById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car id to delete:");
        int deleteId = scanner.nextInt();
        CarView.deleteCar(deleteId);
        System.out.println("Car with id " + deleteId + " deleted.");
    }

    public static void searchCarByModel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car model to search:");
        String model = scanner.next();
        List<Car> carsByModel = CarView.getCarsByModel(model);
        System.out.println("Cars with model " + model + ":");
        carsByModel.forEach(System.out::println);
    }

    public static List<Car> getAllCars(String sortBy) {
        return CarController.getAll(sortBy);}

    public static void updateCar(Car car) {
        CarController.update(car);}

    public static void deleteCar(int id) {
        CarController.delete(id);}

    public static List<Car> getCarsByModel(String model) {
        return CarController.getByModel(model);}
}