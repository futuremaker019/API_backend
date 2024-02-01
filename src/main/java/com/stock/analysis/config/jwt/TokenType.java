package com.stock.analysis.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TokenType {

    ACCESS_TOKEN("accessToken"),
    REFRESH_TOKEN("refreshToken")

    ;

    @Getter private final String value;

}
