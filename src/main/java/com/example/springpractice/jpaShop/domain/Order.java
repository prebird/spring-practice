package com.example.springpractice.jpaShop.domain;

import com.example.springpractice.jpaShop.domain.enums.OrderStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Member member;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
