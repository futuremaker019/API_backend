package com.stock.analysis.application.code.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.domain.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponseDto{

    private Long id;
    private String name;
    private String icon;
    private Long parentId;
    private List<CodeResponseDto> children;
    private String createdBy;
    private String modifiedBy;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @QueryProjection
    public CodeResponseDto(Long id, String name, Long parentId, List<CodeResponseDto> children) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = children;
    }

    public static CodeResponseDto of(Long id,
                                     String name,
                                     Long parentId,
                                     String icon,
                                     List<CodeResponseDto> children,
                                     String createdBy,
                                     LocalDateTime createdAt,
                                     String modifiedBy,
                                     LocalDateTime modifiedAt
                                     ) {
        return new CodeResponseDto(id, name, icon, parentId, children, createdBy, modifiedBy, createdAt, modifiedAt);
    }

    public static CodeResponseDto from(Code code) {
        return CodeResponseDto.of(
                code.getId(),
                code.getName(),
                code.getParentId(),
                code.getIcon(),
                code.getChildren().stream()
                        .map(CodeResponseDto::from).toList(),
                code.getCreatedBy(),
                code.getCreatedAt(),
                code.getModifiedBy(),
                code.getModifiedAt()
        );
    }
}
