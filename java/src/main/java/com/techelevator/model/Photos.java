package com.techelevator.model;

public class Photos {

    private int photoId;

    private String photoUrl;

    private int petId;

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getPhotoId() {
        return photoId;
    }

    public int photoId() {
        return photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
