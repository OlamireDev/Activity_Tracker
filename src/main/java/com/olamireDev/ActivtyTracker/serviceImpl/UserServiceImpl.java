package com.olamireDev.ActivtyTracker.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olamireDev.ActivtyTracker.DTO.UserDTO;
import com.olamireDev.ActivtyTracker.model.User;
import com.olamireDev.ActivtyTracker.repository.UserRepository;
import com.olamireDev.ActivtyTracker.service.UserService;

@Service
public class UserServiceImpl extends UserService {

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        super(userRepository);
    }
    
    @Override
    public UserDTO getUser(String email, String password){
        User returnedUser = userRepository.findByEmailAndPassword(email, password).orElse(null);
        if(returnedUser != null){
            return new UserDTO(returnedUser.getUserId(), returnedUser.getFirstname());
        }
        return null;
    }

    @Override
    public Long validEmail(String email){
        return userRepository.countByEmail(email);
    }

    public UserDTO addUser(User user){
        userRepository.save(user);
        return getUser(user.getEmail(), user.getPassword());
    }

}
