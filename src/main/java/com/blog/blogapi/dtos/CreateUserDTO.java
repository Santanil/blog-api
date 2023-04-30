package com.blog.blogapi.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {
    String email;
    String username;
    String password;
    String bio;
    String image;
}
