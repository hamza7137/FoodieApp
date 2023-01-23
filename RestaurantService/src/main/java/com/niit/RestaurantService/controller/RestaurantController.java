package com.niit.RestaurantService.controller;


import com.niit.RestaurantService.domain.Cuisine;
import com.niit.RestaurantService.domain.Restaurant;
import com.niit.RestaurantService.exceptions.RestaurantAlreadyExists;
import com.niit.RestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v4/")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    private ResponseEntity<?> responseEntity;

    /**
     * to register the restaurant
     * http://localhost:9000/api/v4/registerrestaurant
     * @param restaurant
     * @return ResponseEntity of restaurant
     */
    @PostMapping("/registerrestaurant")
    public ResponseEntity<?> registerRestaurant(@RequestBody Restaurant restaurant)
    {

        try{
            responseEntity=new ResponseEntity<>(restaurantService.registerRestaurant(restaurant), HttpStatus.CREATED);

        }
        catch ( RestaurantAlreadyExists e)
        {
            responseEntity=new ResponseEntity<>("Not registered", HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println("exception1  "+e);
        }
        return responseEntity;
    }


    /**
     * to update Restaurant
     * http://localhost:9000/api/v4/restaurant/updaterestaurant
     * @param restaurant
     * @return ResponseEntity of restaurant
     */
    @PutMapping("/restaurant/updaterestaurant")
    public ResponseEntity<?> updateRestaurant(@RequestBody Restaurant restaurant)
    {
        responseEntity=new ResponseEntity<>(restaurantService.updateRestaurant(restaurant), HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * To get all the restaurants
     * http://localhost:9000/api/v4/getallrestaurants
     * @param
     * @return ResponseEntity of restaurant
     */
    @GetMapping("/getallrestaurants")
    public ResponseEntity<?> getAllRestaurant()
    {
        responseEntity=new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
        return responseEntity;
    }

    /**
     * to get a pirticular restaurant
     * http://localhost:9000/api/v4/restaurant/getrestaurant/{emailId}
     * @param
     * @return ResponseEntity of restaurant
     */
    @GetMapping("/restaurant/getrestaurant/{emailId}")
    public ResponseEntity<?> getARestaurant(@PathVariable String emailId)
    {
        responseEntity=new ResponseEntity<>(restaurantService.getRestaurant(emailId), HttpStatus.OK);
        return responseEntity;
    }

    /**
     *  to delete a restaurant
     *  http://localhost:9000/api/v4/restaurant/delete/{emailId}
     * @param emailId
     * @return ResponseEntity of restaurant
     */
    @DeleteMapping("/restaurant/delete/{emailId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String emailId) {
        responseEntity=new ResponseEntity<>(restaurantService.deleteRestaurant(emailId), HttpStatus.OK);
        return responseEntity;
    }

    /**
     *  to add cuisine
     *  http://localhost:9000/api/v4/restaurant/addcuisine/{emailId}
     * @param emailId
     * @return ResponseEntity of restaurant
     */
    @PostMapping("/restaurant/addcuisine/{emailId}")
    public ResponseEntity<?> addCuisine(@PathVariable String emailId , @RequestBody Cuisine cuisine) {
        responseEntity=new ResponseEntity<>(restaurantService.addCuisine(cuisine,emailId), HttpStatus.OK);
        return responseEntity;
    }

    /**
     *  to update cuisine
     *  http://localhost:9000/api/v4/restaurant/updatecuisine/{emailId}
     * @param emailId
     * @return ResponseEntity of restaurant
     */
    @PutMapping("/restaurant/updatecuisine/{emailId}")
    public ResponseEntity<?> updateCuisine(@PathVariable String emailId , @RequestBody Cuisine cuisine) {
        responseEntity=new ResponseEntity<>(restaurantService.updateCuisine(cuisine,emailId), HttpStatus.OK);
        return responseEntity;
    }

    /**
     * to delete a cuisine
     * http://localhost:9000/api/v4/restaurant/deletecuisine/{cuisineName}/{emailId}
     * @param emailId
     * @param cuisineName
     * @return ResponseEntity of restaurant
     */
    @DeleteMapping("/restaurant/deletecuisine/{cuisineName}/{emailId}")
    public ResponseEntity<?> deleteCuisine(@PathVariable String cuisineName,@PathVariable String emailId) {
        responseEntity=new ResponseEntity<>(restaurantService.deleteCuisine(cuisineName,emailId), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/getcuisines/{emailId}")
    public ResponseEntity<?> getAllCuisines(@PathVariable String emailId) {
        return new ResponseEntity<>(restaurantService.getAllCuisines(emailId),HttpStatus.OK);
    }

    @GetMapping("/getNotApprovedRestaurants")
    public ResponseEntity<?> getNotApprovedRestaurants() {
        return new ResponseEntity<>(restaurantService.getNotApprovedRestaurants(), HttpStatus.OK);
    }

    @PutMapping("/afterApprovedRestaurant/{emailId}")
    public ResponseEntity<?> afterApprovedRestaurant(@PathVariable String emailId) {
        return new ResponseEntity<>(restaurantService.afterApprovedRestaurant(emailId), HttpStatus.OK);
    }

    @GetMapping("/getApprovedRestaurants")
    public ResponseEntity<?> getApprovedRestaurants() {
        return new ResponseEntity<>(restaurantService.getApprovedRestaurants(), HttpStatus.OK);
    }
}
