package com.stock.analysis.dto;

import com.stock.analysis.domain.contant.RoleType;
import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.request.UserJoinRequest;

import java.time.LocalDateTime;
import java.util.Set;

public record UserAccountDto(
        Long id,
        String userId,
        String userPassword,
        String email,
        String nickname,
        Set<RoleType> roleTypes,    // 권한은 Admin이
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserAccountDto of(Long id, String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname) {
        return new UserAccountDto(id, userId, userPassword, email, nickname, roleTypes, null, null, null, null);
    }

    // of : 팩토리 메서드 패턴으로 dto 객체 생성
    public static UserAccountDto of(Long id, String userId, String userPassword, Set<RoleType> roleTypes, String email, String nickname, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(id, userId, userPassword, email, nickname, roleTypes, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto of(String userId, String userPassword, Set<RoleType> roleTypes) {
        return new UserAccountDto(null, userId, userPassword, null, null, roleTypes, null, null, null, null);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getRoleTypes(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                id,
                userId,
                userPassword,
                email,
                nickname,
                roleTypes
        );
    }
}
