package com.example.cakeShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cakeshops")
public class CakeShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private String name;

    public CakeShop(String name) {
        this.name = name;
    }

    public CakeShop() {}
}
