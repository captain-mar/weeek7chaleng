package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public static BCryptPasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private SSUserDetailsService userDetailsService;

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return new SSUserDetailsService(userRepository );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception{
    http.authorizeRequests()
            .antMatchers("/","/h2/**","/register","/css/**","/emailnotfund", "/send","/send2","/js/**").permitAll()
            .antMatchers("/add","/process","/show").access("hasAnyAuthority('ADMIN','USER')")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/").permitAll().permitAll()
            .and()
            .httpBasic();

    http.csrf().disable();
    http.headers().frameOptions().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.userDetailsService((userDetailsServiceBean()))
            .passwordEncoder(encoder());
  }
}
