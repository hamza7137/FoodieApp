package com.niit.admin.controller;

import com.niit.admin.domain.Admin;
import com.niit.admin.domain.AdminRestaurant;
import com.niit.admin.service.AdminServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AdminController {
    @Autowired
    AdminServiceImplementation adminService;

    /**
     * admin adding restaurants in the dashboard
     * @param restaurant
     * @return ResponseEntity
     */
    @PostMapping("/addrestautrant")
    public ResponseEntity<?> registerRestaurant(@RequestBody AdminRestaurant restaurant) {
        return new ResponseEntity<>(adminService.addRestaurant(restaurant), HttpStatus.CREATED);
    }

    /**
     *
     * @param emailId
     * @return Response message
     */

    @PostMapping("/admin/{emailId}/approverestaurant")
    public ResponseEntity<?> approveRestaurant (@PathVariable String emailId) {
       return new ResponseEntity<>(adminService.sendToUser(emailId),HttpStatus.OK);
    }

    /**
     * Get all restaurants added by admins
     * @return ResponseEntity
     */
    @GetMapping("/admin/restaurants")
    public ResponseEntity<?> getallRestaurants () {
        return new ResponseEntity<>(adminService.findAllRestaurants(),HttpStatus.OK);
    }

    // Not required.
    @PostMapping("/registeradmin")
    public  ResponseEntity<?> registeradmin(Admin admin) {
        return new ResponseEntity<>(adminService.registeradmin(admin),HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleterestaurant/{emailId}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String emailId) {
        return new ResponseEntity<>(adminService.deleteAdminRestaurant(emailId),HttpStatus.OK);
    }

    @PutMapping("/adminAfterApprovedRestaurant/{emailId}")
    public ResponseEntity<?> afterApprovedRestaurant(@PathVariable String emailId) {
        return new ResponseEntity<>(adminService.afterApprovedRestaurant(emailId), HttpStatus.OK);
    }
}