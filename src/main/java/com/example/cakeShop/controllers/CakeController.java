package com.example.cakeShop.controllers;

import com.example.cakeShop.models.Cake;
import com.example.cakeShop.repositories.CakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CakeController {

    @Autowired
    CakeRepository cakeRepository;

    @GetMapping(value = "/cakes")
    public ResponseEntity<List<Cake>> getAllCakes(){
        return new ResponseEntity<>(cakeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/cakes/{id}")
    public ResponseEntity getCake(@PathVariable Long id){
        return new ResponseEntity(cakeRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/cakes")
    public ResponseEntity<Cake> postCakes(@RequestBody Cake cake){
        cakeRepository.save(cake);
        return new ResponseEntity<>(cake, HttpStatus.CREATED);
    }

    @PutMapping(value = "/cakes/{id}")
    public ResponseEntity<Cake> putCake(@RequestBody Cake cake, @PathVariable Long id){
        Cake cakeToUpdate = cakeRepository.findById(id).get();
        cakeToUpdate.setName(cake.getName());
        cakeToUpdate.setPrice(cake.getPrice());
        cakeToUpdate.setCakeShop(cake.getCakeShop());
        return new ResponseEntity<>(cakeToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/cakes/{id}")
    public ResponseEntity<Long> deleteCake(@PathVariable Long id){
        cakeRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
