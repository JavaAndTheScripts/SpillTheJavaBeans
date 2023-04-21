package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.javaandthescripts.spillthejavabeans.models.Cafe;
import com.javaandthescripts.spillthejavabeans.models.LoginUser;
import com.javaandthescripts.spillthejavabeans.models.Manager;
import com.javaandthescripts.spillthejavabeans.models.User;
import com.javaandthescripts.spillthejavabeans.repositories.UserRepo;

@Service
public class ManagerService {
	@Autowired
    private UserRepo userRepo;

// ==========================
//       REGISTRATION
// ==========================
    public Manager register(Cafe cafe, Manager newSub, BindingResult result) {
    	// if store code does not match manager code
    	if(!newSub.getCode().equals(cafe.getCode())) {
    		System.out.println("=".repeat(20));
    		System.out.printf("\n\tVARIABLES:\t session: %s input: %s \n\n",cafe.getCode() , newSub.getCode());
    		System.out.println("=".repeat(20));
    		result.rejectValue("code", "Matches", "Cafe Code must match set value!");
    	}
    	
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
    public Manager login(LoginUser newLogin, BindingResult result) {
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        // is the email is not valid
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        
        Manager manager = (Manager)potentialUser.get();
        // if the passwords do not match
        if(!BCrypt.checkpw(newLogin.getPassword(), manager.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        
        // if there are errors
        if(result.hasErrors()) {
            return null;
        } 
        
        else {
            return manager;
        }
    }

// ==========================
//       CRUD METHODS
// ==========================
    // CREATE SUBSCRIBER
    public Manager createOne(Manager i) {
      return userRepo.save(i);
    }
    
    // READ ALL (Manager) - will most likely never be used but in here just in case
    public List<Manager> getAll() {
      return userRepo.findAllManagers();
    }

    // READ ONE (Manager)
    public Manager getOne(Long id) {
      return (Manager) userRepo.findById(id).orElse(null);
    }
    
    // UPDATE (Manager)
    public Manager updateOne(Manager i) {
      return userRepo.save(i);
    }
    
    // DELETE 
    public void deleteOne(Long id) {
      userRepo.deleteById(id);
    }
}
