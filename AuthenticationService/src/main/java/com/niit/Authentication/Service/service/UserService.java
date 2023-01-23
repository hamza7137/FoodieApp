package com.niit.Authentication.Service.service;

import com.niit.Authentication.Service.domain.User;
import com.niit.Authentication.Service.exception.InvalidCredentialsException;
import com.niit.Authentication.Service.exception.UserAlreadyExistsException;
import com.niit.Authentication.Service.exception.UserNotFoundException;

public interface UserService {

    User signUp(User user) throws UserAlreadyExistsException;
    User logIn(User user) throws UserNotFoundException;
    boolean deleteUserDetails(String emailId);
}
