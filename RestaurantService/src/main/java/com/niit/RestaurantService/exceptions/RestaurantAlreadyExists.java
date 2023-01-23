package com.niit.RestaurantService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.ALREADY_REPORTED,reason = "Restaurant Already Exists")
public class RestaurantAlreadyExists extends Throwable{
}
