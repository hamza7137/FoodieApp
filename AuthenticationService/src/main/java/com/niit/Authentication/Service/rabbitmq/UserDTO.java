package com.niit.Authentication.Service.rabbitmq;

import com.niit.Authentication.Service.domain.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String emailId;
    private String password;
    private String userName;
    private UserRole userRole;
}
