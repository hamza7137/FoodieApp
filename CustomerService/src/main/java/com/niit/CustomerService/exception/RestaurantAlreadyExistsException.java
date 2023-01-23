package com.niit.CustomerService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Restaurant Already Existing")
public class RestaurantAlreadyExistsException extends Exception {
}
