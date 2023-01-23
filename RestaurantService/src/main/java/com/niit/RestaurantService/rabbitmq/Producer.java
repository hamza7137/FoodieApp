package com.niit.RestaurantService.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    public void sendMessageToAdmin(RestaurantDTO restaurantDTO) {
        rabbitTemplate.convertAndSend(directExchange.getName(),"admin_routing",restaurantDTO);
    }

}
