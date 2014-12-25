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
public class ObstacleType2{
    int direction;
    int orientation;
    final int type = 1;
    
    int width;
    int xCord;
    int yCord;
    int xCord2;
    int yCord2;
    int lengthx = 600;
    int lengthy = 900;
    Rectangle hitBox1;
    Rectangle hitBox2;
    public ObstacleType2(int direction, int orientation)
    {
        this.direction = direction;
        this.orientation = orientation;
        width = MathUtils.random(175,400);       
        if (orientation == 0)
        {
            yCord = MathUtils.random(-450 , 840 - lengthx - width -150);
            yCord2 = yCord + lengthx + width;
            if (direction == 0)
            {
                xCord = -20;
                xCord2 = -20;
            }
            else
            {
                xCord = 1200;
                xCord2 = 1200;
            }
            hitBox1 = new Rectangle(xCord, yCord, 20, lengthx);
            hitBox2 = new Rectangle(xCord2, yCord2, 20, lengthx);
        }
        else
        {
            xCord = MathUtils.random( -700 , 1200 - lengthy - width - 200);
            xCord2 = xCord + lengthy + width;
            if (direction == 0)
            {
                yCord = 0;
                yCord2 = 0;
            }
            else
            {
                yCord = 840;
                yCord2 = 840;

            }
            hitBox1 = new Rectangle(xCord, yCord, lengthy, 20);
            hitBox2 = new Rectangle(xCord2, yCord2, lengthy, 20);
        }
    }
    public void changeX(int x)
    {
        if (direction == 0)
        {
        xCord += x;
        xCord2 += x;
        }
        else
        {
        xCord -= x;
        xCord2 -= x;
        }
        hitBox1.setX(xCord);
        hitBox2.setX(xCord2);
    }
    
    public void changeY(int y)
    {
        if (direction == 0)
        {
        yCord += y;
        yCord2 += y;
        }
        else
        {
        yCord -= y;
        yCord2 -= y;
        }   
        hitBox1.setY(yCord);
        hitBox2.setY(yCord2);
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


