package com.stock.analysis.config;

import com.stock.analysis.config.filter.ForceLoginFilter;
import com.stock.analysis.exception.CustomAuthenticationEntryPoint;
import com.stock.analysis.config.jwt.JwtFilter;
import com.stock.analysis.config.jwt.TokenManager;
import com.stock.analysis.dto.security.UserPrincipal;
import com.stock.analysis.application.useraccount.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenManager tokenManager;

    @Bean
    public ForceLoginFilter forceLoginFilter() {
        return new ForceLoginFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .antMatchers(HttpMethod.GET, "/", "/swagger-ui/**").permitAll()
                        .antMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
                        .antMatchers("/api/**").authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtFilter(tokenManager), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
        return username -> userAccountService.searchUser(username)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. userId : " + username));
    }
}
