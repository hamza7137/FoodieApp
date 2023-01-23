package com.niit.CustomerService.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    private String emailId;
    private String restaurantName;
    private String imageUrl;
    private long phoneNo;
    private String city;
    private List<Cuisine> Cuisines;
}
