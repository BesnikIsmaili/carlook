package de.projekt.carlook.dao.entity;

public class Reservation {

    private int id;

    private int car_id;

    private String email;

    public Reservation() {
    }

    public Reservation(int car_id, String email) {
        this.car_id = car_id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
