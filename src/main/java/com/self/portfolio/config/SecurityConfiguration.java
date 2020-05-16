package com.self.portfolio.config;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("Authentication started.." + auth.toString());
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/users/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll().
                anyRequest().authenticated()
                .and().httpBasic();
                // .and().formLogin();
                // .antMatchers("/users").hasRole("ADMIN")
                // .antMatchers("/states").hasAnyRole("USER","ADMIN")
                // .antMatchers("/**").permitAll();
            }
            // .and().formLogin()
            // .permitAll();

    @Bean
    public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
    }
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     // System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
    //     return new BCryptPasswordEncoder();
    // }

}