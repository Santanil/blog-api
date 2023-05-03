package com.blog.blogapi.security.authtokens;

import com.blog.blogapi.users.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
}
