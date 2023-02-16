package com.example.cakeShop;

import com.example.cakeShop.models.Cake;
import com.example.cakeShop.models.CakeShop;
import com.example.cakeShop.repositories.CakeRepository;
import com.example.cakeShop.repositories.CakeShopRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CakeApplicationIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CakeShopRepository cakeShopRepository;

    @Autowired
    CakeRepository cakeRepository;

    @Test
    public void getAllCakes(){
        ResponseEntity<List<Cake>> response = restTemplate.exchange("/cakes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Cake>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Cake> cakes = response.getBody();
        assertNotNull(cakes);
        assertTrue(cakes.size() > 0);
    }

    @Test
    public void getCake(){
//      Arrange
        CakeShop patisserieCakes = new CakeShop("Patisserie Cakes");
        cakeShopRepository.save(patisserieCakes);
        Cake redVelvetCake = new Cake("Red Velvet Cake", 6.00, patisserieCakes);
        cakeRepository.save(redVelvetCake);

//        Act
        ResponseEntity<Cake> response = restTemplate.getForEntity("/cakes/{id}", Cake.class, redVelvetCake.getId());

//        Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(redVelvetCake.getId());
        assertThat(response.getBody().getName()).isEqualTo(redVelvetCake.getName());
        assertThat(response.getBody().getPrice()).isEqualTo(redVelvetCake.getPrice());

//        Assert to get cakeshop attached to cake as otherwise getting an error message
        assertThat(response.getBody().getCakeShop().getId()).isEqualTo(patisserieCakes.getId());
        assertThat(response.getBody().getCakeShop().getName()).isEqualTo(patisserieCakes.getName());
    }

//    @Test
//    public void addNewCake(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        CakeShop patisserieCakes = new CakeShop("Patisserie Cakes");
//        cakeShopRepository.save(patisserieCakes);
//
//        Cake blueberryCake = new Cake("Blueberry cake", 6.00, patisserieCakes);
//
//        HttpEntity<Cake> request = new HttpEntity<>(blueberryCake, headers);
//        ResponseEntity<Cake> response = restTemplate.postForEntity("/cakes", request, Cake.class);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertNotNull(response.getBody().getId());
//
//    }
}
