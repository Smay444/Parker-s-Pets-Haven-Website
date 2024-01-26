package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Pets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.ArrayList;

@Component
public class JdbcPetsDao implements PetsDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Pets createPet(Pets pet) {

        Pets newPet = null;

        String sql = "INSERT INTO pets (pet_name, pet_breed, pet_color, pet_age, is_available,\n" +
                "\t pet_description, pet_weight, pet_zip, pet_city, pet_state, adopted_by)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,\n" +
                "\t?,?) RETURNING pet_id";

        try{
            int petId = jdbcTemplate.queryForObject(sql, int.class,
                    pet.getPetName(), pet.getPetBreed(), pet.getPetColor(), pet.getPetAge(),
                    pet.isAvailable(), pet.getPetDescription(), pet.getPetWeight(), pet.getZipCode(),
                    pet.getPetCity(), pet.getPetState(), pet.getAdoptedBy());

            newPet = new  Pets(petId, pet.getPetName(), pet.getPetBreed(), pet.getPetColor(), pet.getPetAge(),
                    pet.isAvailable(), pet.getPetDescription(), pet.getPetWeight(), pet.getZipCode(),
                    pet.getPetCity(), pet.getPetState(), pet.getAdoptedBy());

        }catch (EmptyResultDataAccessException ex) {
            System.out.println("No data found. Please check the input parameters.");
        }
        return newPet;
    }

    @Override
    public Pets deletePet(int petId) {

        Pets deletedPets = null;

        String sql = "DELETE FROM pets\n" +
                "WHERE pet_id=?";

        try{
            int rowsAffected = jdbcTemplate.update(sql, petId);

            if (rowsAffected < 0){
                deletedPets = new Pets();
                deletedPets.setPetId(petId);
            } else {
                System.out.println("Pet with ID" + petId + "not found!");
            }

        }catch (Exception ex){
            System.out.println("Something went wrong with trying to delete the listing.");
        }

        return deletedPets;
    }

    @Override
    public Pets updateListing(int petId) {
        boolean updatedAvailability = false;

        String updateQuery = "UPDATE pets\n" +
                "SET is_available = ?\n" +
                "WHERE pet_id = ?";

        try{
            int rowsAffected = jdbcTemplate.update(updateQuery, updatedAvailability, petId);

            if (rowsAffected > 0) {
                return getPetsById(petId);
            } else {
                System.out.println("Pet with ID: " + petId + " not updated.");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong updating pet listing.");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Pets updatePets(int petId, Pets updatedPets) {

        String sql = "UPDATE pets\n" +
                "SET \n" +
                "  pet_name = ?,\n" +
                "  pet_breed = ?,\n" +
                "  pet_color = ?,\n" +
                "  pet_age = ?,\n" +
                "  is_available = ?,\n" +
                "  pet_description = ?,\n" +
                "  pet_weight = ?,\n" +
                "  pet_zip = ?,\n" +
                "  pet_city = ?,\n" +
                "  pet_state = ?,\n" +
                "  adopted_by = ? \n" +
                "WHERE pet_id = ?";

        try{
            int rowsAffected = jdbcTemplate.update(sql,
                    updatedPets.getPetName(),
                    updatedPets.getPetBreed(),
                    updatedPets.getPetColor(),
                    updatedPets.getPetAge(),
                    updatedPets.isAvailable(),
                    updatedPets.getPetDescription(),
                    updatedPets.getPetWeight(),
                    updatedPets.getZipCode(),
                    updatedPets.getPetCity(),
                    updatedPets.getPetState(),
                    updatedPets.getAdoptedBy(),
                    petId);

            if(rowsAffected > 0){
                return getPetsById(petId);
            }else {
                System.out.println("Pet with ID: " + petId + " not updated.");
            }
        }catch(Exception ex){
            System.out.println("Something went wrong updating Pet record");
        }


        return null;
    }

    @Override
    public Pets getPetsById(int petId) {
        Pets pets = null;

        String sql = "SELECT * FROM pets WHERE pet_id = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, petId);
            if(results.next()){
                pets = mapRowToUser(results);
            }
        }catch (CannotGetJdbcConnectionException ex){
            throw new DaoException("Something went wrong getting pets by their ID", ex);
        }

        return pets;
    }

    @Override
    public List<Pets> isAvailable(boolean isAvailable) {
        List<Pets> available = new ArrayList<>();

        String sql = "SELECT * FROM pets WHERE is_available = ?";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, isAvailable);
            while(results.next()){
                available.add (mapRowToUser(results));
            }
        }catch (Exception ex){
            System.out.println("Something went wrong getting available pets");
        }


        return available;
    }


    private Pets mapRowToUser (SqlRowSet rs){
        Pets pets = new Pets();
        pets.setPetId(rs.getInt("pet_id"));
        pets.setPetName(rs.getString("pet_name"));
        pets.setPetBreed(rs.getString("pet_breed"));
        pets.setPetColor(rs.getString("pet_color"));
        pets.setPetAge(rs.getInt("pet_age"));
        pets.setAvailable(rs.getBoolean("is_available"));
        pets.setPetDescription(rs.getString("pet_description"));
        pets.setPetWeight(rs.getInt("pet_weight"));
        pets.setZipCode(rs.getInt("pet_zip"));
        pets.setPetCity(rs.getString("pet_city"));
        pets.setPetState(rs.getString("pet_state"));
        pets.setAdoptedBy(rs.getString("adopted_by"));
        return pets;
    }

}
