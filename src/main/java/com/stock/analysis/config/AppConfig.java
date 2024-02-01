package com.stock.analysis.config;

import com.stock.analysis.application.useraccount.service.UserAccountService;
import com.stock.analysis.dto.security.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserAccountService userAccountService) {
        return username -> userAccountService.searchUser(username)
                .map(UserPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. userId : " + username));
    }
}
