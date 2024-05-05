package com.stock.analysis.domain.entity;

import com.stock.analysis.application.apply.dto.ApplyRequestDto;
import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.ApplyEnum;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE apply SET is_removed = true WHERE id = ?")
@Where(clause = "is_removed = false")
public class Apply extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 100) @Comment("회사명")
    private String companyName;             // 회사명

    @Setter @Column(length = 100) @Comment("회사위치")
    private String companyLocation;         // 회사위치

    @Setter @Comment("플랫폼")
    private Long platform;                  // 플랫폼

    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd") @Comment("지원일자")
    private LocalDate applyDate;            // 지원일자
    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd") @Comment("공고시작일")
    private LocalDate jobOpeningDate;       // 공고시작일
    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd") @Comment("공고종료일")
    private LocalDate jobCloseDate;         // 공고종료일

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '지원유무: 지원, 지원안함, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyEnum.ApplyStatus applyStatus;            // 지원유무

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '지원종류: 직접지원, 헤드헌터, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyEnum.ApplyType applyType;            // 지원종류

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '합격여부: 합격, 불합격, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyEnum.PassType passType;                   // 합격여부

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '이력서 합격여부: 서류통과, 서류미통과, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyEnum.PassResumeType passResumeType;             // 이력서 합격여부

    @Setter @Comment("채용전형 codeId")
    private Long processCodeId;             // 채용전형 id

    @Setter @Comment("헤드헌터 회사")
    private String headhunterCompany;

    @Setter @Comment("사용자 id")
    private Long userId;        // 사용자 id

    @Setter
    @Column(columnDefinition = "bit DEFAULT false NOT NULL COMMENT '삭제여부'")
    private Boolean isRemoved;

    @PrePersist
    void applyCreated() {
        isRemoved = false;
    }

    public static Apply of(
            String companyName,
            String companyLocation,
            Long platform,
            LocalDate applyDate,
            LocalDate jobOpeningDate,
            LocalDate jobCloseDate,
            ApplyEnum.ApplyStatus applyStatus,
            ApplyEnum.ApplyType applyType,
            ApplyEnum.PassType passType,
            ApplyEnum.PassResumeType passResumeType,
            Long userId
    ) {
        return Apply.builder()
                .companyName(companyName)
                .companyLocation(companyLocation)
                .platform(platform)
                .applyDate(applyDate)
                .jobOpeningDate(jobOpeningDate)
                .jobCloseDate(jobCloseDate)
                .applyStatus(applyStatus)
                .applyType(applyType)
                .passType(passType)
                .passResumeType(passResumeType)
                .userId(userId)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apply apply)) return false;
        return id != null && id.equals(apply.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void update(ApplyRequestDto requestDto) {
        setCompanyName(requestDto.getCompanyName());
        setCompanyLocation(requestDto.getCompanyLocation());
        setJobOpeningDate(requestDto.getJobOpeningDate());
        setJobCloseDate(requestDto.getJobCloseDate());
        setApplyDate(requestDto.getApplyDate());
        setApplyStatus(requestDto.getApplyStatus());
        setApplyType(requestDto.getApplyType());
        setPlatform(requestDto.getPlatform());
        setPassType(requestDto.getPassType());
        setPassResumeType(requestDto.getPassResumeType());
        setHeadhunterCompany(requestDto.getHeadhunterCompany());
        setProcessCodeId(requestDto.getProcessCodeId());
    }
}
