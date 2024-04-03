package com.stock.analysis.application.apply.dto;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchApplyDto {

    private String companyName;
    private Boolean pass;

    // 지원유무
    private String isAppliedValue;

    // 공고 종료일 기준 3일전 5일전 10일전 이런거 있었으면 좋겠다.


}
