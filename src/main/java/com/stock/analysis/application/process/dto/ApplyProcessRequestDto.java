package com.stock.analysis.application.process.dto;

import com.stock.analysis.domain.entity.ApplyProcess;
import com.stock.analysis.domain.entity.ApplyProcessComplexIds;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyProcessRequestDto {

    private Long applyId;
    private Long userId;
    private Long processCodeId;

    private String name;
    private int orders;

    public ApplyProcess to(Long applyId, Long userId, int orders) {
        return ApplyProcess.builder()
                .id(new ApplyProcessComplexIds(applyId, userId, processCodeId))
                .name(name)
                .orders(orders)
                .build();
    }
}
