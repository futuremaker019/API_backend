package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.converter.RoleTypesConverter;
import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString(callSuper = true)
@Table(indexes = {

})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    @NotBlank(message = "아이디 필수")
    private String userId;

    @Setter
    @Column(nullable = false)
    @NotBlank(message = "패스워드 필수")
    private String userPassword;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;

    @Convert(converter = RoleTypesConverter.class)
    @Column(nullable = false)
    private Set<RoleType> roleTypes = new LinkedHashSet<>();

    private UserAccount(Long id, String userId, String userPassword, String email, String nickname, Set<RoleType> roleTypes) {
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.roleTypes = roleTypes;
    }

    public static UserAccount of(Long id, String userId, String userPassword, String email, String nickname, Set<RoleType> roleTypes) {
        return new UserAccount(id, userId, userPassword, email, nickname, roleTypes);
    }

    public static UserAccount of(String userId, String userPassword, String email, String nickname, Set<RoleType> roleTypes) {
        return new UserAccount(null, userId, userPassword, email, nickname, roleTypes);
    }

    public void addRoleType(RoleType roleType) {
        this.getRoleTypes().add(roleType);
    }

    public void addRoleTypes(Collection<RoleType> roleTypes) {
        this.getRoleTypes().addAll(roleTypes);
    }

    public void removeRoleType(RoleType roleType) {
        this.getRoleTypes().remove(roleType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return this.getId() != null && this.getId().equals(userAccount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId());
    }
}
