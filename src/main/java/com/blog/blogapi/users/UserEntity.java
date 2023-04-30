package com.blog.blogapi.users;

import com.blog.blogapi.articles.ArticleEntity;
import com.blog.blogapi.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="users")
@Setter
@Getter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    Integer id;
    String username;
    String password;
    String email;


    @ManyToMany(mappedBy = "likedBy")
    List<ArticleEntity> likedArticles;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    List<UserEntity> following;

    @ManyToMany(mappedBy = "following")
    List<UserEntity> followers;

}
