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
 * @author Ilya
 */
public class ObstacleType4{
    int direction;
    int orientation;
    final int type = 3;
    
    int width;
    int xCord;
    int yCord;
    int xCord2;
    int yCord2;
    int lengthxone = 100;
    int lengthxtwo = 150;
    int lengthxthree = 200;
    int lengthyone = 100;
    int lengthytwo = 200;
    int lengthythree = 300;
    
    int chosen1;
    int chosen2;
    
    int type1;
    int type2;
    
    Rectangle hitBox1;
    Rectangle hitBox2;
    public ObstacleType4(int direction, int orientation)
    {
        this.direction = direction;
        this.orientation = orientation;      
        if (orientation == 0)
        { 
            type1 = MathUtils.random(0,2);
            if(type1 == 0)
                chosen1 = lengthxone;
            else if(type1 == 1)
                chosen1 = lengthxtwo;
            else
                chosen1 = lengthxthree;
            
            type2 = MathUtils.random(0,2);
            if(type2 == 0)
                chosen2 = lengthxone;
            else if(type2 == 1)
                chosen2 = lengthxtwo;
            else
                chosen2 = lengthxthree;
            
            width = MathUtils.random(125,840 - chosen1 -chosen2 - 240);
            yCord = MathUtils.random(119 , 840 - chosen1 - width - chosen2 -120);
            yCord2 = yCord + chosen1 + width;
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
            hitBox1 = new Rectangle(xCord, yCord, 20, chosen1);
            hitBox2 = new Rectangle(xCord2, yCord2, 20, chosen2);
        }
        else
        {
            type1 = MathUtils.random(0,2);
            if(type1 == 0)
                chosen1 = lengthyone;
            else if(type1 == 1)
                chosen1 = lengthytwo;
            else
                chosen1 = lengthythree;
            
            type2 = MathUtils.random(0,2);
            if(type2 == 0)
                chosen2 = lengthyone;
            else if(type2 == 1)
                chosen2 = lengthytwo;
            else
                chosen2 = lengthythree;
            
            width = MathUtils.random(125,1200 - chosen1 -chosen2 - 350);
            xCord = MathUtils.random(174 , 1200 - chosen1 - width - chosen2 -175);
            xCord2 = xCord + chosen1 + width;
            if (direction == 0)
            {
                yCord = -20;
                yCord2 = -20;
            }
            else
            {
                yCord = 840;
                yCord2 = 840;
            }
            hitBox1 = new Rectangle(xCord, yCord, chosen1,20);
            hitBox2 = new Rectangle(xCord2, yCord2,chosen2,20);
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