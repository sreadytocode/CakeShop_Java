package com.example.cakeShop;

import com.example.cakeShop.models.Cake;
import com.example.cakeShop.models.CakeShop;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CakeTest {
    Cake cake;

    CakeShop cakeShop;

    @Before
    public void before(){
        cakeShop = new CakeShop("Patisserie Cakes");
        cake = new Cake("Red Velvet Cake", 6.00, cakeShop);
    }

    @Test
    public void doesCakeHaveAName(){
        assertEquals("Red Velvet Cake", cake.getName());
    }

    @Test
    public void doesCakeHaveAPrice(){
        assertEquals(6.00, cake.getPrice(), 0.0);
    }

    @Test
    public void doesCakeHaveAShop(){
        assertEquals(cakeShop, cake.getCakeShop());
    }

}
