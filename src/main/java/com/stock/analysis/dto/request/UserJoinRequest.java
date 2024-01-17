package com.stock.analysis.dto.request;

import javax.validation.constraints.NotBlank;

public record UserJoinRequest(
        @NotBlank(message = "필수값입니다.") String userId,
        @NotBlank(message = "필수값입니다.") String password
) {
    public UserJoinRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
