package com.stock.analysis.dto.request;

import com.stock.analysis.domain.contant.CodeType;
import com.stock.analysis.domain.entity.Code;

public record CodeRequestDto(
        String name,
        Long parentId,
        CodeType codeType
) {
    public static CodeRequestDto of(String name, Long parentId, CodeType codeType) {
        return new CodeRequestDto(name, parentId, codeType);
    }

    public Code to() {
        return Code.of(null, name, parentId);
    }
}
