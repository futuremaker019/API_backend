package com.stock.analysis.dto.request;

import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.domain.entity.UserAccount;

public record CodeRequestDto(
        Long id,
        String name,
        Long parentId
) {
    public static CodeRequestDto of(Long id, String name, Long parentId) {
        return new CodeRequestDto(id, name, parentId);
    }

    public Code toEntity(UserAccount userAccount) {
        return Code.of(
                name,
                parentId,
                userAccount
        );
    }
}
