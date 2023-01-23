package com.niit.CustomerService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {


    @Id
    private String emailId;
    private String password;
    private String userName;
    private UserRole userRole;
    private long phoneNo;
    private String city;
    private List<Cuisine> cart;
    private List<Restaurant> favouriteRestaurant;
    private List<Cuisine> favouriteCuisine;
}
