package com.stock.analysis.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
}
