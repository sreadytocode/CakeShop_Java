package com.example.cakeShop;

import com.example.cakeShop.models.Cake;
import com.example.cakeShop.models.CakeShop;
import com.example.cakeShop.repositories.CakeRepository;
import com.example.cakeShop.repositories.CakeShopRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.ResponseEntity.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CakeShopApplicationIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CakeShopRepository cakeShopRepository;

    @Autowired
    CakeRepository cakeRepository;

    @Test
    public void getAllCakeShops() throws JsonProcessingException {

//        Arrange
        CakeShop patisserieCakes = new CakeShop("Patisserie Cakes");

        Cake redVelvetCake = new Cake("Red Velvet Cake", 6.00, patisserieCakes);
        redVelvetCake.setId(1L);
        Cake oreoCheeseCake = new Cake("Oreo Cheesecake", 6.00, patisserieCakes);
        oreoCheeseCake.setId(2L);
        Cake victoriaSponge = new Cake("Victoria Sponge Cake", 6.00, patisserieCakes);
        victoriaSponge.setId(3L);
        Cake blackForestCake = new Cake("Black Forest Cake", 5.90, patisserieCakes);
        blackForestCake.setId(4L);
        Cake tiramisu = new Cake("Tiramisu", 5.00, patisserieCakes);
        tiramisu.setId(5L);
        List <Cake> cakeList = Arrays.asList(redVelvetCake, oreoCheeseCake, victoriaSponge, blackForestCake, tiramisu);
        patisserieCakes.setCakes(cakeList);
        patisserieCakes.setId(1L);
        List<CakeShop> cakeShopList = Arrays.asList(patisserieCakes);

//      Convert JSON into JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String cakes = objectMapper.writeValueAsString(cakeShopList);

//      Act
        ResponseEntity<String> response = restTemplate.getForEntity("/cakeShops", String.class, patisserieCakes.getId());

//      Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(cakes);
    }

    @Test
    public void getCakeShop(){
        CakeShop patisserieCakes = new CakeShop("Patisserie Cakes");
        cakeShopRepository.save(patisserieCakes);

        ResponseEntity<CakeShop> response = restTemplate.getForEntity("/cakeShops/{id}", CakeShop.class, patisserieCakes.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(patisserieCakes.getId());
        assertThat(response.getBody().getName()).isEqualTo(patisserieCakes.getName());
        assertThat(response.getBody().getCakes()).isEqualTo(patisserieCakes.getCakes());
    }


}
