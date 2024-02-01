package com.stock.analysis.dto.response;

import com.stock.analysis.domain.entity.UserAccount;

public record UserLoginResponse(
        Long id,
        String userId,
        String email,
        String nickname,
        String token
) {
    public static UserLoginResponse of(Long id, String userId, String email, String nickname, String token) {
        return new UserLoginResponse(id, userId, email, nickname, token);
    }

    public static UserLoginResponse from(UserAccount userAccount, String token) {
        return new UserLoginResponse(
                userAccount.getId(),
                userAccount.getUserId(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                token
        );
    }
}
