package com.blog.blogapi.security.authtokens;

import com.blog.blogapi.users.UserEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthTokenService {

    private final AuthTokenRepository authTokenRepository;

    public AuthTokenService(AuthTokenRepository authTokenRepository){
        this.authTokenRepository=authTokenRepository;
    }

    public UUID createAuthToken(UserEntity userEntity){
        AuthTokenEntity authTokenEntity = new AuthTokenEntity();
        authTokenEntity.setUser(userEntity);
        var savedAuthToken=authTokenRepository.save(authTokenEntity);
        return savedAuthToken.getId();
    }

    public UserEntity getUserIdFromAuthToken(UUID authToken){
        var savedAuthToken=authTokenRepository.findById(authToken)
                .orElseThrow(() -> new BadCredentialsException("Invalid Auth Token"));

        return savedAuthToken.getUser();
    }
}
