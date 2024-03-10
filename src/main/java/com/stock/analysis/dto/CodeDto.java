package com.stock.analysis.dto;

import com.stock.analysis.domain.entity.Code;

import java.util.List;

/**
 * 내부 로직에서 사용되는 code dto
 */
public record CodeDto(
        Long id,
        String name,
        Long parentId,
        List<Long> children
) {
    public static CodeDto of(Long id, String name, Long parentId, List<Long> children) {
        return new CodeDto(id, name, parentId, children);
    }

    public static CodeDto from(Code code) {
        return CodeDto.of(
                code.getId(),
                code.getName(),
                code.getParentId(),
                code.getChildren().stream().map(Code::getId).toList()
        );
    }
}
