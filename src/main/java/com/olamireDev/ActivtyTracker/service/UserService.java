package com.olamireDev.ActivtyTracker.service;

import com.olamireDev.ActivtyTracker.DTO.UserDTO;
import com.olamireDev.ActivtyTracker.model.User;
import com.olamireDev.ActivtyTracker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class UserService {
    
    protected UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO getUser(String email, String pass){
        return null;
    }

    public Long validEmail(String email){
        return 0L;
    }

    public UserDTO addUser(User user){
        return null;
    }

}
