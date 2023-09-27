package com.amdocs.plant.model;

public class Plant {
	private int plantID;
	private String plantName;
	private String originCountryName;
	private int sunlightRequired;
	private String waterSupplyFrequency;
	private String plantType;
	private double cost;
	
	public Plant() {
		super();
	}
	
	public Plant(int plantID, String plantName, String originCountryName, int sunlightRequired,
			String waterSupplyFrequency, String plantType, double cost) {
		super();
		this.plantID = plantID;
		this.plantName = plantName;
		this.originCountryName = originCountryName;
		this.sunlightRequired = sunlightRequired;
		this.waterSupplyFrequency = waterSupplyFrequency;
		this.plantType = plantType;
		this.cost = cost;
	}

	public int getPlantID() {
		return plantID;
	}

	public void setPlantID(int plantID) {
		this.plantID = plantID;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getOriginCountryName() {
		return originCountryName;
	}

	public void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}

	public int getSunlightRequired() {
		return sunlightRequired;
	}

	public void setSunlightRequired(int sunlightRequired) {
		this.sunlightRequired = sunlightRequired;
	}

	public String getWaterSupplyFrequency() {
		return waterSupplyFrequency;
	}

	public void setWaterSupplyFrequency(String waterSupplyFrequency) {
		this.waterSupplyFrequency = waterSupplyFrequency;
	}

	public String getPlantType() {
		return plantType;
	}

	public void setPlantType(String plantType) {
		this.plantType = plantType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
