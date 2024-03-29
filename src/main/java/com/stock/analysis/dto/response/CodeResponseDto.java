package com.stock.analysis.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CodeResponseDto{

    private Long id;
    private String name;
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
                                     List<CodeResponseDto> children,
                                     String createdBy,
                                     LocalDateTime createdAt,
                                     String modifiedBy,
                                     LocalDateTime modifiedAt
                                     ) {
        return new CodeResponseDto(id, name, parentId, children, createdBy, modifiedBy, createdAt, modifiedAt);
    }

    public static CodeResponseDto from(Code code) {
        return CodeResponseDto.of(
                code.getId(),
                code.getName(),
                code.getParentId(),
                code.getChildren().stream()
                        .map(CodeResponseDto::from).toList(),
                code.getCreatedBy(),
                code.getCreatedAt(),
                code.getModifiedBy(),
                code.getModifiedAt()
        );
    }
}
