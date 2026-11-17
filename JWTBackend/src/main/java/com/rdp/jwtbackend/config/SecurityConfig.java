package com.rdp.jwtbackend.config;

import com.rdp.jwtbackend.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // only for practice, NEVER in prod
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
// This tells Spring: use my security config, not the default one (which would create a default login form)
public class SecurityConfig {

    @Autowired
    JWTFilter filter;

    // This Security Filter Chain, that decides what calls would be authenticated, and what'll be blocked
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable CSRF (Cross Site Request Forgery) protection
        // Generally not recommended for browser-based apps, but often disabled for REST APIs
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request

                        // Authorize all HTTP requests except register and login
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest()
                        .authenticated());

        // Enable default login form (Spring Security will generate a basic login page)
//        http.formLogin();

        // Enable HTTP Basic authentication (useful for REST clients like Postman, curl, etc.)
        http.httpBasic(Customizer.withDefaults());

        // If you want stateless sessions (e.g., JWT instead of cookies), you’d configure:
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // This is used when we want to add a filter at a specific position. (What is filter? See end of this file!)
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserInfoService myUserDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(11)); // Use BCrypt with strength 11
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // only for practice, NEVER use in prod
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}


/*
Basically a filter in normal words is like yk filtering something. Same thing here, it simply filters the Username and Password and the Authentication. Before that "addFilterBefore()" class, the filter used was simple Username and Password, means it just checked for Username and Password and allows the user to login.
But now, it checks for the JWT Token first, that we created in the JWTService Class, amd then also checks for the Username and Password
 */