package com.stock.analysis.dto.response;

import com.stock.analysis.domain.entity.UserAccount;
import com.stock.analysis.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinResponse {

    private Long id;
    private String userId;

    public static UserJoinResponse fromUserAccountDto(UserAccountDto dto) {
        return new UserJoinResponse(
                dto.id(),
                dto.userId()
        );
    }

    public static UserJoinResponse fromUserAccount(UserAccount userAccount) {
        return new UserJoinResponse(
                userAccount.getId(),
                userAccount.getUserId()
        );
    }
}
