package com.javaandthescripts.spillthejavabeans.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.javaandthescripts.spillthejavabeans.models.LoginUser;
import com.javaandthescripts.spillthejavabeans.models.User;
import com.javaandthescripts.spillthejavabeans.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

// ==========================
//        REGISTRATION
// ==========================
    public User register(User newUser, BindingResult result) {
        // if email already in use
        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }
        // if the passwords do not match
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        // where there errors in the last checks or form?
        if(result.hasErrors()) {
            return null;
        } else {
            // encrypting the password
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }

// ==========================
//          LOGIN
// ==========================
    public User login(LoginUser newLogin, BindingResult result) {
        // is the email valid?
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Unknown email!");
            return null;
        }
        // if the passwords do not match
        User user = potentialUser.get();
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password!");
        }
        // where there errors in the last checks or form?
        if(result.hasErrors()) {
            return null;
        } else {
            return user;
        }
    }

// ==========================
//  CRUD METHODS
// ==========================
    // create
    public User createOne(User i) {
      return userRepo.save(i);
    }
    // read all 
    public List<User> getAll() {
      return userRepo.findAll();
    }
    // read one
    public User getOne(Long id) {
      return userRepo.findById(id).orElse(null);
    }
    // update
    public User updateOne(User i) {
      return userRepo.save(i);
    }
    // delete
    public void deleteOne(Long id) {
      userRepo.deleteById(id);
    }
}