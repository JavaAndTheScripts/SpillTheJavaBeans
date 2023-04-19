package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaandthescripts.spillthejavabeans.models.Puzzle;
import com.javaandthescripts.spillthejavabeans.repositories.PuzzleRepo;

@Service
public class PuzzleService {
    @Autowired
    private PuzzleRepo puzzleRepo;

// ==========================
//        CRUD METHODS
// ==========================
    // create
    public Puzzle createOne(Puzzle i) {
        return puzzleRepo.save(i);
    }
    // read all 
    public List<Puzzle> getAll() {
        return puzzleRepo.findAll();
    }
    // read one
    public Puzzle getOne() { // Long id
//        return puzzleRepo.findById(id).orElse(null);
    	return puzzleRepo.findPuzzle();
    }
    // update
    public Puzzle updateOne(Puzzle i) {
        return puzzleRepo.save(i);
    }
    // delete
    public void deleteOne(Long id) {
        puzzleRepo.deleteById(id);
    }

}
