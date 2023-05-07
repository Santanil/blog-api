package com.blog.blogapi.users;
import com.blog.blogapi.dtos.CreateUserDTO;
import com.blog.blogapi.security.jwt.JWTService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;


//DataJpaTest are much faster than SpringBootTest
@DataJpaTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    private UserService userService;

    private UserService getUserService(){
        if(userService==null){
            var modelMapper =new ModelMapper();
            var passwordEncoder=new BCryptPasswordEncoder();
            var jwtService=new JWTService();
            userService=new UserService(userRepository,modelMapper, passwordEncoder, jwtService);
        }
        return userService;
    }

    @Test
    public void testCreateUser(){
        var newUserDTO=new CreateUserDTO();
        newUserDTO.setEmail("test@gmail.com");
        newUserDTO.setUsername("testUser1");
        newUserDTO.setPassword("testpwd");
//        newUserDTO.setBio("test bio");
//        newUserDTO.setImage("testImage");
        var savedUser=getUserService().createUser(newUserDTO);
        assertNotNull(savedUser);
    }
}
