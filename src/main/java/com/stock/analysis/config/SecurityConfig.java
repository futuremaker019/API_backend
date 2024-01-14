package com.stock.analysis.config;

import com.stock.analysis.exception.CustomAccessDeniedHandler;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenManager tokenManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .antMatchers(HttpMethod.GET, "/", "/swagger-ui/**").permitAll()
                        .antMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
                        .antMatchers("/api/**").authenticated()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.addExposedHeader("Set-Cookie");
        configuration.addExposedHeader("Content-Disposition");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
