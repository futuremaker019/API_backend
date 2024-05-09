package com.stock.analysis.domain.entity;


import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyProcess {

    @EmbeddedId
    private ApplyProcessComplexIds id;

    @Setter @Column(length = 100) @Comment("채용전형명")
    private String name;
    @Setter @Comment("순서")
    private int orders;

}
