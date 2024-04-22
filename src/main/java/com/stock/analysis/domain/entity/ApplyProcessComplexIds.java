package com.stock.analysis.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplyProcessComplexIds implements Serializable {

    private static final long serialVersionUID = 99L;

    @Column(name = "apply_id")
    private Long applyId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "process_code_id")
    private Long processCodeId;     // 통계를 위한 id, 채용전형 id가 지워지거나 수정된다면?

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof ApplyProcessComplexIds ids)) return false;
        return Objects.equals(applyId, ids.applyId)
                && Objects.equals(userId, ids.userId)
                && Objects.equals(processCodeId, ids.processCodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applyId, userId, processCodeId);
    }

}
