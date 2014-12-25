/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Administrator
 */
public class ObstacleType3{
    int direction;
    int orientation;
    final int type = 2;
    
    int width;
    int width2;
    int xCord;
    int yCord;
    int xCord2;
    int xCord3;
    int yCord2;
    int yCord3;
    int number;
    
    int lengthxside = 400;
    int lengthyside = 800;
    
    int lengthxone = 100;
    int lengthxtwo = 150;
    int lengthxthree = 200;
    
    int lengthyone = 100;
    int lengthytwo = 200;
    int lengthythree = 300;
    
    int chosen;
     
    Rectangle hitBox1;
    Rectangle hitBox2;
    Rectangle hitBox3;
    public ObstacleType3(int direction, int orientation)
    {
        this.direction = direction;
        this.orientation = orientation;
        width = MathUtils.random(120,250);   
        width2 = MathUtils.random(120,250);  
        number = MathUtils.random(0,2);
        
        if (orientation == 0)
        {
           if (number == 0)
               chosen = lengthxone;
           else if (number == 1)
               chosen = lengthxtwo;
           else if (number == 2)
               chosen = lengthxthree;
        }
        else
        {
            if (number == 0)
               chosen = lengthyone;
            else if (number == 1)
               chosen = lengthytwo;
            else if (number == 2)
               chosen = lengthythree;
        }
        
        
        if (orientation == 0)
        {
            yCord = MathUtils.random(-335 , 840 - chosen - width - width2 - lengthxside - 65);
            yCord2 = yCord + lengthxside + width;
            yCord3 = yCord2 + chosen + width2;
            if (direction == 0)
            {
                xCord = -20;
                xCord2 = -20;
                xCord3 = -20;
            }
            else
            {
                xCord = 1200;
                xCord2 = 1200;
                xCord3 = 1200;

            }
            hitBox1 = new Rectangle(xCord, yCord, 20, lengthxside);
            hitBox2 = new Rectangle(xCord2, yCord2, 20, chosen);
            hitBox3 = new Rectangle(xCord3, yCord3, 20, lengthxside);
        }
        else
        {
            xCord = MathUtils.random(-601 , 1200 - chosen - width - width2 - lengthyside - 200);
            xCord2 = xCord + lengthyside + width;
            xCord3 = xCord2 + chosen + width2;
            if (direction == 0)
            {
                yCord = 0;
                yCord2 = 0;
                yCord3 = 0;
            }
            else
            {
                yCord = 840;
                yCord2 = 840;
                yCord3 = 840;
            }
            hitBox1 = new Rectangle(xCord, yCord, lengthyside, 20);
            hitBox2 = new Rectangle(xCord2, yCord2, chosen, 20);
            hitBox3 = new Rectangle(xCord3, yCord3, lengthyside, 20);
        }
    }
    public void changeX(int x)
    {
        if (direction == 0)
        {
        xCord += x;
        xCord2 += x;
        xCord3 += x;
        }
        else
        {
        xCord -= x;
        xCord2 -= x;
        xCord3 -= x;
        }
        hitBox1.setX(xCord);
        hitBox2.setX(xCord2);
        hitBox3.setX(xCord3);
    }
    
    public void changeY(int y)
    {
        if (direction == 0)
        {
        yCord += y;
        yCord2 += y;
        yCord3 += y;
        }
        else
        {
        yCord -= y;
        yCord2 -= y;
        yCord3 -= y;
        }
        hitBox1.setY(yCord);
        hitBox2.setY(yCord2);
        hitBox3.setY(yCord3);
    }
    
    public int rxcord()
    {
        return xCord;
    }
    
    public int rycord()
    {
        return yCord;
    }
}
