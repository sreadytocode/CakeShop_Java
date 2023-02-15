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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
