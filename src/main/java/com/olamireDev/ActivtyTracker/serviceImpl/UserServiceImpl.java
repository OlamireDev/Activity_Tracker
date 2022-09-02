package com.olamireDev.ActivtyTracker.serviceImpl;

import com.olamireDev.ActivtyTracker.exceptions.EmailInUseException;
import com.olamireDev.ActivtyTracker.exceptions.UserNotFoundException;
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
    public UserDTO getUser(String email, String password, String where){
        User returnedUser = userRepository.findByEmailAndPassword(email, password).orElseThrow(() ->
                new UserNotFoundException("user with provided cridentials not found", where));
        return new UserDTO(returnedUser.getUserId(), returnedUser.getFirstname());
    }

    @Override
    public void validEmail(String email){
        if(userRepository.countByEmail(email) > 0){
            throw new EmailInUseException();
        }
    }

    public UserDTO addUser(User user, String where){
        userRepository.save(user);
        return getUser(user.getEmail(), user.getPassword(), where);
    }

}
