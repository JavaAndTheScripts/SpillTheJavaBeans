package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.javaandthescripts.spillthejavabeans.models.LoginUser;
import com.javaandthescripts.spillthejavabeans.models.Subscriber;
import com.javaandthescripts.spillthejavabeans.models.User;
import com.javaandthescripts.spillthejavabeans.repositories.UserRepo;

// TODO: Verify this works with the User for login/registration 

@Service
public class SubscriberService {

    @Autowired
    private UserRepo userRepo;

// ==========================
//        REGISTRATION
// ==========================
    public Subscriber register(Subscriber newSub, BindingResult result) {
        // if email already in use
        if(userRepo.findByEmail(newSub.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }

        // if the passwords do not match
        if(!newSub.getPassword().equals(newSub.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        // where there errors in the last checks or form?
        if(result.hasErrors()) {
            return null;
        } else {
            // encrypting the password
            String hashed = BCrypt.hashpw(newSub.getPassword(), BCrypt.gensalt());
            newSub.setPassword(hashed);
            return userRepo.save(newSub);
        }
    }

// ==========================
//          LOGIN
// ==========================
    public Subscriber login(LoginUser newLogin, BindingResult result) {
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        // is the email is not valid
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        
        Subscriber subscriber = (Subscriber)potentialUser.get();
        // if the passwords do not match
        if(!BCrypt.checkpw(newLogin.getPassword(), subscriber.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        
        // if there are errors
        if(result.hasErrors()) {
            return null;
        } 
        
        else {
            return subscriber;
        }
    }

// ==========================
//  	 CRUD METHODS
// ==========================
    // CREATE SUBSCRIBER
    public Subscriber createOne(Subscriber i) {
      return userRepo.save(i);
    }
    
    // READ ALL (Subscriber) - will most likely never be used but in here just in case
    public List<Subscriber> getAll() {
      return userRepo.findAllSubscribers();
    }

    // READ ONE (Subscriber)
    public Subscriber getOne(Long id) {
      return (Subscriber) userRepo.findById(id).orElse(null);
    }
    
    // UPDATE (Subscriber)
    public Subscriber updateOne(Subscriber i) {
      return userRepo.save(i);
    }
    
    // DELETE 
    public void deleteOne(Long id) {
      userRepo.deleteById(id);
    }
}