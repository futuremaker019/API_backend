package com.stock.analysis.application.process.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ApplyProcessResponseDto {

    private String name;
    private int orders;

    @QueryProjection
    public ApplyProcessResponseDto(String name, int orders) {
        this.name = name;
        this.orders = orders;
    }
}
