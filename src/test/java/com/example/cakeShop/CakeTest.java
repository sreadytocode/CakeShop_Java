package com.example.cakeShop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CakeTest {
    Cake cake;

    @Before
    public void before(){
        cake = new Cake("Red Velvet Cake");
    }

    @Test
    public void doesCakeHaveAName(){
        assertEquals("Red Velvet Cake", cake.getName());
    }

}
