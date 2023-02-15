package com.example.cakeShop.components;

import com.example.cakeShop.models.Cake;
import com.example.cakeShop.models.CakeShop;
import com.example.cakeShop.repositories.CakeRepository;
import com.example.cakeShop.repositories.CakeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    CakeRepository cakeRepository;

    @Autowired
    CakeShopRepository cakeShopRepository;

    public DataLoader(){}

    public void run(ApplicationArguments args){
        CakeShop patisserieCakes = new CakeShop("Patisserie Cakes");
        cakeShopRepository.save(patisserieCakes);

        Cake redVelvetCake = new Cake("Red Velvet Cake", 6.00, patisserieCakes);
        cakeRepository.save(redVelvetCake);

        Cake oreoCheeseCake = new Cake("Oreo Cheesecake", 6.00, patisserieCakes);
        cakeRepository.save(oreoCheeseCake);

        Cake victoriaSponge = new Cake("Victoria Sponge Cake", 6.00, patisserieCakes);
        cakeRepository.save(victoriaSponge);

        Cake blackForestCake = new Cake("Black Forest Cake", 5.90, patisserieCakes);
        cakeRepository.save(blackForestCake);

        Cake tiramisu = new Cake("Tiramisu", 5.00, patisserieCakes);
        cakeRepository.save(tiramisu);
    }
}
