package com.example.cakeShop;

import com.example.cakeShop.models.Cake;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CakeTest {
    Cake cake;

    @Before
    public void before(){
        cake = new Cake("Red Velvet Cake", 6.00);
    }

    @Test
    public void doesCakeHaveAName(){
        assertEquals("Red Velvet Cake", cake.getName());
    }

    @Test
    public void doesCakeHaveAPrice(){
        assertEquals(6.00, cake.getPrice(), 0.0);
    }

}
