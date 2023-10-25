package com.stock.analysis.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@ToString(callSuper = true)     // auditing 의 데이터를 찍기 위해 callSuper를 붙임
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdBy"),
        @Index(columnList = "createdAt")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RawData {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(precision = 6, scale = 10)
    private BigDecimal close;

    @Column(precision = 3, scale = 3)
    private BigDecimal value;       // value에 - (마이너스)가 가능한가...

}
