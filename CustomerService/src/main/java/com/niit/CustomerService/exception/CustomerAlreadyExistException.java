package com.niit.CustomerService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Customer Already Existing")
public class CustomerAlreadyExistException extends Exception{

}
