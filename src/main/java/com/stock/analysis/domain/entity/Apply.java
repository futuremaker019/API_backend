package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.ApplyType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Column(name = "is_applied")
    @Enumerated(value = EnumType.STRING)
    private ApplyType isApplied;

    @Setter
    @Column(name = "apply_type")
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

}
