package com.stock.analysis.domain.entity;

import com.stock.analysis.domain.AuditingFields;
import com.stock.analysis.dto.request.CodeRequestDto;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Entity
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_removed = false")
public class Code extends AuditingFields {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    private Long parentId;

    @ToString.Exclude
    @OneToMany(mappedBy = "parentId")
    private Set<Code> children = new LinkedHashSet<>();

    public Code(Long id, String name, Long parentId, Set<Code> children) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = children;
    }

    public static Code of(Long id, String name, Long parentId, Set<Code> children) {
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
        setName(dto.name());;
        setParentId(dto.parentId());
    }
}

