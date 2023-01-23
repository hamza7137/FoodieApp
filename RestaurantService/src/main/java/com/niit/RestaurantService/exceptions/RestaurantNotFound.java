package com.niit.RestaurantService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Restaurant not Found")
public class RestaurantNotFound extends Throwable{
}
