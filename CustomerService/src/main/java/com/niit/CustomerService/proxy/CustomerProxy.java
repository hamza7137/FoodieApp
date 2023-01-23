package com.niit.CustomerService.proxy;


import com.niit.CustomerService.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="authentication-service",url = "localhost:8082")
public interface CustomerProxy
{
    @PostMapping("/api/v2/signup")
    public ResponseEntity<?> signUp(@RequestBody User user);
}
