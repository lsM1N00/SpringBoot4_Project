package com.inspirecamp7.myspringbootproject4.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")  // 테이블명 지정
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)  // TABLE, SEQUENCE,IDENTITY,UUID,AUTO;
    private Long id;

    @Column(unique = true, nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String customerName;

    @Column()
    LocalDateTime createdAt = LocalDateTime.now();

}
