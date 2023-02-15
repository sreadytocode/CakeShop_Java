package com.example.cakeShop.repositories;

import com.example.cakeShop.models.CakeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CakeShopRepository extends JpaRepository<CakeShop, Long> {
    List<CakeShop> findByName(String name);
}
