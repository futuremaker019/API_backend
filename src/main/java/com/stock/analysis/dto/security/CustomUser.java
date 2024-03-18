package com.stock.analysis.dto.security;

import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.entity.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUser extends User {

    private UserAccount userAccount;

    public CustomUser(UserAccount userAccount) {
        super(userAccount.getUserId(), userAccount.getUserPassword(), authorities(userAccount.getRoleTypes()));
        this.userAccount = userAccount;
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<RoleType> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet());
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
