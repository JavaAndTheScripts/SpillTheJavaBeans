package com.javaandthescripts.spillthejavabeans.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaandthescripts.spillthejavabeans.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
//	Model gets imported here	
    List<User> findAll(); // probably not going to be used unless admin 

    Optional<User> findByEmail(String email);

//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service

}