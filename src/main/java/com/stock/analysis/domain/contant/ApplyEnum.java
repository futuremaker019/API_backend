package com.stock.analysis.domain.contant;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ApplyEnum {

    @Getter
    @AllArgsConstructor
    public enum ApplyType {
        APPLIED_BY_SELF("직접지원"),
        APPLIED_BY_HEADHUNTER("헤드헌터지원"),
        NONE("미정")
        ;

        private final String kor;
    }

    @Getter
    @AllArgsConstructor
    public enum ApplyStatus {
        APPLIED("지원"),
        NOT_APPLIED("미지원"),
        NONE("미정")
        ;

        private final String kor;
    }

    @Getter
    @AllArgsConstructor
    public enum PassType {
        PASSED("합격"),
        NOT_PASSED( "불합격"),
        NONE("미정")
        ;

        private final String kor;
    }

    @Getter
    @AllArgsConstructor
    public enum PassResumeType {
        PASS_RESUME( "서류통과"),
        NOT_PASS_RESUME( "서류미통과"),
        NONE("미정")
        ;

        private final String kor;
    }



}
