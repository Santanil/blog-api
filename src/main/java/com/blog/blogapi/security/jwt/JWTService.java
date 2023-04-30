package com.blog.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    Algorithm algorithm = Algorithm.HMAC256("this is the secret key but don't hard code the secret key");

    //create the jwt token
    public String createJWTtoken(Integer userId) {
        String token = JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000*60*60*24*7)) //7days valid
                .sign(algorithm);

        return token;
    }

    //Verify JWT token and return the userId if verification passed
    public Integer getUserIdFromJWTToken(String token){
        JWTVerifier verifier = JWT.require(algorithm)
                // specify an specific claim validations
                .withIssuer("auth0")
                // reusable verifier instance
                .build();
        var decodedJWT=verifier.verify(token);
        var subject=decodedJWT.getSubject();
        return Integer.parseInt(subject);
    }

}
