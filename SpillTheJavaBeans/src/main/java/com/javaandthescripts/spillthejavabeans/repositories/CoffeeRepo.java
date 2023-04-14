/**
 * 
 */
package com.javaandthescripts.spillthejavabeans.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaandthescripts.spillthejavabeans.models.Coffee;

@Repository
public interface CoffeeRepo extends CrudRepository <Coffee, Long> {
//	Model gets imported here	
    List<Coffee> findAll();

//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service
}
