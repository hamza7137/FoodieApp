package com.niit.RestaurantService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class Restaurant {

    @Id
    private String emailId;
    private String password;
    private UserRole userRole;
    private String restaurantName;
    private String imageUrl;
    private long phoneNo;
    private String city;
    private List<Cuisine> Cuisines;
    private ApproveType approveType;

}
