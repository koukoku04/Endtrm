package org.example.Methods;


import org.example.Models.Car;

import java.util.List;
import java.util.Scanner;

public class MainHelper {
    private static Scanner scanner;
    private static CarService carService;

    public MainHelper(Scanner scanner, CarService carService) {
        this.scanner = scanner;
        this.carService = carService;
    }

    public int getUserChoice() {
        return scanner.nextInt();
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void displayCarById() {
        System.out.println("Enter car id:");
        int id = scanner.nextInt();
        Car carById = CarService.getCarById(id);
        System.out.println("Car by id: " + carById);
    }

    public static void displayAllCars() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the sorting option:");
        System.out.println("1. Sort by year");
        System.out.println("2. Sort by price");
        System.out.println("3. No sorting");
        int option = scanner.nextInt();
        String sortBy = getSortByOption(option);
        List<Car> allCars = CarService.getAllCars(sortBy);
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
        CarService.updateCar(existingCar);
        System.out.println("Existing car updated: " + existingCar);
    }

    public static void deleteCarById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car id to delete:");
        int deleteId = scanner.nextInt();
        CarService.deleteCar(deleteId);
        System.out.println("Car with id " + deleteId + " deleted.");
    }

    public static void searchCarByModel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter car model to search:");
        String model = scanner.next();
        List<Car> carsByModel = CarService.getCarsByModel(model);
        System.out.println("Cars with model " + model + ":");
        carsByModel.forEach(System.out::println);
    }
}
