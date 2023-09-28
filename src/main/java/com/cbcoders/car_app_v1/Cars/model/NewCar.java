package com.cbcoders.car_app_v1.Cars.model;

import com.cbcoders.car_app_v1.Cars.model.Enums.SoldOrStock;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(name = "carId")
@Table(name = "newCar")
public class NewCar extends Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /*@ManyToOne
    @JoinColumn(name = "userId")
    private User user;*/

    public NewCar() {
    }

    public NewCar(String brand, String model, String color, String chassisNumber, Integer keysNumber, Date dateArrived,
                  SoldOrStock soldOrStock, String customerName) {
        super(brand, model, color, chassisNumber, keysNumber, dateArrived, soldOrStock, customerName);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewCar newCar)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCustomerName(), newCar.getCustomerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCustomerName());
    }

    @Override
    public String toString() {
        return "NewCar{" +
                '}' + super.toString();
    }
}
