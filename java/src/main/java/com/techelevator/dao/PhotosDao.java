package com.techelevator.dao;

import com.techelevator.model.Photos;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;

public interface PhotosDao {

    public Photos addPhoto(Photos photos);

    public Photos getPhoto(int photoId);

    public List<Photos> getPhotos(int petId);

    public Photos removePhoto(int photoId);

    public Photos updatePhoto(int photoId, Photos updatedPhoto);

}
