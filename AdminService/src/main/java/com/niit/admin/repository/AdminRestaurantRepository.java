package com.niit.admin.repository;

import com.niit.admin.domain.AdminRestaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRestaurantRepository extends MongoRepository<AdminRestaurant,String> {

}
