package com.cbcoders.car_app_v1.Cars.model;

import com.cbcoders.car_app_v1.Cars.model.Enums.CarNewOrUsed;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SequenceGenerator(name = "car_seq", initialValue = 1, allocationSize = 1)
public abstract class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    private Long carId;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false, unique = true)
    private String chassisNumber;
    @Column(nullable = false)
    private Integer keysNumber;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Europe/London")
    @Temporal(TemporalType.DATE)
    private Date dateArrived;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarNewOrUsed carNewOrUsed;
    private Boolean isSold = false;

    public Car() {
    }

    public Car(String brand, String model, String color, String chassisNumber, Integer keysNumber, Date dateArrived,
               CarNewOrUsed carNewOrUsed, Boolean isSold) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.chassisNumber = chassisNumber;
        this.keysNumber = keysNumber;
        this.dateArrived = dateArrived;
        this.carNewOrUsed = carNewOrUsed;
        this.isSold = isSold;
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
        this.brand = brand.toUpperCase();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model.toUpperCase();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color.toUpperCase();
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber.toUpperCase();
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

    public CarNewOrUsed getCarNewOrUsed() {
        return carNewOrUsed;
    }

    public void setCarNewOrUsed(CarNewOrUsed carNewOrUsed) {
        this.carNewOrUsed = carNewOrUsed;
    }
    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(getCarId(), car.getCarId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId());
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", keysNumber=" + keysNumber +
                ", dateArrived=" + dateArrived +
                ", carNewOrUsed=" + carNewOrUsed +
                ", isSold=" + isSold +
                '}';
    }
}
