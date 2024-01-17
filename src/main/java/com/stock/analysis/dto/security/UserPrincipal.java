package com.stock.analysis.dto.security;

import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public record UserPrincipal(
        Long id,
        String userId,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname
) implements UserDetails {

    public static UserPrincipal of(Long id, String userId, String password, String email, String nickname) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new UserPrincipal(
                id,
                userId,
                password,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname
        );
    }

    public static UserPrincipal of(String userId, String password) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);
        return new UserPrincipal(
                null,
                userId,
                password,
                roleTypes.stream()
                        .map(RoleType::getRoleName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                null,
                null
        );
    }

    public static UserPrincipal from(UserAccountDto dto) {
        return UserPrincipal.of(
                dto.id(),
                dto.userId(),
                dto.userPassword(),
                dto.email(),
                dto.nickname()
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                id,
                userId,
                password,
                email,
                nickname
        );
    }

//    public enum RoleType {
//        USER("ROLE_USER");
//
//        @Getter
//        private final String name;
//
//        RoleType(String name) {
//            this.name = name;
//        }
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
