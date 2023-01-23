package com.niit.admin.rabbitmq;

import com.niit.admin.domain.ApproveType;
import com.niit.admin.domain.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestauantDTO {

    private String emailId;
    private String password;
    private String userName;
    private UserRole userRole;
    private String city;
    private String adminEmail;
    private ApproveType approveType;
}
