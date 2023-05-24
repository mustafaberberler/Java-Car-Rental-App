/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author mustafa
 */
public class Vehicle extends User {
    
    private String advertNumber;
    private String advertDate;
    private String brand;
    private String series;
    private String model;
    private String year;
    private String fuel;
    private String transmission;
    private String km;
    private String chassis;
    private String color;
    private double fuelConsumption;
    private int fuelTank;
    private double rentCost;
    private int rentalPeriod;
    private double totalCost;
    private int range;    
    
    public Vehicle(String uniqueID, String identityNumber, String name, String surname, String gender, String authority, String username, String password, String newPassword, String date, String email, String advertNumber
    , String advertDate, String brand, String series, String model, String year, String fuel, String transmission, String km, String chassis, String color, double fuelConsumption, int fuelTank, double rentCost, int rentalPeriod, double totalCost, int range) {
        
        super(uniqueID, identityNumber, name, surname, gender, authority, username, password, newPassword, date, email);
        this.advertNumber = advertNumber;
        this.advertDate = advertDate;
        this.brand = brand;
        this.series = series;
        this.model = model;
        this.year = year;
        this.fuel = fuel;
        this.transmission = transmission;
        this.km = km;
        this.chassis = chassis;
        this.color = color;
        this.fuelConsumption = fuelConsumption;
        this.fuelTank = fuelTank;
        this.rentCost = rentCost;
        this.rentalPeriod = rentalPeriod;
        this.totalCost = totalCost;
        this.range = range;
        
    }

    /**
     * @return the advertNumber
     */
    public String getAdvertNumber() {
        return advertNumber;
    }

    /**
     * @param advertNumber the advertNumber to set
     */
    public void setAdvertNumber(String advertNumber) {
        this.advertNumber = advertNumber;
    }

    /**
     * @return the advertDate
     */
    public String getAdvertDate() {
        return advertDate;
    }

    /**
     * @param advertDate the advertDate to set
     */
    public void setAdvertDate(String advertDate) {
        this.advertDate = advertDate;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the series
     */
    public String getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(String series) {
        this.series = series;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the fuel
     */
    public String getFuel() {
        return fuel;
    }

    /**
     * @param fuel the fuel to set
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     * @return the transmission
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @param transmission the transmission to set
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * @return the km
     */
    public String getKm() {
        return km;
    }

    /**
     * @param km the km to set
     */
    public void setKm(String km) {
        this.km = km;
    }

    /**
     * @return the chassis
     */
    public String getChassis() {
        return chassis;
    }

    /**
     * @param chassis the chassis to set
     */
    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the fuelConsumption
     */
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     * @param fuelConsumption the fuelConsumption to set
     */
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * @return the fuelTank
     */
    public int getFuelTank() {
        return fuelTank;
    }

    /**
     * @param fuelTank the fuelTank to set
     */
    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    /**
     * @return the rentCost
     */
    public double getRentCost() {
        return rentCost;
    }

    /**
     * @param rentCost the rentCost to set
     */
    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    /**
     * @return the rentalPeriod
     */
    public int getRentalPeriod() {
        return rentalPeriod;
    }

    /**
     * @param rentalPeriod the rentalPeriod to set
     */
    public void setRentalPeriod(int rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    /**
     * @return the totalCost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost the totalCost to set
     */
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    /**
     * @return the range
     */
    public int getRange() {
        return range;
    }

    /**
     * @param range the range to set
     */
    public void setRange(int range) {
        this.range = range;
    }
}
