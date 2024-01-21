package com.stock.analysis.dto.request;

import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.dto.UserAccountDto;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public record UserJoinRequest(
        @NotBlank(message = "아이디는 필수값입니다.") String userId,
        @NotBlank(message = "패스워드는 필수값입니다.") String password,
        @NotBlank(message = "권한은 필수값입니다.") String authority
) {
    public static UserJoinRequest of(String userId, String password, String authority) {
        return new UserJoinRequest(
                userId,
                password,
                authority
        );
    }

    public UserAccountDto toDto() {
        Set<RoleType> roleTypes;
        switch (authority) {
            case "DEVELOPER" -> roleTypes = Set.of(RoleType.USER, RoleType.DEVELOPER);
            case "MANAGER" -> roleTypes = Set.of(RoleType.USER, RoleType.MANAGER);
            case "ADMIN" -> roleTypes = Set.of(RoleType.USER, RoleType.ADMIN);
            default -> roleTypes = Set.of(RoleType.USER);
        }

        return UserAccountDto.of(userId, password, roleTypes);
    }
}
