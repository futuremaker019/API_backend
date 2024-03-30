package com.stock.analysis.application.useraccount.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountResponseDto {

    private Long id;
    private String userId;
    private String email;
    private String nickname;


}
