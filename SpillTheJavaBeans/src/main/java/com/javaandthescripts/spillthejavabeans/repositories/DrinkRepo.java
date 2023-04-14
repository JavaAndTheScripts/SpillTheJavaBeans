/**
 * 
 */
package com.javaandthescripts.spillthejavabeans.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaandthescripts.spillthejavabeans.models.Drink;

@Repository
public interface DrinkRepo extends CrudRepository<Drink, Long> {
//	Model gets imported here	
//  List<<MODEL_NAME>> findAll();

//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service
}
