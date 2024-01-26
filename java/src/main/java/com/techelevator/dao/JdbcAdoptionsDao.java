package com.techelevator.dao;

import com.techelevator.model.Adoptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAdoptionsDao implements AdoptionsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Adoptions> getAdoptions(int adoptionId) {
        List<Adoptions> adoptions = new ArrayList<>();

        String sql = "SELECT * FROM adoptions WHERE adoption_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, adoptionId);

            while(results.next()){
                Adoptions adoptions1 = mapRowToAdoptions(results);
                adoptions.add(adoptions1);
            }
        } catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
        return adoptions;
    }

    @Override
    public Adoptions getAdoption(int adoptionId) {
        Adoptions adoptions = null;

        String sql = "SELECT adoption_id, pet_id, user_id, date_adopted FROM adoptions WHERE adoption_id = ?;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, adoptionId);

            if(results.next()){
                adoptions = mapRowToAdoptions(results);
            }
        }catch (Exception ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }

        return adoptions;
    }

    @Override
    public Adoptions createAdoption(Adoptions adoptions) {
        Adoptions newAdoptions = null;

        String sql = "INSERT INTO adoptions(pet_id, user_id, date_adopted) VALUES (?, ?, ?) RETURNING adoption_id";

        try {
            int adoptionId = jdbcTemplate.queryForObject(sql, int.class, adoptions.getPetId(), adoptions.getUserId(), adoptions.getDateAdopted());

           newAdoptions = getAdoption(adoptionId);


        } catch (Exception ex){
            System.out.println("Something went awry in creation of Adoption: " + ex.getMessage());
        }

        return newAdoptions;
    }

    @Override
    public Adoptions updateAdoption(int adoptionId, Adoptions updatedAdoption) {

        String sql = "UPDATE adoptions SET pet_id = ?, user_id = ?, date_adopted = ? WHERE adoption_id = ?;";

        try{
            int numberOfRows = jdbcTemplate.update(sql, updatedAdoption.getPetId(), updatedAdoption.getUserId(), updatedAdoption.getDateAdopted(), adoptionId);

            if(numberOfRows > 0){
                return getAdoption(adoptionId);
            }else{
                System.out.println("Adoption Record not updated with ID of: "+ adoptionId);
            }

        }catch(Exception ex){
            System.out.println("Could not update Adoption record because : " + ex.getMessage());
        }
        return null;
    }

    @Override
    public Adoptions removeAdoption(int adoptionId) {
        Adoptions removedAdoptions = null;

        String sql = "DELETE FROM adoptions WHERE adoption_id = ?;";

        try{
            int numberRows = jdbcTemplate.update(sql, adoptionId);

            if(numberRows < 0) {
                removedAdoptions = new Adoptions();
                removedAdoptions.setAdoptionId(adoptionId);
            }else{
                System.out.println("Adoption not found with the ID of: " +adoptionId);
            }

        }catch(Exception ex){
            System.out.println("Something went awry removing adoption");
        }
        return removedAdoptions;
    }

    private Adoptions mapRowToAdoptions(SqlRowSet results) {
        Adoptions adoptions = new Adoptions();

        adoptions.setAdoptionId(results.getInt("adoption_id"));
        adoptions.setPetId(results.getInt("pet_id"));
        adoptions.setUserId(results.getInt("user_id"));

        if(results.getDate("date_adopted") != null) {
            adoptions.setDateAdopted(results.getDate("date_adopted").toLocalDate());
        }

        return adoptions;
    }

}
