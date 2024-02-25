package com.stock.analysis.dto.request;

import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.Code;

public record CodeRequestDto(
        Long id,
        String name,
        Long parentId,
        CodeType codeType
) {
    public static CodeRequestDto of(Long id, String name, Long parentId, CodeType codeType) {
        return new CodeRequestDto(id, name, parentId, codeType);
    }

    public Code to() {
        return Code.of(null, name, parentId);
    }
}
