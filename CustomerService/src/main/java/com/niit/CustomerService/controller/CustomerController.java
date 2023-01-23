package com.niit.CustomerService.controller;

import com.niit.CustomerService.domain.Cuisine;
import com.niit.CustomerService.domain.Restaurant;
import com.niit.CustomerService.domain.User;
import com.niit.CustomerService.exception.CustomerAlreadyExistException;
import com.niit.CustomerService.exception.RestaurantAlreadyExistsException;
import com.niit.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    private ResponseEntity<?> responseEntity;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
        //this.responseEntity = responseEntity;
    }

    /**
     *  http://localhost:9000/api/v3/registercuatomer
     * @param user
     * @return ResponseEntity of Customer
     */
    @PostMapping("/registercustomer")
    public ResponseEntity<?> registerCustomer(@RequestBody User user)
    {
        try{
            responseEntity=new ResponseEntity<>(customerService.registerCustomer(user), HttpStatus.CREATED);
            System.out.println("*****Customer Controller***** ");

        }
        catch (CustomerAlreadyExistException e)
        {
            responseEntity = new ResponseEntity<>("Not registered", HttpStatus.INTERNAL_SERVER_ERROR);
            System.out.println("exception1  "+e);
        }
        return responseEntity;
    }

    /**
     * http://localhost:9000/api/v3/updatecustomer
     * @param user
     * @return ResponseEntity of Customer
     */
    @PutMapping("/customer/updatecustomer")
    public ResponseEntity<?> updateCustomer(@RequestBody User user)
    {
        responseEntity=new ResponseEntity<>(customerService.updateCustomer(user), HttpStatus.CREATED);
        return responseEntity;
    }

    /**
     * http://localhost:9000/api/v3/addfavrestuarant/{emailId}
     * @param emailId
     * @return ResponseEntity
     */
    @PostMapping("/customer/addfavrestuarant/{emailId}")
    public ResponseEntity<?>  addFavouriteRestaurant(@RequestBody Restaurant restaurant , @PathVariable String emailId) throws RestaurantAlreadyExistsException {
        try {
            return new ResponseEntity<>(customerService.addFavouriteRestaurant(restaurant,emailId),HttpStatus.ACCEPTED);
        } catch (RestaurantAlreadyExistsException e) {
            throw new RestaurantAlreadyExistsException();
        }
    }

    /**
     *  http://localhost:9000/api/v3/addfavcuisine/{emailId}
     * @param emailId
     * @return ResponseEntity
     */
    @PostMapping("/customer/addfavcuisine/{emailId}")
    public ResponseEntity<?>  addFavouriteCuisine(@RequestBody Cuisine cuisine , @PathVariable String emailId)
    {
        return new ResponseEntity<>(customerService.addFavouriteCuisine(cuisine,emailId),HttpStatus.ACCEPTED);
    }

    /**
     *  http://localhost:9000/api/v3/customer/addtocart/{emailId}
     * @param emailId
     * @return ResponseEntity
     */
    @PostMapping("/customer/addtocart/{emailId}")
    public ResponseEntity<?>  addToCart(@RequestBody Cuisine cuisine , @PathVariable String emailId)
    {
        return  new ResponseEntity<>(customerService.addToCart(cuisine,emailId),HttpStatus.ACCEPTED);
    }

    /**
     * http://localhost:9000/api/v3/getfavrestaurant/{emailId}
     * @param emailId
     * @return ResponseEntity
     */
    @GetMapping("/customer/getfavrestaurant/{emailId}")
    public ResponseEntity<?> getFavouriteRestaurant(@PathVariable String emailId) {
        return new ResponseEntity<>(customerService.getFavouriteRestaurant(emailId), HttpStatus.OK);
    }

    /**
     * http://localhost:9000/api/v3/getfavcuisine/{emailId}
     * @param emailId
     * @return ResponseEntity
     */
    @GetMapping("/customer/getfavcuisine/{emailId}")
    public ResponseEntity<?> getFavouriteCuisine(@PathVariable String emailId) {
        return new ResponseEntity<>(customerService.getFavouriteCuisine(emailId), HttpStatus.OK);
    }

    /**
     * http://localhost:9000/api/v3/getfromcart/{emailId}
     * @param emailId
     * @return ResponseEntity
     */
    @GetMapping("/customer/getfromcart/{emailId}")
    public ResponseEntity<?> getFromCart(@PathVariable String emailId) {
        return new ResponseEntity<>(customerService.getFromCart(emailId), HttpStatus.OK);
    }

    /**
     * http://localhost:9000/api/v3/deletefavrestaurant/{emailId}/{restaurantName}
     * @param emailId
     * @param restaurantName
     * @return ResponseEntity
     */

    @DeleteMapping("/customer/deletefavrestaurant/{emailId}/{restaurantName}")
    public ResponseEntity<?> deleteFavouriteRestaurant(@PathVariable String restaurantName, @PathVariable String emailId) {
        return new ResponseEntity<>(customerService.deleteFromFavouriteRestaurant(restaurantName,emailId), HttpStatus.OK);
    }

    /**
     * http://localhost:9000/api/v3/deletefavcuisine/{emailId}/{restaurantName}
     * @param emailId
     * @param cuisineName
     * @return ResponseEntity
     */
    @DeleteMapping("/customer/deletefavcuisine/{emailId}/{cuisineName}")
    public ResponseEntity<?> deleteFavouriteCuisine(@PathVariable String cuisineName, @PathVariable String emailId) {
        return new ResponseEntity<>(customerService.deleteFromFavouriteCuisine(cuisineName,emailId), HttpStatus.OK);
    }

    /**
     * http://localhost:9000/api/v3/customer/deletefromcart/{emailId}/{cuisineName}
     * @param emailId
     * @param cuisineName
     * @return ResponseEntity
     */
    @DeleteMapping("/customer/deletefromcart/{emailId}/{cuisineName}")
    public ResponseEntity<?> deleteFromCart(@PathVariable String cuisineName, @PathVariable String emailId) {
        return new ResponseEntity<>(customerService.deleteFromCart(cuisineName,emailId), HttpStatus.OK);
    }

    @GetMapping("/customer/{emailId}")
    public ResponseEntity<?> getCustomerByEmailId(@PathVariable String emailId){
        return new ResponseEntity<>(customerService.getCustomerByEmailId(emailId), HttpStatus.OK);
    }

    @DeleteMapping("/customer/deleteallcuisines/{emailId}")
    public ResponseEntity<?> deleteAllCuisines(@PathVariable String emailId) {
        return new ResponseEntity<>(customerService.deleteAllCuisines(emailId),HttpStatus.OK);
    }
}
