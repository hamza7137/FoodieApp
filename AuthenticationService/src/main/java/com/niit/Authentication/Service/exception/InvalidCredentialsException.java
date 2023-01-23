package com.niit.Authentication.Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Invalid Credentials Entered")
public class InvalidCredentialsException extends Exception {
}
