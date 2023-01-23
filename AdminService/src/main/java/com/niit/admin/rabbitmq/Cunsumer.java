package com.niit.admin.rabbitmq;

import com.niit.admin.domain.AdminRestaurant;
import com.niit.admin.service.AdminServiceImplementation;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cunsumer {

    @Autowired
    private AdminServiceImplementation adminServiceImplementation;

    @RabbitListener(queues = "admin_queue")
    public void sendData(RestauantDTO restaurantDTO){
        AdminRestaurant restaurant = new AdminRestaurant();
        restaurant.setEmailId(restaurantDTO.getEmailId());
        restaurant.setPassword(restaurantDTO.getPassword());
        restaurant.setUserName(restaurantDTO.getUserName());
        restaurant.setUserRole(restaurantDTO.getUserRole());
        restaurant.setCity(restaurantDTO.getCity());
        restaurant.setAdminEmail(restaurant.getAdminEmail());
        restaurant.setApproveType(restaurantDTO.getApproveType());
        adminServiceImplementation.addRestaurant(restaurant);
    }

}
