package com.niit.admin.rabbitmq;

import com.niit.admin.domain.UserRole;
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
