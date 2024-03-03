package com.stock.analysis.dto.response;

import com.stock.analysis.application.code.repository.CodeRepository;
import com.stock.analysis.domain.entity.Code;
import com.stock.analysis.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String createdAt;
    private String createdBy;
    private String modifiedAt;
    private String modifiedBy;

    public static CodeResponseDto of(Long id,
                                     String name,
                                     Long parentId,
                                     List<CodeResponseDto> children,
                                     String createdAt,
                                     String createdBy,
                                     String modifiedAt,
                                     String modifiedBy
    ) {
        return new CodeResponseDto(id, name, parentId, children, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static CodeResponseDto from(Code code) {
        return CodeResponseDto.of(
                code.getId(),
                code.getName(),
                code.getParentId(),
                code.getChildren().stream()
                        .map(CodeResponseDto::from).toList(),
                Utils.ConvertDate(code.getCreatedAt()),
                code.getCreatedBy(),
                Utils.ConvertDate(code.getModifiedAt()),
                code.getModifiedBy()
        );
    }
}
