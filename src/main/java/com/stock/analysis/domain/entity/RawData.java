package com.stock.analysis.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString(callSuper = true)     // auditing 의 데이터를 찍기 위해 callSuper를 붙임
@Table(indexes = {
        @Index(columnList = "year"),
        @Index(columnList = "month"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Integer year;

    @Setter
    @Column(nullable = false)
    private Integer month;

    @Setter
    @Column(precision = 6, scale = 5)
    private BigDecimal close;

    @Setter
    @Column(precision = 3, scale = 3)
    private BigDecimal value;       // value에 - (마이너스)가 가능한가...

}
