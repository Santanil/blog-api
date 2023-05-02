package com.blog.blogapi.articles;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService){
        this.articleService=articleService;
    }

    @GetMapping("")
    public String getArticles(){
        return "Articles";
    }

    @GetMapping("/private")
    public String getPrivateArticles(@AuthenticationPrincipal Integer userId){
        return "Private Articles fetched for user id: "+userId;
    }
}
