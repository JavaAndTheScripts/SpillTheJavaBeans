package com.javaandthescripts.spillthejavabeans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaandthescripts.spillthejavabeans.models.Puzzle;

@Repository
public interface PuzzleRepo extends CrudRepository<Puzzle, Long> {
//	Model gets imported here	
    List<Puzzle> findAll();

    @Query(value="SELECT * FROM puzzles ORDER BY id DESC LIMIT 1", nativeQuery=true)
    Puzzle findPuzzle();
//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service

}
