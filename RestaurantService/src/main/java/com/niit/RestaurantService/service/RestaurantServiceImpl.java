package com.niit.RestaurantService.service;

import com.niit.RestaurantService.domain.ApproveType;
import com.niit.RestaurantService.domain.Cuisine;
import com.niit.RestaurantService.domain.Restaurant;
import com.niit.RestaurantService.domain.UserRole;
import com.niit.RestaurantService.exceptions.RestaurantAlreadyExists;
import com.niit.RestaurantService.rabbitmq.Producer;
import com.niit.RestaurantService.rabbitmq.RestaurantDTO;
import com.niit.RestaurantService.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private Producer producer;

    /**
     *
     * @param restaurant
     * @return Restaurant
     */
    @Override
    public Restaurant registerRestaurant(Restaurant restaurant) throws RestaurantAlreadyExists {
        if(restaurantRepository.findById(restaurant.getEmailId()).isPresent()){
            throw new RestaurantAlreadyExists();
        }
        restaurant.setUserRole(UserRole.RESTAURANT);
        restaurant.setApproveType(ApproveType.NOT_APPROVED);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setEmailId(restaurant.getEmailId());
        restaurantDTO.setPassword(restaurant.getPassword());
        restaurantDTO.setUserName(restaurant.getRestaurantName());
        restaurantDTO.setUserRole(restaurant.getUserRole());
        restaurantDTO.setCity(restaurant.getCity());
        restaurantDTO.setApproveType(ApproveType.NOT_APPROVED);
        restaurantDTO.setAdminEmail("admin@gmail.com");
        producer.sendMessageToAdmin(restaurantDTO);
        return restaurantRepository.save(restaurant);
    }


    /**
     *
     * @param restaurant
     * @return Restaurant
     */
    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Restaurant restaurant1=restaurantRepository.findByEmailIdAndPassword(restaurant.getEmailId() , restaurant.getPassword());
        restaurant1.setRestaurantName(restaurant.getRestaurantName());
        restaurant1.setPhoneNo(restaurant.getPhoneNo());
        return restaurantRepository.save(restaurant1);
    }

    @Override
    public Restaurant getRestaurant(String emailId) {
        return  restaurantRepository.findById(emailId).get();
    }

    /**
     *
     * @return List<Restaurant>
     */
    @Override
    public List<Restaurant> getAllRestaurants(){
        return  restaurantRepository.findAll();
    }


    /**
     *
     * @param emailId
     * @return boolean
     */
    @Override
    public boolean deleteRestaurant(String emailId) {
        restaurantRepository.deleteById(emailId);
        return true;
    }


    /**
     *
     * @param cuisine
     * @param emailId
     * @return Restaurant
     */
    @Override
    public Restaurant addCuisine(Cuisine cuisine ,String emailId) {
        Restaurant restaurant = restaurantRepository.findById(emailId).get();
        if(restaurant.getCuisines() == null)
        {
            restaurant.setCuisines(Arrays.asList(cuisine));
        }
        else{
            restaurant.getCuisines().add(cuisine);
        }
        return restaurantRepository.save(restaurant);

    }

    /**
     *
     * @param cuisine
     * @param emailId
     * @return Restaurant
     */

    @Override
    public Restaurant updateCuisine(Cuisine cuisine, String emailId) {
        Restaurant restaurant = restaurantRepository.findById(emailId).get();
        restaurant.getCuisines().removeIf(cus -> cus.getCuisineName().equals(cuisine.getCuisineName()));
        if(restaurant.getCuisines() == null)
        {
            restaurant.setCuisines(Arrays.asList(cuisine));
        }
        else{
            restaurant.getCuisines().add(cuisine);
        }
        return restaurantRepository.save(restaurant);

    }

    /**
     *
     * @param cuisineName
     * @param emailId
     * @return Restaurant
     */

    @Override
    public Restaurant deleteCuisine(String cuisineName ,String emailId) {
        Restaurant restaurant = restaurantRepository.findById(emailId).get();
        restaurant.getCuisines().removeIf(tv -> tv.getCuisineName().equals(cuisineName));
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getAllCuisines(String emailId) {
        return restaurantRepository.findById(emailId).get();
    }

    @Override
    public List<Restaurant> getNotApprovedRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        restaurants.removeIf(restaurant -> restaurant.getApproveType().equals(ApproveType.APPROVED));
        return restaurants;
    }

    @Override
    public Restaurant afterApprovedRestaurant(String emailId) {
        Restaurant restaurant = restaurantRepository.findById(emailId).get();
        restaurant.setApproveType(ApproveType.APPROVED);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getApprovedRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        restaurants.removeIf(restaurant -> restaurant.getApproveType().equals(ApproveType.NOT_APPROVED));
        return restaurants;
    }

}
