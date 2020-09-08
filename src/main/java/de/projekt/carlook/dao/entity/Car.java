package de.projekt.carlook.dao.entity;

public class Car {

    private int id;

    private String brand;

    private int year;

    private String description;

    public Car() {
    }

    public Car(String brand, int year, String description) {
        this.brand = brand;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
