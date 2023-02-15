package com.example.cakeShop;

import com.example.cakeShop.models.Cake;
import com.example.cakeShop.models.CakeShop;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CakeShopTest {
    CakeShop cakeShop;

    Cake cake;

    @Before
    public void before(){
        cakeShop = new CakeShop("Patisserie Cakes");
        cake = new Cake("Red Velvet Cake", 6.00, cakeShop);
    }

    @Test
    public void doesCakeShopHaveAName(){
        assertEquals("Patisserie Cakes", cakeShop.getName());
    }

}
