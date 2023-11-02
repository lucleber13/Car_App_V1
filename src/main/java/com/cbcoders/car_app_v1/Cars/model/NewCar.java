package com.cbcoders.car_app_v1.Cars.model;

import com.cbcoders.car_app_v1.Cars.model.Enums.CarNewOrUsed;
import com.cbcoders.car_app_v1.Users.model.User;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "carId")
public class NewCar extends Car implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public NewCar() {
    }

    public NewCar(String brand, String model, String color, String chassisNumber, Integer keysNumber, Date dateArrived,
                  CarNewOrUsed carNewOrUsed, Boolean isSold) {
        super(brand, model, color, chassisNumber, keysNumber, dateArrived, carNewOrUsed, isSold);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "NewCar{" +
                "user=" + user +
                "} " + super.toString();
    }
}
