package com.Travelbnb.service;

import com.Travelbnb.entity.Appuser;
import com.Travelbnb.paylod.LoginDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class jwtservice {


    @Value("${jwt.algorithm.SecurityKey}")
    private String algorithmkey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;
    private final String USER_NAME = "username";

    private Algorithm algorithm;

    @PostConstruct // it is part of java EE and used when dependency injection are done
    //-> it is used for validation or Initializing caches or data structures.
    public void postconstruct(){
        algorithm = Algorithm.HMAC256(algorithmkey);
//        System.out.println(algorithmkey);
    }
    public String GenrateToken(Appuser user){
        return JWT.create().withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }
     public String getUsername(String token){
        DecodedJWT decodedJWT =JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJWT.getClaim(USER_NAME).asString();
     }
}
