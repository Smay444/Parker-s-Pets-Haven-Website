package com.techelevator.dao;
import com.techelevator.model.Pets;
import java.util.List;

public interface PetsDao {

    Pets createPet(Pets pet);
    Pets deletePet(int petId);

    Pets updateListing(int petId);

    Pets updatePets(int petId, Pets updatedPets);

    Pets getPetsById(int petId);

    public List<Pets> isAvailable(boolean isAvailable);





}
