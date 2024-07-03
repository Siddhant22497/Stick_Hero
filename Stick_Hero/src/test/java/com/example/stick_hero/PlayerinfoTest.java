package com.example.stick_hero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerinfoTest {
    @Test
    public void test1()
    {
        Playerinfo playerinfo1=Playerinfo.getPlayerinfo("player1");
        assertNotNull(playerinfo1);

    }

    @Test
    public void test2()
    {
        Playerinfo playerinfo1=Playerinfo.getPlayerinfo("player1");
        assertEquals(0,playerinfo1.getHighestpoint());
        assertEquals(0,playerinfo1.getTotalCheries());

    }
}