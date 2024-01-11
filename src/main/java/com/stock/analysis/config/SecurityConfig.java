package com.stock.analysis.config;

import com.stock.analysis.config.filter.ForceLoginFilter;
import com.stock.analysis.dto.security.UserPrincipal;
import com.stock.analysis.application.useraccount.service.UserAccountService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

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
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .antMatchers(
                                HttpMethod.GET,
                                "/"
                        ).permitAll()
                        .antMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                    .formLogin()
                .and()
                    .addFilterBefore(forceLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
        return username -> userAccountService.searchUser(username)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. userId : " + username));
    }
}
