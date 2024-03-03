package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.dto.request.CodeRequestDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
@SQLDelete(sql = "UPDATE code SET is_removed = true WHERE id = ?")
@Where(clause = "is_removed = false")
public class Code extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Column(length = 100)
    private String icon;

    @Setter
    private Long parentId;

    @ToString.Exclude
    @OneToMany(mappedBy = "parentId")
    private List<Code> children = new ArrayList<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Setter
    @Column(columnDefinition = "bit DEFAULT false NOT NULL COMMENT '삭제여부'")
    private Boolean isRemoved;

    public Code(Long id, String name, Long parentId, List<Code> children) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = children;
    }

    public static Code of(Long id, String name, Long parentId, List<Code> children) {
        return new Code(id, name, parentId, children);
    }

    public static Code of(Long id, String name, Long parentId) {
        return new Code(id, name, parentId, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Code code)) return false;
        return id != null && id.equals(code.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void updateCode(CodeRequestDto dto) {
        setName(dto.name());
        setParentId(dto.parentId());
    }
}

