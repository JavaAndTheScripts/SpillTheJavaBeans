/**
 * 
 */
package com.javaandthescripts.spillthejavabeans.repositories;

import org.springframework.data.repository.CrudRepository;

import com.javaandthescripts.spillthejavabeans.models.Cafe;


public interface CafeRepo extends CrudRepository<Cafe, Long>{
//	Model gets imported here	
//  List<<MODEL_NAME>> findAll();

//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service
}
