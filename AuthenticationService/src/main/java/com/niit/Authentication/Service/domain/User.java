package com.niit.Authentication.Service.domain;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    private String emailId;
    private String userName;
    private String password;
    private UserRole userRole;


    public User() {
    }

    public User(String emailId, String userName, String password, UserRole userRole) {
        this.emailId = emailId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailId='" + emailId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
