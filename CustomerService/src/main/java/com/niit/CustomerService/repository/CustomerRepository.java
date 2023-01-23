package com.niit.CustomerService.repository;

import com.niit.CustomerService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface CustomerRepository extends MongoRepository<User, String> {


   public User findByEmailIdAndPassword(String emailId, String password );

    public User findByEmailId(String emailId);
}
