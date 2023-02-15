package com.example.cakeShop;

import com.example.cakeShop.models.CakeShop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CakeShopTest {
    CakeShop cakeShop;

    @Before
    public void before(){
        cakeShop = new CakeShop("Patisserie Cakes");
    }

    @Test
    public void CakeShopHasName(){
        assertEquals("Patisserie Cakes", cakeShop.getName());
    }
}
