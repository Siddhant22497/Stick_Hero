package com.example.stick_hero;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Gamemechanics{                                                                         //singleton design
    private static Gamemechanics gamemechanics=null;
    public static Gamemechanics getGamemechanics()
    {
        if(gamemechanics==null)
        {
            gamemechanics=new Gamemechanics();
        }
        return gamemechanics;

    }

    public float getRandomaxis(float d1)
    {
        Random r1=new Random();
        float d2=r1.nextFloat(d1);
        return d2;
    }
    private Gamemechanics()
    {

    }


    public int randomgenerate(int x)
    {
        Random r=new Random();
        return  r.nextInt(x)+30;
    }
    public int randomgeneratedistance(int x)
    {
        Random r=new Random();
        return  r.nextInt(x)+20+400;
    }
    public int calculatedistance(int first,int second)
    {
        int m=first-second;
        m=Math.abs(m);
        return m;
    }





}
