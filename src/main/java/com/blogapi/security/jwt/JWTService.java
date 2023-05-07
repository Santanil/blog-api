package com.blog.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    Algorithm algorithm = Algorithm.HMAC256("secret key");

    public String createJWTToken(Integer userId) {
        return createJWTToken(
                userId,
                new Date(),
                new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)
        );
    }
    //create the jwt token
    public String createJWTToken(Integer userId, Date iat, Date exp) {
        String token = JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(iat)
                .withExpiresAt(exp) //7days valid
                .sign(algorithm);

        return token;
    }

    //Verify JWT token and return the userId if verification passed
    public Integer getUserIdFromJWTToken(String token){
        var decodedJWT=JWT.decode(token);
        var subject=decodedJWT.getSubject();
        return Integer.parseInt(subject);
    }

}
