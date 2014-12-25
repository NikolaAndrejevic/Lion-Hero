/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;



import com.badlogic.gdx.math.Circle;
/**
 *
 * @author Administrator
 */
public class Player {
    int xCord;
    int yCord;
    int speed;
    int speedod;
    int radius = 32;
    Circle playerBox;
    
    
    public Player (int x, int y){
        xCord = x;
        yCord = y;
        speed = 200;
        speedod = (int)(1.41 * speed);
        playerBox = new Circle(x + radius + 5,y + radius + 6,radius);          
    }
    
    public void changeCordX (int x)
    {
        xCord += x;
        playerBox.setX(xCord + radius + 5);
    }
    
     public void changeCordY (int y)
    {
        yCord += y;
        playerBox.setY(yCord + radius + 6);
    }
    
    public void changeSpeed(int s)
    {
        speed = s;
        speedod = (int)(1.41 * speed);
    }
   
}
