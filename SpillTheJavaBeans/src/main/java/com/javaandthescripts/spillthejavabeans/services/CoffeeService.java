package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaandthescripts.spillthejavabeans.models.Coffee;
import com.javaandthescripts.spillthejavabeans.repositories.CoffeeRepo;

@Service
public class CoffeeService {
	@Autowired
    private CoffeeRepo coffeeRepo;

// ==========================
//        CRUD METHODS
// ==========================
    // create
    public Coffee createOne(Coffee c) {
        return coffeeRepo.save(c);
    }
    // read all 
    public List<Coffee> getAll() {
        return coffeeRepo.findAll();
    }

// read one
    public Coffee getOne(Long id) {
        return coffeeRepo.findById(id).orElse(null);
    }

// update
    public Coffee updateOne(Coffee c) {
        return coffeeRepo.save(c);
    }

// delete
    public void deleteOne(Long id) {
        coffeeRepo.deleteById(id);
    }

}

