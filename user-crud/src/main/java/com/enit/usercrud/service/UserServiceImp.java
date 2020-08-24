package com.enit.usercrud.service;

import com.enit.usercrud.model.User;
import com.enit.usercrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.enit.usercrud.service.UserService;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    public String  saveUser(String username,User user){
        if(userRepository.findByUsername(username).isPresent())
            return "username: "+username+" exist !!!!";
        userRepository.save(user);
        return "username:"+username+" is sucessfully saved ";
    }

    public Optional<User> findByUsername(String username){

        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email){
        return  userRepository.findByEmail(email);
    }
    public String deleteByUsername(String username){
        if(userRepository.findByUsername(username).isPresent())
                return userRepository.deleteByUsername(username);
        else
            return "username: "+username+" does not exist !!!!";
    }
    public Boolean existsByUsername(String username){
        if(userRepository.findByUsername(username).isPresent())
            return true ;
        return  false;

    }

   public  Boolean existsByEmail(String email){
        if(userRepository.findByEmail(email).isPresent())
            return true ;
        return  false;
    }

    public String  updateUser(String username,User user){
        if(userRepository.findByUsername(username).isPresent()) {
            userRepository.save(user);
            return "user: "+username+" updated sucessfully";
        }
        return "user: "+username+" does not exist !!!!!";
    }




}
