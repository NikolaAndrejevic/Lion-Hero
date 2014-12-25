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
public class ObstacleType1{

    int direction;
    int orientation;
    final int type = 0;
    
    int number;
    int xCord;
    int yCord;
    final int firstx = 200;
    final int secondx = 275;
    final int thirdx = 350;
    final int firsty = 400;
    final int secondy = 500;
    final int thirdy = 600;
    int chosen;
    Rectangle hitBox1;
    public ObstacleType1(int direction, int orientation)
    {
        this.direction = direction;
        this.orientation = orientation;
        number = MathUtils.random(0,2);
        if (orientation == 0)
        {
            if (number == 0)
                chosen = firstx;
            else if (number == 1)
                chosen = secondx;
            else if (number ==2)
                chosen = thirdx;
        }
        else
        {
            if (number == 0)
                chosen = firsty;
            else if (number == 1)
                chosen = secondy;
            else if (number ==2)
                chosen = thirdy;
        }
            
        if (orientation == 0)
        {
            yCord = MathUtils.random(0 , 840 - chosen);
            if (direction == 0)
            {
                xCord = -20;
            }
            else
            {
                xCord = 1200;
            }
            hitBox1 = new Rectangle(xCord, yCord, 20, chosen);
        }
        else
        {
            xCord = MathUtils.random(0 , 1200 - chosen);
            
            if (direction == 0)
            {
                yCord = 0;
            }
            else
            {
                yCord = 840;
            }
            hitBox1 = new Rectangle(xCord, yCord, chosen, 20);
        }
    }
    
    public void changeX(int x)
    {
        if (direction == 0)
            xCord += x;
        else
            xCord -=x;
        hitBox1.setX(xCord);
    }
    
    public void changeY(int y)
    {
        if (direction == 0)
        yCord += y;
        else
            yCord -=y;
        hitBox1.setY(yCord);
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
