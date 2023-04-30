package com.blog.blogapi.users;

import com.blog.blogapi.dtos.CreateUserDTO;
import com.blog.blogapi.dtos.LoginUserDTO;
import com.blog.blogapi.dtos.UserResponseDTO;
import com.blog.blogapi.security.jwt.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JWTService jwtService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO createUser(CreateUserDTO createUserDTO){
        //TODO: Validate email
        //TODO: Check if username already exists
        var newUserEntity=modelMapper.map(createUserDTO,UserEntity.class);
        newUserEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        var savedUser=userRepository.save(newUserEntity);
        var userResponseDTO=modelMapper.map(savedUser,UserResponseDTO.class);
        //send the token to the client after successful user creation
        userResponseDTO.setToken(jwtService.createJWTtoken(savedUser.getId()));
        return userResponseDTO;
    }

    public UserResponseDTO loginUser(LoginUserDTO loginUserDTO){
        var userEntity=userRepository.findByUsername(loginUserDTO.getUsername());
        //TODO: implement password matching logic
        if(userEntity==null){
            throw new UserNotFoundException(loginUserDTO.getUsername());
        }
        var passMatch=passwordEncoder.matches(loginUserDTO.getPassword(),userEntity.getPassword());
        if(!passMatch){
            throw new IncorrectPasswordException("Incorrect Password");
        }
        var userResponseDTO=modelMapper.map(userEntity,UserResponseDTO.class);
        //send the token to the client after successful login
        userResponseDTO.setToken(jwtService.createJWTtoken(userEntity.getId()));
        return userResponseDTO;
    }

    public static class UserNotFoundException extends IllegalArgumentException{
        public UserNotFoundException(Integer id){
            super("User with id: "+id+ " was not found");
        }
        public UserNotFoundException(String username){
            super("User with username: "+username+ " was not found");
        }
    }

    public static class IncorrectPasswordException extends IllegalArgumentException{
        public IncorrectPasswordException(String msg){
            super(msg);
        }
    }
}
