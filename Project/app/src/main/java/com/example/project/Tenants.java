package com.example.project;

import java.util.ArrayList;
public class Tenants {
    public static ArrayList<Tenants> customersArrayList=new ArrayList<Tenants>();

    private String tenantId ;
    private String tenantFirstName;
    private String tenantLastName;
    private String tenantGender;
    private String tenantPassword;
    private String tenantNationality;
    private String tenantOccupation;
    private String tenantSize;
    private String tenantCountry;
    private String tenantCity;
    private String tenantPhone;

    public Tenants() {
    }

    public Tenants(String tenantId, String tenantFirstName, String tenantLastName, String tenantGender,
                   String tenantPassword, String tenantNationality, String tenantOccupation,
                   String tenantSize, String tenantCountry, String tenantCity, String tenantPhone) {
        this.tenantId = tenantId;
        this.tenantFirstName = tenantFirstName;
        this.tenantLastName = tenantLastName;
        this.tenantGender = tenantGender;
        this.tenantPassword = tenantPassword;
        this.tenantNationality = tenantNationality;
        this.tenantOccupation = tenantOccupation;
        this.tenantSize = tenantSize;
        this.tenantCountry = tenantCountry;
        this.tenantCity = tenantCity;
        this.tenantPhone = tenantPhone;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantFirstName() {
        return tenantFirstName;
    }

    public void setTenantFirstName(String tenantFirstName) {
        this.tenantFirstName = tenantFirstName;
    }

    public String getTenantLastName() {
        return tenantLastName;
    }

    public void setTenantLastName(String tenantLastName) {
        this.tenantLastName = tenantLastName;
    }

    public String getTenantGender() {
        return tenantGender;
    }

    public void setTenantGender(String tenantGender) {
        this.tenantGender = tenantGender;
    }

    public String getTenantPassword() {
        return tenantPassword;
    }

    public void setTenantPassword(String tenantPassword) {
        this.tenantPassword = tenantPassword;
    }

    public String getTenantNationality() {
        return tenantNationality;
    }

    public void setTenantNationality(String tenantNationality) {
        this.tenantNationality = tenantNationality;
    }

    public String getTenantOccupation() {
        return tenantOccupation;
    }

    public void setTenantOccupation(String tenantOccupation) {
        this.tenantOccupation = tenantOccupation;
    }

    public String getTenantSize() {
        return tenantSize;
    }

    public void setTenantSize(String tenantSize) {
        this.tenantSize = tenantSize;
    }

    public String getTenantCountry() {
        return tenantCountry;
    }

    public void setTenantCountry(String tenantCountry) {
        this.tenantCountry = tenantCountry;
    }

    public String getTenantCity() {
        return tenantCity;
    }

    public void setTenantCity(String tenantCity) {
        this.tenantCity = tenantCity;
    }

    public String getTenantPhone() {
        return tenantPhone;
    }

    public void setTenantPhone(String tenantPhone) {
        this.tenantPhone = tenantPhone;
    }
}
