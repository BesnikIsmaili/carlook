package de.projekt.carlook.dao.entity;

public class ReservedCar {

    private int reservationId;

    private Car car;

    public ReservedCar(int reservationId, Car car){
        this.reservationId = reservationId;
        this.car = car;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getBrand() {
        return car.getBrand();
    }

    public String getDescription() {
        return car.getDescription();
    }

    public int getYear() {
        return car.getYear();
    }
}
