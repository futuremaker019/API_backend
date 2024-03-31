package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.ApplyType;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE apply SET is_removed = true WHERE id = ?")
@Where(clause = "is_removed = false")
public class Apply extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 100) @Comment("회사명")
    private String companyName;             // 회사명

    @Setter @Column(length = 1000) @Comment("회사위치")
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
    private ApplyType isApplied;            // 지원유무

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '지원종류: 직접지원, 헤드헌터, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyType applyType;            // 지원종류

    @Setter @Comment("합격여부")
    private boolean pass;                   // 합격여부
    @Setter @Comment("이력서 합격여부")
    private boolean passResume;             // 이력서 합격여부

    @Setter @Comment("채용전형 codeId")
    private Long processCodeId;             // 채용전형 id

    @Comment("사용자 id")
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount userAccount;        // 사용자 id

    @Setter
    @Column(columnDefinition = "bit DEFAULT false NOT NULL COMMENT '삭제여부'")
    private Boolean isRemoved;

    public static Apply of(
            String companyName,
            String companyLocation,
            Long platform,
            LocalDate applyDate,
            LocalDate jobOpeningDate,
            LocalDate jobCloseDate,
            ApplyType isApplied,
            ApplyType applyType,
            boolean pass,
            boolean passResume,
            UserAccount userAccount
    ) {
        return Apply.builder()
                .companyName(companyName)
                .companyLocation(companyLocation)
                .platform(platform)
                .applyDate(applyDate)
                .jobOpeningDate(jobOpeningDate)
                .jobCloseDate(jobCloseDate)
                .isApplied(isApplied)
                .applyType(applyType)
                .pass(pass)
                .passResume(passResume)
                .userAccount(userAccount)
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

}
