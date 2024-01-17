package com.stock.analysis.dto.request;

import javax.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank(message = "아이디는 필수값입니다.") String userId,
        @NotBlank(message = "패스워드는 필수값입니다.") String password
) {
    public UserLoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
