package com.example.cakeShop.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"cakes"})
    @ManyToOne
    @JoinColumn(name = "cakeShop_id", nullable = false)
    private CakeShop cakeShop;

    public Cake(String name, Double price, CakeShop cakeShop) {
        this.name = name;
        this.price = price;
        this.cakeShop = cakeShop;
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

    public CakeShop getCakeShop() {
        return cakeShop;
    }

    public void setCakeShop(CakeShop cakeShop) {
        this.cakeShop = cakeShop;
    }
}
