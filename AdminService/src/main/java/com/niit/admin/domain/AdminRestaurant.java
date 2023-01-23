package com.niit.admin.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
public class AdminRestaurant {

    @Id
    private String emailId;
    private String password;
    private String userName;
    private UserRole userRole;
    private String city;
    private String adminEmail;
    private ApproveType approveType;
}
