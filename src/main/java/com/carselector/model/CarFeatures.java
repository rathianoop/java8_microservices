package com.carselector.model;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;

public class CarFeatures {

	private String make;
	private String model;
	private String color;
	private Integer year;

	@Digits(integer = 5, fraction = 2)
	private BigDecimal price;
	@Digits(integer = 3, fraction = 2)
	private BigDecimal tccRating;
	@Digits(integer = 2, fraction = 2)
	private BigDecimal hwyMPG;
	@Digits(integer = 2, fraction = 2)
	private BigDecimal fuelComsumption;

	private CarFeatures(Builder builder) {
		this.make = builder.make;
		this.model = builder.model;
		this.color = builder.color;
		this.year = builder.year;
		this.price = builder.price;
		this.tccRating = builder.tccRating;
		this.hwyMPG = builder.hwyMPG;
		this.fuelComsumption = builder.fuelComsumption;
	}

	public static class Builder {

		private String make;
		private String model;
		private String color;
		private Integer year;

		@Digits(integer = 5, fraction = 2)
		private BigDecimal price;
		@Digits(integer = 3, fraction = 2)
		private BigDecimal tccRating;
		@Digits(integer = 2, fraction = 2)
		private BigDecimal hwyMPG;
		@Digits(integer = 2, fraction = 2)
		private BigDecimal fuelComsumption;

		public Builder make(String make) {
			this.make = make;
			return this;
		}

		public Builder model(String model) {
			this.model = model;
			return this;
		}

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		public Builder year(Integer year) {
			this.year = year;
			return this;
		}

		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}

		public Builder tccRating(BigDecimal tccRating) {
			this.tccRating = tccRating;
			return this;
		}

		public Builder hwyMPG(BigDecimal hwyMPG) {
			this.hwyMPG = hwyMPG;
			return this;
		}

		public Builder fuelComsumption(BigDecimal fuelComsumption) {
			this.fuelComsumption = fuelComsumption;
			return this;
		}

		public Builder(CarFeatures carFeatures) {
			this.make = carFeatures.make;
			this.model = carFeatures.model;
			this.color = carFeatures.color;
			this.year = carFeatures.year;
			this.price = carFeatures.price;
			this.tccRating = carFeatures.tccRating;
			this.hwyMPG = carFeatures.hwyMPG;
			this.fuelComsumption = carFeatures.fuelComsumption;
		}

		public Builder() {

		}

		public CarFeatures build() {
			return new CarFeatures(this);
		}
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public Integer getYear() {
		return year;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTccRating() {
		return tccRating;
	}

	public BigDecimal getHwyMPG() {
		return hwyMPG;
	}

	public BigDecimal getFuelComsumption() {
		return fuelComsumption;
	}
}
