package com.techelevator.controller;

import com.techelevator.dao.PetsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Pets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;



@RestController
@CrossOrigin
@RequestMapping("/pets")
public class PetsController {

   // private static final String API_BASE_PATH = "/pets/" ;
    @Autowired
    private PetsDao petsDao;

    @GetMapping(path="/{petId}")
    public Pets getPet(@PathVariable int petId){
        return petsDao.getPetsById(petId);
    }
    @GetMapping("/available")
    public List<Pets> availablePets(){ return petsDao.isAvailable(true);
    }
    @GetMapping("/adopted")
    public List<Pets> adoptedPets(){ return petsDao.isAvailable(false);
    }

    @PostMapping("/addNew")
    public Pets createPetListing(@RequestBody Pets pet) {
        return petsDao.createPet(pet);
    }

    @RequestMapping(path = "/{petId}", method = RequestMethod.PUT)
    public Pets updatePet(@Valid @RequestBody Pets pets, @PathVariable int petId) {
        pets.setPetId(petId);
        try {
            Pets updatedPet = petsDao.updatePets(petId, pets);
            return updatedPet;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found.");
        }
    }

   
}
