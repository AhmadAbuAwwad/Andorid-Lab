package com.example.project.ui.home;

public class House {
    private int id;
    private String city;
    private String address;
    private String surface;
    private String year;


    private String numOfbedrooms;
    private String price;
    private String status;
    private boolean hasbalcone;
    private boolean hasgarden;
    private String date;
    private String description;
    private String owner;

    public House(int id, String city, String address, String surface, String year, String numOfbedrooms, String price, String status, boolean hasbalcone, boolean hasgarden, String date, String description, String owner, String ownerName, String bitmap) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.surface = surface;
        this.year = year;
        this.numOfbedrooms = numOfbedrooms;
        this.price = price;
        this.status = status;
        this.hasbalcone = hasbalcone;
        this.hasgarden = hasgarden;
        this.date = date;
        this.description = description;
        this.owner = owner;
        this.ownerName = ownerName;
        this.bitmap = bitmap;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
        this.bitmap = bitmap;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    private String bitmap;
    private String ownerName;


    public String getOwnerName() {
        return ownerName;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }


    public String getAddress() {
        return address;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getNumOfbedrooms() {
        return numOfbedrooms;
    }

    public void setNumOfbedrooms(String numOfbedrooms) {
        this.numOfbedrooms = numOfbedrooms;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHasbalcone() {
        return hasbalcone;
    }

    public void setHasbalcone(boolean hasbalcone) {
        this.hasbalcone = hasbalcone;
    }

    public boolean isHasgarden() {
        return hasgarden;
    }

    public void setHasgarden(boolean hasgarden) {
        this.hasgarden = hasgarden;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", surface='" + surface + '\'' +
                ", numOfbedrooms='" + numOfbedrooms + '\'' +
                ", price='" + price + '\'' +
                ", status='" + status + '\'' +
                ", hasbalcone=" + hasbalcone +
                ", hasgarden=" + hasgarden +
                ", owener='" + owner + '\'' +
                '}';
    }
}

