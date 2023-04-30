package com.blog.blogapi.articles;

import com.blog.blogapi.common.BaseEntity;
import com.blog.blogapi.users.UserEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity(name="articles")
public class ArticleEntity extends BaseEntity {

    @Column(unique = true,nullable = false,length = 150)
    String slug;

    @Column(nullable = false,length = 200)
    String title;
    String subtitle;

    @Column(nullable = false, length = 8000)
    String body;


    @ManyToMany
    @JoinTable(
            name = "article_likes",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<UserEntity> likedBy;

}
