package com.example.project;

public class Rentals {
    private String rentalId;
    private String rentalName;
    private String rentalPassword;
    private String rentalCountry;
    private String rentalCity;
    private String rentalPhone;

    public Rentals() {
    }

    public Rentals(String rentalId, String rentalName, String rentalPassword,
                   String rentalCountry, String rentalCity, String rentalPhone) {
        this.rentalId = rentalId;
        this.rentalName = rentalName;
        this.rentalPassword = rentalPassword;
        this.rentalCountry = rentalCountry;
        this.rentalCity = rentalCity;
        this.rentalPhone = rentalPhone;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    public String getRentalPassword() {
        return rentalPassword;
    }

    public void setRentalPassword(String rentalPassword) {
        this.rentalPassword = rentalPassword;
    }

    public String getRentalCountry() {
        return rentalCountry;
    }

    public void setRentalCountry(String rentalCountry) {
        this.rentalCountry = rentalCountry;
    }

    public String getRentalCity() {
        return rentalCity;
    }

    public void setRentalCity(String rentalCity) {
        this.rentalCity = rentalCity;
    }

    public String getRentalPhone() {
        return rentalPhone;
    }

    public void setRentalPhone(String rentalPhone) {
        this.rentalPhone = rentalPhone;
    }
}