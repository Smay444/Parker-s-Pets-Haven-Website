package com.techelevator.model;

public class Pets {

    private int petId;
    private String petName;
    private String petBreed;
    private String petColor;
    private int petAge;
    private boolean available;
    private String petDescription;
    private int petWeight;
    private int zipCode;
    private String petCity;
    private String petState;
    private String adoptedBy;

    public Pets(int petId, String petName, String petBreed, String petColor, int petAge, boolean isAvailable, String petDescription, int petWeight, int zipCode, String petCity, String petState, String adoptedBy) {

        this.petName = petName;
        this.petBreed = petBreed;
        this.petColor = petColor;
        this.petAge = petAge;
        this.available = isAvailable;
        this.petDescription = petDescription;
        this.petWeight = petWeight;
        this.zipCode = zipCode;
        this.petCity = petCity;
        this.petState = petState;
        this.adoptedBy = adoptedBy;
    }

    public String getAdoptedBy() {
        return adoptedBy;
    }

    public void setAdoptedBy(String adoptedBy) {
        this.adoptedBy = adoptedBy;
    }

    public Pets() {
        
    }

    public Pets(String petName, String petBreed, String petColor, int petAge, boolean available, String petDescription, int petWeight, int zipCode, String petCity, String petState) {
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPetDescription() {
        return petDescription;
    }

    public void setPetDescription(String petDescription) {
        this.petDescription = petDescription;
    }

    public int getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(int petWeight) {
        this.petWeight = petWeight;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getPetCity() {
        return petCity;
    }

    public void setPetCity(String petCity) {
        this.petCity = petCity;
    }

    public String getPetState() {
        return petState;
    }

    public void setPetState(String petState) {
        this.petState = petState;
    }

    public void setIsAvailable(boolean isAvailable) {
    }
}
