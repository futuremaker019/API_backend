package com.stock.analysis.application.apply.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.stock.analysis.application.contentFile.dto.ContentFileResponseDto;
import com.stock.analysis.application.process.dto.ApplyProcessResponseDto;
import com.stock.analysis.application.useraccount.dto.UserAccountResponseDto;
import com.stock.analysis.domain.contant.ApplyEnum;
import com.stock.analysis.domain.entity.ApplyProcess;
import com.stock.analysis.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private String headhunterCompany;

    private ApplyEnum.IsApplied isApplied;
    private String isAppliedValue;
    private ApplyEnum.ApplyType applyType;
    private String applyTypeValue;

    private boolean pass;
    private String passValue;
    private boolean passResume;
    private String passResumeValue;

    private Long processCodeId;

    private UserAccountResponseDto userAccount;
    private Boolean isRemoved;

    private List<ContentFileResponseDto> attachments = new ArrayList<>();
    private List<ApplyProcessResponseDto> processDtos = new ArrayList<>();

    public void setProcesses(List<ApplyProcess> processes) {
        this.processDtos = processes.stream().map(ApplyResponseDto::from).toList();
    }

    public static ApplyProcessResponseDto from(ApplyProcess process) {
        return ApplyProcessResponseDto.builder()
                .processCodeId(process.getId().getProcessCodeId())
                .name(process.getName())
                .orders(process.getOrders())
                .build();
    }

    @QueryProjection
    public ApplyResponseDto(Long id, String companyName, String companyLocation,
                            Long platform, LocalDate applyDate, LocalDate jobOpeningDate,
                            LocalDate jobCloseDate, ApplyEnum.IsApplied isApplied, ApplyEnum.ApplyType applyType,
                            boolean pass, boolean passResume, Long processCodeId, String headhunterCompany,
                            List<ContentFileResponseDto> attachments
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
        this.isAppliedValue = isApplied.getKor();
        this.applyTypeValue = applyType.getKor();
        this.pass = pass;
        this.passResume = passResume;
        this.processCodeId = processCodeId;
        this.attachments = Utils.getList(attachments);
        this.headhunterCompany = headhunterCompany;
    }

    @QueryProjection
    public ApplyResponseDto(Long id, String companyName, String companyLocation,
                            LocalDate applyDate, LocalDate jobOpeningDate,
                            LocalDate jobCloseDate, ApplyEnum.IsApplied isApplied, ApplyEnum.ApplyType applyType,
                            boolean pass, boolean passResume
    ) {
        this.id = id;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.applyDate = applyDate;
        this.jobOpeningDate = jobOpeningDate;
        this.jobCloseDate = jobCloseDate;
        this.isAppliedValue = isApplied.getKor();
        this.applyTypeValue = applyType.getKor();
        this.passValue = pass ? ApplyEnum.PassType.PASS.getValue() : ApplyEnum.PassType.NOT_PASS.getValue();
        this.passResumeValue = passResume ? ApplyEnum.PassResumeType.PASS_RESUME.getValue()
                : ApplyEnum.PassResumeType.NOT_PASS_RESUME.getValue();
    }
}
