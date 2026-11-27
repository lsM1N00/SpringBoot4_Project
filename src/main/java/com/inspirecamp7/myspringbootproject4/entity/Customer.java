package com.inspirecamp7.myspringbootproject4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")  // 테이블명 지정
@DynamicUpdate
@Getter @Setter
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // TABLE, SEQUENCE,IDENTITY,UUID,AUTO;
    private Long id;

    @Column(unique = true, nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt = LocalDateTime.now();


    //private String customerAddress;

}
