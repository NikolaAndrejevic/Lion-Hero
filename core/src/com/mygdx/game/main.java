/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



/**
 *
 * @author Nikola
 */
public class main extends Game{
    
    public SpriteBatch batch;
    public BitmapFont font;
    public int finalTime;

     @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new mainMenuScreen(this));
    }

     @Override
    public void render() {
        super.render(); 
    }

     @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}

    

