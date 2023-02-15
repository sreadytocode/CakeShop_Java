package com.example.cakeShop.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cakeshops")
public class CakeShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private String name;

    @JsonIgnoreProperties({"cakeShop"})
    @OneToMany(mappedBy = "cakeShop", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Cake> cakes;

    public CakeShop(String name) {
        this.name = name;
        this.cakes = new ArrayList<>();
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

    public List<Cake> getCakes() {
        return cakes;
    }

    public void setCakes(List<Cake> cakes) {
        this.cakes = cakes;
    }

    public int getCakesCount(){
        return cakes.size();
    }
}
