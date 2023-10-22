package com.ezybytes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;
}
