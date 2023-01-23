package com.niit.Authentication.Service.rabbitmq;

import com.niit.Authentication.Service.domain.User;
import com.niit.Authentication.Service.exception.UserAlreadyExistsException;
import com.niit.Authentication.Service.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserServiceImpl userService;

    @RabbitListener(queues =  "user_queue")
    public void sendDataFromRabbitmq(UserDTO userDTO) throws UserAlreadyExistsException{
        User user = new User();
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        user.setUserRole(userDTO.getUserRole());
        user.setUserName(userDTO.getUserName());
        userService.signUp(user);
    }
}
