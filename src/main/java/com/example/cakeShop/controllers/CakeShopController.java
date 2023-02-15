package com.example.cakeShop.controllers;

import com.example.cakeShop.models.CakeShop;
import com.example.cakeShop.repositories.CakeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/cakeShops")
    public ResponseEntity<CakeShop> postCakeShops(@RequestBody CakeShop cakeShop){
        cakeShopRepository.save(cakeShop);
        return new ResponseEntity<>(cakeShop, HttpStatus.CREATED);
    }

    @PutMapping(value = "/cakeShops/{id}")
    public ResponseEntity<CakeShop> putCakeShop(@RequestBody CakeShop cakeShop, @PathVariable Long id){
        CakeShop cakeShopUpdate = cakeShopRepository.findById(id).get();
        cakeShopUpdate.setName(cakeShop.getName());
        cakeShopUpdate.setCakes(cakeShop.getCakes());
        return new ResponseEntity<>(cakeShopUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cakeShops/{id}")
    public ResponseEntity<Long> deleteCakeShop(@PathVariable Long id){
        cakeShopRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
