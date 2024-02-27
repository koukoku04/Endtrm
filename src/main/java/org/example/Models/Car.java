package org.example.Models;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;

    private String condition;

    private int price;
    private String number;

    public Car() {

    }

    public Car(String brand, String model,int year, String condition, int price, String number){
        this.brand=brand;
        this.model = model;
        this.year=year;
        this.condition=condition;
        this.price=price;
        this.number=number;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                ", Contact number of owner=" + number +
                '}';
    }




}