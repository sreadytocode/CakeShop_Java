package com.example.cakeShop.controllers;

import com.example.cakeShop.models.CakeShop;
import com.example.cakeShop.repositories.CakeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CakeShopController {
    @Autowired
    CakeShopRepository cakeShopRepository;

    @GetMapping(value = "/cakeShops")
    public ResponseEntity<List<CakeShop>> getAllCakeShops(){
        return new ResponseEntity<>(cakeShopRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/cakeShops/{id}")
    public ResponseEntity getCakeShop(@PathVariable Long id) {
        return new ResponseEntity<>(cakeShopRepository.findById(id), HttpStatus.OK);
    }


}
