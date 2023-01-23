package com.niit.Authentication.Service.security;

import com.niit.Authentication.Service.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGenerator {

    public Map<String, String> generateToken(User user) {

        long currenttime = System.currentTimeMillis();
        // multiple claims for a token - 3 types - registered, public, and private
        String jwtToken = Jwts.builder().setIssuer("ShopZone")
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date(currenttime))
                .signWith(SignatureAlgorithm.HS256, "mysecretkey")
                .setExpiration(new Date(currenttime+600000))  // token expires in 10 mins  (1000*60*10)
                //mysecret is the key that has to be shared everytime you do encrypt and decrypt process
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "Authentication Successful");
        map.put("message","User successfully logged in");
        map.put("emailId",user.getEmailId());
        String userRole = String.valueOf(user.getUserRole());
        map.put("userRole", userRole);
        return map;
    }
}
