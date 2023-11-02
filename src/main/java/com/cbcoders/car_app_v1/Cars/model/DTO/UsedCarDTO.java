package com.cbcoders.car_app_v1.Cars.model.DTO;

import com.cbcoders.car_app_v1.Cars.model.Enums.CarNewOrUsed;
import com.cbcoders.car_app_v1.Users.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.cbcoders.car_app_v1.Cars.model.UsedCar}
 */
public class UsedCarDTO {
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
	private CarNewOrUsed carNewOrUsed;
	private Boolean isSold;
	private Integer mileage;
	private String regNumber;
	private User user;

	public UsedCarDTO() {
	}

	public UsedCarDTO(Long carId, String brand, String model, String color, String chassisNumber, Integer keysNumber,
	                  Date dateArrived, CarNewOrUsed carNewOrUsed, Boolean isSold, Integer mileage, String regNumber) {
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.chassisNumber = chassisNumber;
		this.keysNumber = keysNumber;
		this.dateArrived = dateArrived;
		this.carNewOrUsed = carNewOrUsed;
		this.isSold = isSold;
		this.mileage = mileage;
		this.regNumber = regNumber;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UsedCarDTO that)) return false;
		return Objects.equals(getCarId(), that.getCarId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCarId());
	}

	@Override
	public String toString() {
		return "UsedCarDTO{" +
				"carId=" + carId +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", color='" + color + '\'' +
				", chassisNumber='" + chassisNumber + '\'' +
				", keysNumber=" + keysNumber +
				", dateArrived=" + dateArrived +
				", carNewOrUsed=" + carNewOrUsed +
				", isSold=" + isSold +
				", mileage=" + mileage +
				", regNumber='" + regNumber + '\'' +
				", user=" + user +
				'}';
	}
}