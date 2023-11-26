package com.stock.analysis.dto;

import com.stock.analysis.domain.entity.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        String userId,
        String userPassword,
        String email,
        String nickname,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static UserAccountDto of(Long id, String userId, String userPassword, String email, String nickname) {
        return new UserAccountDto(id, userId, userPassword, email, nickname, null, null, null, null);
    }

    // of : 팩토리 메서드 패턴으로 dto 객체 생성
    public static UserAccountDto of(Long id, String userId, String userPassword, String email, String nickname, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(id, userId, userPassword, email, nickname, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
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
                nickname
        );
    }
}
