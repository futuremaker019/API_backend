package com.stock.analysis.domain.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "process", indexes = {
        @Index(name = "idx_applyId_userId", columnList = "applyId, userId")
})
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) @Comment("지원 id")
    private Long applyId;
    @Setter @Column(nullable = false) @Comment("사용자 id")
    private Long userId;
    @Setter @Column(nullable = false) @Comment("채용전형 코드 id")
    private Long processCodeId;     // 통계를 위한 id, 채용전형 id가 지워지거나 수정된다면?

    @Setter @Column(length = 100) @Comment("채용전형명")
    private String name;
    @Setter @Comment("순서")  // 진행단계 만들어야 하기때문에
    private int orders;

}
