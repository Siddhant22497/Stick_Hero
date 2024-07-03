package com.example.stick_hero;

import java.util.HashMap;
import java.util.Map;

public class Playerinfo {                                                   //Flyweight, name should be unique
    private int totalcheries=0;
    private int highestpoint=0;
    private static Map<String,Playerinfo> instance=new HashMap<String,Playerinfo>();
    private Playerinfo()
    {

    }
    public static Playerinfo getPlayerinfo(String name)
    {
        if(!instance.containsKey(name))
        {
          instance.put(name,new Playerinfo());
        }
        return instance.get(name);
    }
    public static  void settingPlayerinfo(String name,Playerinfo playerinfo)
    {
        if(instance.containsKey(name))
        {
            instance.put(name,playerinfo);
        }
        else
        {
            System.out.println("Name does not exit");
        }
    }



    public int getTotalCheries() {
        return totalcheries;
    }

    public void setTotal_cheries(int totalcheries) {
        this.totalcheries = totalcheries;
    }

    public int getHighestpoint() {
        return highestpoint;
    }

    public void setHighestpoint(int highestpoint) {
        this.highestpoint = highestpoint;
    }
}
