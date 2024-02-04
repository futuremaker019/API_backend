package com.stock.analysis.domain.contant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CodeType {

    PLATFORM("플랫폼", 1L),
    RECRUITMENT_PROCEDURE("채용전형", 2L),

    ;

    @Getter
    public final String codeName;
    @Getter
    public final Long codeId;

}
