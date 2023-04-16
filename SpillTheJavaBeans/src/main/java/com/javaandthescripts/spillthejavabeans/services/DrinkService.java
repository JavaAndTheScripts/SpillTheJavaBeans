package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaandthescripts.spillthejavabeans.models.Drink;
import com.javaandthescripts.spillthejavabeans.repositories.DrinkRepo;

@Service
public class DrinkService {
	
	@Autowired
    private DrinkRepo drinkRepo;

// ==========================
//        CRUD METHODS
// ==========================
    // create
    public Drink createOne(Drink i) {
        return drinkRepo.save(i);
    }
    // read all 
    public List<Drink> getAll() {
        return drinkRepo.findAll();
    }

	// read one
	public Drink getOne(Long id) {
        return drinkRepo.findById(id).orElse(null);
    }

	// update
	public Drink updateOne(Drink i) {
        return drinkRepo.save(i);
    }

	// delete
	public void deleteOne(Long id) {
        drinkRepo.deleteById(id);
    }

}

