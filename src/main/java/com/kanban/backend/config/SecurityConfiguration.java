package com.kanban.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private AuthenticationProvider authenticationProvider;
    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/register",
            "/api/v1/auth/authenticate",
            "api/v1/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        System.out.println("http"+http);
        http.cors().and().csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


//        http.csrf()
////                .disable()
////                .authorizeRequests()
////                .requestMatchers(WHITE_LIST_URL)
////                .permitAll()
////                .anyRequest()
////                .authenticated();


//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/api/v1/auth/register").permitAll()
//                .requestMatchers("/api/v1/auth/authenticate").permitAll()
//                .anyRequest().authenticated()


        return http.build();
    }
}
