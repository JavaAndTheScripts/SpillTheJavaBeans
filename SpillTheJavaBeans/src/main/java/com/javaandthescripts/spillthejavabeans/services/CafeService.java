package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.repositories.CafeRepo;

@Service
public class CafeService {
	@Autowired
	    private CafeRepo cafeRepo;

// ==========================
//	    CRUD METHODS
// ==========================
    // create
    public Cafe createOne(Cafe i) {
        return cafeRepo.save(i);
    }
    
    // read all 
    public Cafe getCafe() {
        return cafeRepo.findCafe();
    }

	// read one
//	public Cafe getOne(Long id) {
//	        return cafeRepo.findById(id).orElse(null);
//    }

	// update
	public Cafe updateOne(Cafe i) {
	        return cafeRepo.save(i);
	    }

	// delete
	public void deleteOne(Long id) {
	        cafeRepo.deleteById(id);
    }

}

