package com.blog.blogapi.security.jwt;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


public class JWTServiceTests {

    private JWTService jwtService=new JWTService();
    @Test
    void canCreateJWTFromUserId(){
        var userId=1122;
        var jwt=jwtService.createJWTToken(userId);
        //Assert.assertEquals(jwt,jwt);
    }
}
