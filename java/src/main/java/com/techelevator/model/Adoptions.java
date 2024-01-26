package com.techelevator.model;

import java.time.LocalDate;

public class Adoptions {

    private int adoptionId;

    private int petId;

    private int userId;

    private LocalDate dateAdopted;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(int adoptionId) {
        this.adoptionId = adoptionId;
    }

    public int getPetId() {
        return petId;
    }

    public LocalDate getDateAdopted() {
        return dateAdopted;
    }

    public void setDateAdopted(LocalDate dateAdopted) {
        this.dateAdopted = dateAdopted;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }


}
