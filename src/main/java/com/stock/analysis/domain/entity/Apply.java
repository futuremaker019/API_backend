package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.ApplyType;
import lombok.*;
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

    @Setter @Column(nullable = false, length = 100)
    private String companyName;

    @Setter @Column(length = 1000)
    private String companyLocation;

    @Setter
    private Long platform;

    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applyDate;
    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobOpeningDate;
    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate jobCloseDate;

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '지원유무: 지원, 지원안함, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyType isApplied;

    @Setter
    @Column(columnDefinition = "varchar(50) DEFAULT 'NONE' COMMENT '지원종류: 직접지원, 헤드헌터, 미정'")
    @Enumerated(value = EnumType.STRING)
    private ApplyType applyType;

    @Setter
    private boolean pass;
    @Setter
    private boolean passResume;

    @Setter
    private Long processCodeId;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount userAccount;

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
