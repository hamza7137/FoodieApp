package com.niit.admin.domain;

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
public class Admin
{
    @Id
    private String emailId;
    private String password;
    private String userName;
    private UserRole userRole;
    public <E> Admin(String emailId, List<E> restaurant) {
    }
}
