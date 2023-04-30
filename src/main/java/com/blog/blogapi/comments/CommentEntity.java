package com.blog.blogapi.comments;

import com.blog.blogapi.articles.ArticleEntity;
import com.blog.blogapi.common.BaseEntity;
import com.blog.blogapi.users.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;

@Entity(name="comments")
public class CommentEntity extends BaseEntity {

    @Column(nullable = false, length = 100)
    String title;

    @Column(length = 1000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;

}
