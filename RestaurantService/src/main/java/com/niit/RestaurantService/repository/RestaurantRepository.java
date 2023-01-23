package com.niit.RestaurantService.repository;

import com.niit.RestaurantService.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    public Restaurant findByEmailIdAndPassword(String emailId, String password);
}
