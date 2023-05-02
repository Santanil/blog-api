package com.blog.blogapi.security.jwt;

import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class JWTServiceTests {

    private JWTService jwtService=new JWTService();
    @Test
    void canCreateJWTFromUserId(){
        var userId=1122;
        var jwt=jwtService.createJWTToken(userId);
        Assert.assertEquals(jwt,jwt);
    }
}
