package com.niit.RestaurantService.service;

import com.niit.RestaurantService.domain.Cuisine;
import com.niit.RestaurantService.domain.Restaurant;
import com.niit.RestaurantService.exceptions.RestaurantAlreadyExists;

import java.util.List;

public interface RestaurantService {


    public Restaurant registerRestaurant(Restaurant restaurant) throws RestaurantAlreadyExists;

    public Restaurant  updateRestaurant(Restaurant restaurant);

    public Restaurant getRestaurant(String emailId);

    public List<Restaurant> getAllRestaurants();

    public boolean deleteRestaurant(String emailId);

    public Restaurant addCuisine(Cuisine cuisine ,String emailId);

    public Restaurant updateCuisine (Cuisine cuisine ,String emailId);

    public Restaurant deleteCuisine(String cuisineName, String emailId);

    public Restaurant getAllCuisines(String emailId);

    public List<Restaurant> getNotApprovedRestaurants();

    public Restaurant afterApprovedRestaurant(String emailId);

    public List<Restaurant> getApprovedRestaurants();


}
