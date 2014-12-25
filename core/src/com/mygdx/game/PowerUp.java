/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

/**
 *
 * @author Nikola
 */

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Circle;
public class PowerUp {
    int xCord;
    int yCord;
    int type;
    String typeStr;
    int radius = 25;
    Circle hitBox;
    
    
    public PowerUp (){
        xCord = MathUtils.random(100, 900);
        yCord = MathUtils.random(100, 600);        
        type = MathUtils.random(1, 5); //Player Speed, Game Slow, Invicibility
        hitBox = new Circle(xCord + radius ,yCord + radius ,radius);          
    }   
}
