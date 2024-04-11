package com.stock.analysis.application.apply.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchApplyDto {

    private String companyName;         // 회사명
    private String passValue;           // 합격유무
    private String isAppliedValue;      // 지원유무

    // 공고 종료일 기준 3일전 5일전 10일전 이런거 있었으면 좋겠다.
    private LocalDate jobCloseStartDate;
    private LocalDate jobCloseEndDate;

}
