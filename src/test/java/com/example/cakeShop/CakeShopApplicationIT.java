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
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

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
    public void getAllCakeShops() {

//        Use ParametizedTypeReference to tell restTemplate that we expect a list of CakeShop objects as a response
        ResponseEntity<List<CakeShop>> response = restTemplate.exchange("/cakeShops", HttpMethod.GET, null, new ParameterizedTypeReference<List<CakeShop>>() {});

        // Assert that the HTTP status code is 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert that the response body is not null and contains at least one CakeShop object
        List<CakeShop> cakeShops = response.getBody();
        assertNotNull(cakeShops);
        assertTrue(cakeShops.size() > 0);

    }

//    @Test
//    public void getAllCakeShopsAndConvertToJsonStringFormat() throws JsonProcessingException {

////      Arrange
//        CakeShop patisserieCakes = new CakeShop("Patisserie Cakes");
//
//        Cake redVelvetCake = new Cake("Red Velvet Cake", 6.00, patisserieCakes);
//        redVelvetCake.setId(1L);
//
//        Cake oreoCheeseCake = new Cake("Oreo Cheesecake", 6.00, patisserieCakes);
//        oreoCheeseCake.setId(2L);
//
//        Cake victoriaSponge = new Cake("Victoria Sponge Cake", 6.00, patisserieCakes);
//        victoriaSponge.setId(3L);
//
//        Cake blackForestCake = new Cake("Black Forest Cake", 5.90, patisserieCakes);
//        blackForestCake.setId(4L);
//
//        Cake tiramisu = new Cake("Tiramisu", 5.00, patisserieCakes);
//        tiramisu.setId(5L);
//
//        List <Cake> cakeList = Arrays.asList(redVelvetCake, oreoCheeseCake, victoriaSponge, blackForestCake, tiramisu);
//
//        patisserieCakes.setCakes(cakeList);
//        patisserieCakes.setId(1L);
//
//        CakeShop maya = new CakeShop("Maya desserts");
//        maya.setId(2L);
//
//        Cake tiramisuCake = new Cake("Tiramisu Cake", 32.95, maya);
//        tiramisuCake.setId(6L);
//        List <Cake> mayaCakeList = Arrays.asList(tiramisuCake);
//        maya.setCakes(mayaCakeList);
//
//        List<CakeShop> cakeShopList = Arrays.asList(patisserieCakes, maya);
////        cakeShopRepository.saveAll(cakeShopList);
////      Convert JSON into JSON string in this instance as easier to test full object as an arraylist
//        ObjectMapper objectMapper = new ObjectMapper();
//        String cakeShop = objectMapper.writeValueAsString(cakeShopList);
//
////      Act
//        ResponseEntity<String> response = restTemplate.getForEntity("/cakeShops", String.class);
//
////      Assert
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isEqualTo(cakeShop);
//    }

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

    @Test
    public void addNewCakeShop() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CakeShop newCakeShop = new CakeShop("New CakeShop");

        HttpEntity<CakeShop> request = new HttpEntity<>(newCakeShop, headers);
        ResponseEntity<CakeShop> response = restTemplate.postForEntity("/cakeShops", request, CakeShop.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());

    }


}
