package com.example.springpractice.jpaShop.domain;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Item item;
    @ManyToOne
    private Order order;
    private int orderPrice;
    private int count;
}
