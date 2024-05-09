package com.stock.analysis.application.apply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.time.LocalDateTime;
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

    private ApplyEnum.ApplyStatus applyStatus;
    private ApplyEnum.ApplyType applyType;
    private ApplyEnum.PassType passType;
    private ApplyEnum.PassResumeType passResumeType;

    private Long processCodeId;

    private UserAccountResponseDto userAccount;
    private Boolean isRemoved;

    private List<ContentFileResponseDto> attachments = new ArrayList<>();
    private List<ApplyProcessResponseDto> processDtos = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    private String createdBy;
    private String modifiedBy;

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
    public ApplyResponseDto(Long id, String companyName, String companyLocation, Long platform,
                            LocalDate applyDate, LocalDate jobOpeningDate, LocalDate jobCloseDate,
                            ApplyEnum.ApplyStatus applyStatus, ApplyEnum.ApplyType applyType,
                            ApplyEnum.PassType passType, ApplyEnum.PassResumeType passResumeType,
                            Long processCodeId, String headhunterCompany,
                            LocalDateTime createdAt, LocalDateTime modifiedAt,
                            String createdBy, String modifiedBy,
                            List<ContentFileResponseDto> attachments
    ) {
        this.id = id;
        this.companyLocation = companyLocation;
        this.companyName = companyName;
        this.platform = platform;
        this.applyDate = applyDate;
        this.jobOpeningDate = jobOpeningDate;
        this.jobCloseDate = jobCloseDate;
        this.applyStatus = applyStatus;
        this.applyType = applyType;
        this.passType = passType;
        this.passResumeType = passResumeType;
        this.processCodeId = processCodeId;
        this.attachments = Utils.getList(attachments);
        this.headhunterCompany = headhunterCompany;
        this.createdAt = createdAt; this.modifiedAt = modifiedAt;
        this.createdBy = createdBy; this.modifiedBy = modifiedBy;
    }

    @QueryProjection
    public ApplyResponseDto(Long id, String companyName, String companyLocation,
                            LocalDate applyDate, LocalDate jobOpeningDate, LocalDate jobCloseDate,
                            ApplyEnum.ApplyStatus applyStatus, ApplyEnum.ApplyType applyType,
                            ApplyEnum.PassType passType, ApplyEnum.PassResumeType passResumeType
    ) {
        this.id = id;
        this.companyName = companyName;
        this.companyLocation = companyLocation;
        this.applyDate = applyDate;
        this.jobOpeningDate = jobOpeningDate;
        this.jobCloseDate = jobCloseDate;
        this.applyStatus = applyStatus;
        this.applyType = applyType;
        this.passType = passType;
        this.passResumeType = passResumeType;
    }
}
