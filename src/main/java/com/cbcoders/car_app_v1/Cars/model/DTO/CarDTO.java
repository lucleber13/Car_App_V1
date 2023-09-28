package com.cbcoders.car_app_v1.Cars.model.DTO;

import com.cbcoders.car_app_v1.Cars.model.Enums.SoldOrStock;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

public class CarDTO { // User user
    private Long carId;
    private String brand;
    private String model;
    private String color;
    private String chassisNumber;
    private Integer keysNumber;
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Europe/London")
    @Temporal(TemporalType.DATE)
    private Date dateArrived;
    @Enumerated(EnumType.STRING)
    private SoldOrStock soldOrStock;
    private String customerName;
    private String regNumber;
    private Integer mileage;
    //private User user;

    public CarDTO() {
    }

    public CarDTO(String brand, String model, String color, String chassisNumber, Integer keysNumber, Date dateArrived,
                  SoldOrStock soldOrStock, String customerName, String regNumber, Integer mileage) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.chassisNumber = chassisNumber;
        this.keysNumber = keysNumber;
        this.dateArrived = dateArrived;
        this.soldOrStock = soldOrStock;
        this.customerName = customerName;
        this.regNumber = regNumber;
        this.mileage = mileage;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Integer getKeysNumber() {
        return keysNumber;
    }

    public void setKeysNumber(Integer keysNumber) {
        this.keysNumber = keysNumber;
    }

    public Date getDateArrived() {
        return dateArrived;
    }

    public void setDateArrived(Date dateArrived) {
        this.dateArrived = dateArrived;
    }

    public SoldOrStock getSoldOrStock() {
        return soldOrStock;
    }

    public void setSoldOrStock(SoldOrStock soldOrStock) {
        this.soldOrStock = soldOrStock;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @Override
    public String toString() {
        return "CarDTO{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", keysNumber=" + keysNumber +
                ", dateArrived=" + dateArrived +
                ", soldOrStock=" + soldOrStock +
                ", customerName='" + customerName + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", mileage=" + mileage +
                //", user=" + user +
                '}';
    }
}
