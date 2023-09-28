package com.cbcoders.car_app_v1.Cars.model;

import com.cbcoders.car_app_v1.Cars.model.Enums.SoldOrStock;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "carId")
@Table(name = "usedCar")
public class UsedCar extends Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(nullable = false)
    private Integer mileage;
    @Column(nullable = false)
    private String regNumber;

    /*@ManyToOne
    @JoinColumn(name = "userId")
    private User user;*/

    public UsedCar() {
    }

    public UsedCar(String brand, String model, String color, String chassisNumber, Integer keysNumber, Date dateArrived,
                   SoldOrStock soldOrStock, String customerName, Integer mileage, String regNumber) {
        super(brand, model, color, chassisNumber, keysNumber, dateArrived, soldOrStock, customerName);
        this.mileage = mileage;
        this.regNumber = regNumber;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsedCar usedCar)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getMileage(), usedCar.getMileage()) &&
                Objects.equals(getRegNumber(), usedCar.getRegNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMileage(), getRegNumber());
    }

    @Override
    public String toString() {
        return "UsedCar{" +
                "mileage=" + mileage +
                ", regNumber='" + regNumber + '\'' +
                '}' + super.toString();
    }
}
