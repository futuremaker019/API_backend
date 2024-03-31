package com.stock.analysis.application.apply.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.application.useraccount.dto.UserAccountResponseDto;
import com.stock.analysis.domain.contant.ApplyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyResponseDto {

    private Long id;
    private String companyName;
    private String companyLocation;
    private Long platform;
    private LocalDate applyDate;
    private LocalDate jobOpeningDate;
    private LocalDate jobCloseDate;

    private ApplyType isApplied;
    private ApplyType applyType;

    private boolean pass;
    private boolean passResume;

    private Long processCodeId;

    private UserAccountResponseDto userAccount;
    private Boolean isRemoved;

    @QueryProjection
    public ApplyResponseDto(Long id, String companyName, String companyLocation,
                            Long platform, LocalDate applyDate, LocalDate jobOpeningDate,
                            LocalDate jobCloseDate, ApplyType isApplied, ApplyType applyType,
                            boolean pass, boolean passResume, Long processCodeId
    ) {
       this.id = id;
       this.companyLocation = companyLocation;
       this.companyName = companyName;
       this.platform = platform;
       this.applyDate = applyDate;
       this.jobOpeningDate = jobOpeningDate;
       this.jobCloseDate = jobCloseDate;
       this.isApplied = isApplied;
       this.applyType = applyType;
       this.pass = pass;
       this.passResume = passResume;
       this.processCodeId = processCodeId;
    }

    @QueryProjection
    public ApplyResponseDto(Long id, String companyName, String companyLocation,
                            LocalDate applyDate, LocalDate jobOpeningDate,
                            LocalDate jobCloseDate, ApplyType isApplied, ApplyType applyType,
                            boolean pass, boolean passResume
    ) {
        this.id = id;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.applyDate = applyDate;
        this.jobOpeningDate = jobOpeningDate;
        this.jobCloseDate = jobCloseDate;
        this.isApplied = isApplied;
        this.applyType = applyType;
        this.pass = pass;
        this.passResume = passResume;
    }

}
