package com.example.cakeShop.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cakes")
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private String name;

    @Column
    private Double price;

    public Cake(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Cake(){}

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
