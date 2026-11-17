package com.rdp.authbackend.config;

import com.rdp.authbackend.service.UserInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // only for practice, NEVER in prod
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
// This tells Spring: use my security config, not the default one (which would create a default login form)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable CSRF (Cross Site Request Forgery) protection
        // Generally not recommended for browser-based apps, but often disabled for REST APIs
        http.csrf(AbstractHttpConfigurer::disable);

        // Authorize all HTTP requests: every request must be authenticated
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        // Enable default login form (Spring Security will generate a basic login page)
        http.formLogin();

        // Enable HTTP Basic authentication (useful for REST clients like Postman, curl, etc.)
        http.httpBasic();

        // If you want stateless sessions (e.g., JWT instead of cookies), you’d configure:
         http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

         

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserInfoService myUserDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(11)); // Use BCrypt with strength 11
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // only for practice, NEVER in prod
        return provider;
    }
}
