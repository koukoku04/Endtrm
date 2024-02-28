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

    private Car(Builder builder) {
        this.model = builder.model;
        this.brand = builder.brand;
        this.condition = builder.condition;
        this.year = builder.year;
        this.price = (int) builder.price;

    }

    public static class Builder {
        private String model;
        private String brand;
        private String condition;
        private int year;
        private double price;


        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }


        public Car build() {
            return new Car(this);
        }
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