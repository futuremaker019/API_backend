package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.domain.contant.ApplyType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
public class Apply extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 100)
    private String companyName;

    @Setter @Column(length = 1000)
    private String companyLocation;

    @Setter @Column(length = 50)
    private String platform;

    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appliedAt;
    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jobOpeningDate;
    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jobCloseDate;

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
            String platform,
            LocalDateTime appliedAt,
            LocalDateTime jobOpeningDate,
            LocalDateTime jobCloseDate,
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
                .appliedAt(appliedAt)
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
