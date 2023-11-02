package com.cbcoders.car_app_v1.Cars.model;

import com.cbcoders.car_app_v1.Cars.model.Enums.CarNewOrUsed;
import com.cbcoders.car_app_v1.Users.model.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "carId")
public class UsedCar extends Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer mileage;
    @Column(nullable = true, unique = true)
    private String regNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public UsedCar() {
    }

    public UsedCar(String brand, String model, String color, String chassisNumber, Integer keysNumber, Date dateArrived,
                   CarNewOrUsed carNewOrUsed, Integer mileage, String regNumber, Boolean isSold) {
        super(brand, model, color, chassisNumber, keysNumber, dateArrived, carNewOrUsed, isSold);
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
        this.regNumber = regNumber.toUpperCase();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                ", user=" + user +
                '}' + super.toString();
    }
}
