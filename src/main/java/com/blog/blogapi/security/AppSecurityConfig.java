package com.blog.blogapi.security;

import com.blog.blogapi.security.jwt.JWTAuthenticationFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;


@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


    protected void configure(HttpSecurity http) throws Exception {

        //should not be disabled in prod
        http.csrf().disable().cors().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users/**").permitAll()
                .antMatchers(HttpMethod.GET,"/articles").permitAll()
                .anyRequest().authenticated();

        http
                .addFilterBefore(new JWTAuthenticationFilter(), AnonymousAuthenticationFilter.class);

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
}
