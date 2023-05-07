package com.blog.blogapi.security.authtokens;

import com.blog.blogapi.users.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "auth_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne
    private UserEntity user;
}
