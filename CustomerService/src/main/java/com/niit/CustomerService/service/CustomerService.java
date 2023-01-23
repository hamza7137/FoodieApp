package com.niit.CustomerService.service;

import com.niit.CustomerService.domain.Cuisine;
import com.niit.CustomerService.domain.User;
import com.niit.CustomerService.domain.Restaurant;
import com.niit.CustomerService.exception.CustomerAlreadyExistException;
import com.niit.CustomerService.exception.RestaurantAlreadyExistsException;

import java.util.List;

public interface CustomerService
{
    public User registerCustomer(User user) throws CustomerAlreadyExistException;
    public User updateCustomer(User user);
    public User addFavouriteRestaurant(Restaurant restaurant, String emailId) throws RestaurantAlreadyExistsException;
    public User addFavouriteCuisine(Cuisine Cuisine, String emailId);
    public User addToCart(Cuisine cuisine, String emailId);
    public List<Restaurant> getFavouriteRestaurant(String emailId);
    public List<Cuisine> getFavouriteCuisine(String emailId);
    public List<Cuisine> getFromCart(String emailId);
    public User deleteFromFavouriteRestaurant(String restaurantName, String emailId);
    public User deleteFromFavouriteCuisine(String cuisineName, String emailId);
    public User deleteFromCart(String cuisineName , String emailId);
    public User getCustomerByEmailId(String emailId);
    public boolean deleteAllCuisines(String emailId);
}
