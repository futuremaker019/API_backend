package com.stock.analysis.application.process.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.domain.SuperDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApplyProcessResponseDto extends SuperDto {

    private Long processCodeId;
    private String name;
    private int orders;

    @QueryProjection
    public ApplyProcessResponseDto(Long processCodeId, String name, int orders) {
        this.processCodeId = processCodeId;
        this.name = name;
        this.orders = orders;
    }
}
