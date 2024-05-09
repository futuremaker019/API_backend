package com.stock.analysis.application.code.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.application.useraccount.dto.UserAccountResponseDto;
import com.stock.analysis.domain.entity.Code;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponseDto implements Serializable {

    private Long id;
    private String name;
    private String icon;
    private Long parentId;
    private List<CodeResponseDto> children;
    private UserAccountResponseDto userAccount;
    private Long userId;

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
        return new CodeResponseDto(id, name, icon, parentId, children, null, null, createdBy, modifiedBy, createdAt, modifiedAt);
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

    public static CodeResponseDto dtoWithoutChildren(Code code) {
        return CodeResponseDto.builder()
                .id(code.getId())
                .name(code.getName())
                .parentId(code.getParentId())
                .icon(code.getIcon())
                .userId(code.getUserAccount().getId())
                .createdBy(code.getCreatedBy())
                .createdAt(code.getCreatedAt())
                .modifiedBy(code.getModifiedBy())
                .modifiedAt(code.getModifiedAt())
                .build();
    }

}
