package com.techelevator.controller;


import com.techelevator.dao.PhotosDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Adoptions;
import com.techelevator.model.Photos;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/photos")

public class PhotosController {

    @Autowired
    private PhotosDao photosDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path="/add", method = RequestMethod.POST)
    public Photos addPhoto(@RequestBody Photos photos) {
        return photosDao.addPhoto(photos);
    }

    @RequestMapping(path="/{photoId}", method = RequestMethod.GET)
    public Photos getPhoto(@PathVariable int photoId) {

        return photosDao.getPhoto(photoId);
    }

    @RequestMapping(path="/pet/{petId}", method = RequestMethod.GET)
    public List<Photos> getPhotos(@PathVariable int petId){
        return photosDao.getPhotos(petId);
    }


    @RequestMapping(path="/remove/{photoId}", method = RequestMethod.DELETE)
    public void removePhoto(@PathVariable int photoId){
        photosDao.removePhoto(photoId);
    }

    @RequestMapping(path="/update/{photoId}", method = RequestMethod.PUT)
    public Photos updatePhoto(@Valid @RequestBody Photos updatedPhoto, @PathVariable int photoId){
        updatedPhoto.setPhotoId(photoId);
        try{
            return updatedPhoto = photosDao.updatePhoto(photoId, updatedPhoto);
        }catch(DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo not found");
        }
    }


}
