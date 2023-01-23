package com.niit.Authentication.Service.service;

import com.niit.Authentication.Service.domain.User;
import com.niit.Authentication.Service.exception.InvalidCredentialsException;
import com.niit.Authentication.Service.exception.UserAlreadyExistsException;
import com.niit.Authentication.Service.exception.UserNotFoundException;
import com.niit.Authentication.Service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public User signUp(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmailId()).isPresent()){   // why null check not giving error?
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);       //sending user to mysql user table
    }

    @Override
    public User logIn(User user) throws UserNotFoundException {
        User user1 = userRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
        if(user1==null){
            throw new UserNotFoundException();
        }
        return user1;
    }

    @Override
    public boolean deleteUserDetails(String emailId) {
        userRepository.deleteById(emailId);
        return true;
    }
}
