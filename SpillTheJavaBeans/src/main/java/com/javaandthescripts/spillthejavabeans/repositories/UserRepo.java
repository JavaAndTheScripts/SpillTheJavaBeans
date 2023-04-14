package com.javaandthescripts.spillthejavabeans.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaandthescripts.spillthejavabeans.models.Manager;
import com.javaandthescripts.spillthejavabeans.models.Subscriber;
import com.javaandthescripts.spillthejavabeans.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
//	Model gets imported here	
    List<User> findAll(); // probably not going to be used unless admin 
    
    // READ ALL (Subscribers) - used to get all subscribers in the list
    @Query(value="SELECT * FROM users WHERE dtype = ?Subscriber", nativeQuery=true)
    public List<Subscriber> findAllSubscribers();
    
    // READ ALL (Managers) - used to get all managers in the list
    @Query(value="SELECT * FROM users WHERE dtype = ?Manager", nativeQuery=true)
    public List<Manager> findAllManagers();

    Optional<User> findByEmail(String email);

//	No need to add .save here because CrudRepository already has it	
//	Repo gets "exported" to model Service

}