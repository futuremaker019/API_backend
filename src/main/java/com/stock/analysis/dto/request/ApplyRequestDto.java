package com.stock.analysis.dto.request;

import com.stock.analysis.domain.contant.ApplyType;
import com.stock.analysis.domain.entity.Apply;
import com.stock.analysis.domain.entity.UserAccount;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequestDto {
    private Long id;
    private String companyName;
    private String companyLocation;
    private String platform;
    private LocalDateTime appliedAt;
    private LocalDateTime jobOpeningDate;
    private LocalDateTime jobCloseDate;
    private ApplyType isApplied;
    private ApplyType applyType;
    private boolean pass;
    private boolean passResume;
    private Long processCodeId;

    private UserAccount userAccount;

//    public static Apply toEntity(UserAccount userAccount) {
//        return Apply.
//    }
}
