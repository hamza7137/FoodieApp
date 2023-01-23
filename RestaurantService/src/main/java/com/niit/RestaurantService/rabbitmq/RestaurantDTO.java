package com.niit.RestaurantService.rabbitmq;

import com.niit.RestaurantService.domain.ApproveType;
import com.niit.RestaurantService.domain.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDTO {

    private String emailId;
    private String password;
    private String userName;
    private UserRole userRole;
    private String city;
    private String adminEmail;
    private ApproveType approveType;

}
