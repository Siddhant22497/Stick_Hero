package com.example.stick_hero;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomescreenTest {

    @Test
    public void test1() {
        Homescreen h1=new Homescreen();
        int k=ScreenController.count=0;
        assertEquals(k,0);
    }

    @Test
    public void test2() {
        Homescreen h1=Homescreen.getHomescreen();
        assertNotNull(h1);
    }

    @Test
    public void test3() {
        assertNull(Homescreen.stagehome);
    }
}