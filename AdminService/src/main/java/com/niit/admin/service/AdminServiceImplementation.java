package com.niit.admin.service;

import com.niit.admin.domain.Admin;
import com.niit.admin.domain.AdminRestaurant;
import com.niit.admin.domain.ApproveType;
import com.niit.admin.domain.UserRole;
import com.niit.admin.rabbitmq.Producer;
import com.niit.admin.rabbitmq.RestauantDTO;
import com.niit.admin.rabbitmq.UserDTO;
import com.niit.admin.repository.AdminRepository;
import com.niit.admin.repository.AdminRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImplementation {
    @Autowired
    AdminRestaurantRepository adminRestaurantRepository;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    Producer producer;

    public Admin registeradmin (Admin admin) {
        admin.setUserRole(UserRole.ADMIN);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmailId(admin.getEmailId());
        userDTO.setPassword(admin.getPassword());
        userDTO.setUserName(admin.getUserName());
        userDTO.setUserRole(admin.getUserRole());
        producer.sendMessageToUser(userDTO);
        return adminRepository.save(admin);
    }

    public AdminRestaurant addRestaurant(AdminRestaurant adminRestaurant) {
        return adminRestaurantRepository.save(adminRestaurant);
    }

    public List<AdminRestaurant> findAllRestaurants() {
        return adminRestaurantRepository.findAll();
    }

    public String sendToUser(String emailId)
    {
        AdminRestaurant restauantDTO = adminRestaurantRepository.findById(emailId).get();
        System.out.println(restauantDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmailId(restauantDTO.getEmailId());
        userDTO.setPassword(restauantDTO.getPassword());
        userDTO.setUserName(restauantDTO.getUserName());
        userDTO.setUserRole(UserRole.RESTAURANT);
        producer.sendMessageToUser(userDTO);
        return "Restaurant Approved";
    }

    public boolean deleteAdminRestaurant(String emailId) {
        adminRestaurantRepository.deleteById(emailId);
        return true;
    }

    public AdminRestaurant afterApprovedRestaurant(String emailId) {
        AdminRestaurant restaurantDTO = adminRestaurantRepository.findById(emailId).get();
        restaurantDTO.setApproveType(ApproveType.APPROVED);
        return adminRestaurantRepository.save(restaurantDTO);
    }
}



























//    @Override
//    public Admin register(Admin admin) {
//        admin.setUserRole(UserRole.ADMIN);
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmailId(admin.getEmailId());
//        userDTO.setPassword(admin.getPassword());
//        userDTO.setUserRole(admin.getUserRole());
//        userDTO.setUserName(admin.getUserName());
//        producer.sendMessageToRabbitMq(userDTO);
//        return adminRepository.save(admin);
//
//    }
//
//
//    /**
//     *
//     * @param restaurant Restaurant object
//     * @param emailId
//     * @return List Of Restaurants and newly added restaurant
//     */
//
//    @Override
//    public List<Restaurant> approveRestaurant(Restaurant restaurant, String emailId)
//    {
//        restaurant.setRestaurantId(UUID.randomUUID());
//        Admin admin= new Admin(emailId,List.of(restaurant));
//        Admin admin1=adminRepository.findByEmailId(emailId);
//        if(admin1!=null)
//        {
//            admin.getRestaurants().add(restaurant);
//        }
//        else {
//            admin1 = admin;
//        }
//        return adminRepository.save(admin).getRestaurants();
//    }
//
//    /**
//     *
//     * @param restaurantId
//     * @return String message
//     */
//    @Override
//    public String deleteRestaurant(UUID restaurantId) {
//        Admin admin= (Admin) adminRepository.findAll();
//        List<Restaurant> restaurants;
//        restaurants=admin.getRestaurants();
//        for (Restaurant restaurant: restaurants){
//            if(restaurant.getRestaurantId()==restaurantId)
//            {
//                restaurants.remove(restaurant);
//            }
//            admin.setRestaurants(restaurants);
//        }
//        return "Restaurant got deleted";
//    }
//
//    /**
//     *
//     * @return List Of Restaurant
//     */
//    @Override
//    public List<Restaurant> getRestaurants() {
//        List<Admin> admins= adminRepository.findAll();
//        List<Restaurant> restaurants=new ArrayList<>();
//        for (Admin admin: admins){
//            restaurants.addAll(admin.getRestaurants());
//        }
//        return restaurants;
//    }

