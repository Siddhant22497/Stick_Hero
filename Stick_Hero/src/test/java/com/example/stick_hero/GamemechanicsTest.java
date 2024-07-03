package com.example.stick_hero;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamemechanicsTest {
    @Test
    public void test1()
    {
        Gamemechanics gamemechanics=Gamemechanics.getGamemechanics();
        assertNotNull(gamemechanics);

    }

}