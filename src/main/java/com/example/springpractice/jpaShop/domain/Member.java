package com.example.springpractice.jpaShop.domain;

import com.example.springpractice.jpaShop.domain.enums.RoleType;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
public class Member {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "ROLE_TYPE")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Column(name = "DESCRIPTION")
    @Lob
    private String description;
}
