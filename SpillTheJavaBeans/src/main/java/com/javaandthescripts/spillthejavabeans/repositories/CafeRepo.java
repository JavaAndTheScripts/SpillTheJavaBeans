/**
 * 
 */
package com.javaandthescripts.spillthejavabeans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaandthescripts.spillthejavabeans.models.Cafe;

@Repository
public interface CafeRepo extends CrudRepository<Cafe, Long>{
//	Model gets imported here	
  List<Cafe> findAll();
  
  @Query(value="SELECT * FROM cafes ORDER BY id DESC LIMIT 1", nativeQuery=true)
  Cafe findCafe();

//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service
}
