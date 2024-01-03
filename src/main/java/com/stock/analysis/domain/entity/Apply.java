package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Apply extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String companyName;

    @Setter
    @Column(length = 100)
    private String longitude;

    @Setter
    @Column(length = 100)
    private String latitude;

    @Setter
    @Column(length = 50)
    private String platform;

    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime appliedAt;
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jobOpeningDate;
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime jobCloseDate;

    @Setter
    private Boolean isApplied;

    @Setter
    private String status;      // 상태를 어떤식으로 표현할지 생각을 좀 해야할듯...
}
