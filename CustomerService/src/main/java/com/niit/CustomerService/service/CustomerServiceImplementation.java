package com.niit.CustomerService.service;

import com.niit.CustomerService.domain.Cuisine;
import com.niit.CustomerService.domain.User;
import com.niit.CustomerService.domain.Restaurant;
import com.niit.CustomerService.domain.UserRole;
import com.niit.CustomerService.exception.CustomerAlreadyExistException;

import com.niit.CustomerService.exception.RestaurantAlreadyExistsException;
import com.niit.CustomerService.proxy.CustomerProxy;
import com.niit.CustomerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CustomerServiceImplementation implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerProxy customerProxy;




    /**
     *
     * @param user
     * @return Customer
     * @throws CustomerAlreadyExistException
     */
    public User registerCustomer(User user) throws CustomerAlreadyExistException {
        if(customerRepository.findById(user.getEmailId()).isPresent()){
            throw new CustomerAlreadyExistException();
        }
        user.setUserRole(UserRole.USER);
        System.out.println("*****saving in.....Authentication Service*****");
        customerProxy.signUp(user);

        System.out.println("*****saving in......Customer Service*****");
        return customerRepository.save(user);
    }

    /**
     *
     * @param user
     * @return Customer
     */
    public User updateCustomer(User user) {
        System.out.println("*****Customer Service editCustomerDetail***** ");
        User customer1=customerRepository.findByEmailIdAndPassword(user.getEmailId() , user.getPassword());
        customer1.setUserName(user.getUserName());
        customer1.setPhoneNo(user.getPhoneNo());
        return customerRepository.save(customer1);
    }

    /**
     *
     * @param restaurant
     * @param emailId
     * @return Customer
     */
    public User addFavouriteRestaurant(Restaurant restaurant , String emailId) throws RestaurantAlreadyExistsException {
        List<Restaurant> favRestauant = new ArrayList<>();
        User user = customerRepository.findByEmailId(emailId);
        favRestauant = user.getFavouriteRestaurant();
        if(favRestauant==null)
        {
            user.setFavouriteRestaurant(Arrays.asList(restaurant));
        }
        else if(favRestauant.contains(restaurant)) {
            throw new RestaurantAlreadyExistsException();
        }
        else
        {
            favRestauant.add(restaurant);
            user.setFavouriteRestaurant(favRestauant);
        }
        return customerRepository.save(user);
    }

    /**
     *
     * @param cuisine
     * @param emailId
     * @return Customer
     */
    public User addFavouriteCuisine(Cuisine cuisine , String emailId)
    {
        List<Cuisine> favCuisine= new ArrayList<>();
        User user= customerRepository.findByEmailId(emailId);
        favCuisine=user.getFavouriteCuisine();
        if (favCuisine ==null)
        {
            user.setFavouriteCuisine(Arrays.asList(cuisine));
        }
        else {
            favCuisine.add(cuisine);
            user.setFavouriteCuisine(favCuisine);
        }
        return customerRepository.save(user);

    }

    /**
     *
     * @param cuisine
     * @param emailId
     * @return Customer
     */


    @Override
    public User addToCart(Cuisine cuisine, String emailId) {
        List<Cuisine> cart = new ArrayList<>();
        User customer = customerRepository.findByEmailId(emailId);
        cart = customer.getCart();
        if (cart == null)
        {
            customer.setCart(Arrays.asList(cuisine));
        }
        else {
            cart.add(cuisine);
            customer.setCart(cart);
        }
        return customerRepository.save(customer);
    }


    /**
     *
     * @param emailId
     * @return List<Restaurant>
     */
    public List<Restaurant> getFavouriteRestaurant(String emailId) {
        return customerRepository.findByEmailId(emailId).getFavouriteRestaurant();
    }


    /**
     *
     * @param emailId
     * @return Customer
     */
    public List<Cuisine> getFavouriteCuisine(String emailId) {
        return customerRepository.findByEmailId(emailId).getFavouriteCuisine();
    }


    /**
     *
     * @param emailId
     * @return List<Cuisine>
     */
    public List<Cuisine> getFromCart(String emailId) {
        return customerRepository.findByEmailId(emailId).getCart();
    }


    /**
     *
     * @param restaurantName
     * @param emailId
     * @return Customer
     */
    public User deleteFromFavouriteRestaurant(String restaurantName, String emailId) {
        User cust = customerRepository.findById(emailId).get();
        cust.getFavouriteRestaurant().removeIf(cus->cus.getRestaurantName().equals(restaurantName));
        return  customerRepository.save(cust);
    }


    /**
     *
     * @param cuisineName
     * @param emailId
     * @return Customer
     */
    public User deleteFromFavouriteCuisine(String cuisineName, String emailId) {
        User cust = customerRepository.findById(emailId).get();
        cust.getFavouriteCuisine().removeIf(cus->cus.getCuisineName().equals(cuisineName));
        return  customerRepository.save(cust);
    }


    /**
     *
     * @param cuisineName
     * @param emailId
     * @return Customer
     */
    public User deleteFromCart(String cuisineName , String emailId) {
        User cust = customerRepository.findById(emailId).get();
        cust.getCart().removeIf(cus->cus.getCuisineName().equals(cuisineName));
        return  customerRepository.save(cust);
    }

    /**
     *
     * @param emailId
     * @return Customer
     */
    public User getCustomerByEmailId(String emailId){
        return customerRepository.findById(emailId).get();
    }

    public boolean deleteAllCuisines(String emailId){
        User cust = customerRepository.findById(emailId).get();
        List<Cuisine> cart = cust.getCart();
        System.out.println(cart);
        cart.removeAll(cart);
        System.out.println("After cart.removeAll() : "+cart);
        cust.setCart(cart);
        System.out.println("cust.getCart() : "+cust.getCart());
        customerRepository.save(cust);
        return true;
    }
}
