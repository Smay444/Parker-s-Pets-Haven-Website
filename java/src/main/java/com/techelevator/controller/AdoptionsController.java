package com.techelevator.controller;

import com.techelevator.dao.AdoptionsDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Adoptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/adoptions")
public class AdoptionsController {

    @Autowired
    private AdoptionsDao adoptionsDao;

    @RequestMapping(path="/{adoptionId}", method = RequestMethod.GET)
    public Adoptions getAdoption(@PathVariable int adoptionId){
        return adoptionsDao.getAdoption(adoptionId);
    }

    @RequestMapping(path="/pets/{adoptionId}", method = RequestMethod.GET)
    public List<Adoptions> getAdoptions(@PathVariable int adoptionId){
        return adoptionsDao.getAdoptions(adoptionId);
    }

    @RequestMapping(path="/add" , method = RequestMethod.POST)
    public Adoptions createAdoption(@RequestBody Adoptions adoptions){
        return adoptionsDao.createAdoption(adoptions);
    }

    @RequestMapping(path = "/update/{adoptionId}", method = RequestMethod.PUT)
    public Adoptions updateAdoption(@PathVariable int adoptionId, @RequestBody @Valid Adoptions adoptions){
        adoptions.setAdoptionId(adoptionId);
        try{
            return adoptionsDao.updateAdoption(adoptionId, adoptions);

        }catch(DaoException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID is not valid");
        }
    }

    @RequestMapping(path="/remove/{adoptionId}", method = RequestMethod.DELETE)
    public void removePhoto(@PathVariable int adoptionId){
        adoptionsDao.removeAdoption(adoptionId);
    }

}
