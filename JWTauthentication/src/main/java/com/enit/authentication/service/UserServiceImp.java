package com.enit.authentication.service;


import com.enit.authentication.model.User;
import com.enit.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp {

    @Autowired
    UserRepository userRepository;

    String  saveUser(String username, User user){
        if(userRepository.findByUsername(username).isPresent())
            return "username: "+username+" exist !!!!";
        userRepository.save(user);
        return "username:"+username+" is sucessfully saved ";
    }

    Optional<User> findByUsername(String username){

        return userRepository.findByUsername(username);
    }

    Optional<User> findByEmail(String email){
        return  userRepository.findByEmail(email);
    }
    String deleteByUsername(String username){
        if(userRepository.findByUsername(username).isPresent())
                return userRepository.deleteByUsername(username);
        else
            return "username: "+username+" does not exist !!!!";
    }
    Boolean existsByUsername(String username){
        if(userRepository.findByUsername(username).isPresent())
            return true ;
        return  false;

    }

    Boolean existsByEmail(String email){
        if(userRepository.findByEmail(email).isPresent())
            return true ;
        return  false;
    }

    String  updateUser(String username,User user){
        if(userRepository.findByUsername(username).isPresent()) {
            userRepository.save(user);
            return "user: "+username+" updated sucessfully";
        }
        return "user: "+username+" does not exist !!!!!";
    }




}
