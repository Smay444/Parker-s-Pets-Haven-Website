package com.techelevator.dao;

import com.techelevator.model.Adoptions;

import java.time.LocalDate;
import java.util.List;

public interface AdoptionsDao {

    public List<Adoptions> getAdoptions(int adoptionId);

    public Adoptions getAdoption(int adoptionId);

    public Adoptions createAdoption(Adoptions adoptions);

    public Adoptions updateAdoption(int adoptionId, Adoptions updatedAdoption);

    public Adoptions removeAdoption(int adoptionId);

}
