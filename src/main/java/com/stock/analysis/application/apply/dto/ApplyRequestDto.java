package com.stock.analysis.application.apply.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.stock.analysis.application.process.dto.ApplyProcessRequestDto;
import com.stock.analysis.domain.contant.ApplyEnum;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequestDto {

    private Long id;

    @NotBlank(message = "회사이름을 입력해주세요.")
    private String companyName;
    private String companyLocation;
    private Long platform;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate applyDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate jobOpeningDate;

    @NotNull(message = "공고종료일을 입력해주세요.")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate jobCloseDate;

    private ApplyEnum.IsApplied isApplied;
    private ApplyEnum.ApplyType applyType;
    private boolean pass;
    private boolean passResume;

    private String headhunterCompany;

    private List<ApplyProcessRequestDto> processCodes = new ArrayList<>();

    @Min(value = 0, message = "채용전형을 선택해주세요.")
    private Long processCodeId;

    public Apply toEntity(UserAccount userAccount) {
        return Apply.builder()
                .companyName(companyName)
                .companyLocation(companyLocation)
                .platform(platform)
                .isApplied(isApplied)
                .applyType(applyType)
                .applyDate(applyDate)
                .jobOpeningDate(jobOpeningDate)
                .jobCloseDate(jobCloseDate)
                .pass(pass)
                .passResume(passResume)
                .headhunterCompany(headhunterCompany)
                .userAccount(userAccount)
                .processCodeId(processCodeId)
                .build();
    }
}
