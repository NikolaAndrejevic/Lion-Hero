/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import java.net.*;
import java.io.*;
import java.util.*;


 /*
 * @author Nikola
 */
public class endScreen implements Screen {
     final main game;
     long score;
     Texture background;
      TextButton.TextButtonStyle style= new TextButton.TextButtonStyle();
      

    OrthographicCamera camera;
    static String[] names = new String[5];
    static long[] scores = new long[5];
    
    long swap1;
    String swap2;
    
    //TextButton buttonMulti = new TextButton("Type Name Here",skin,"toggle"); 



    public endScreen(final main gam, long scor) {
        background = new Texture(Gdx.files.internal("m16.jpg"));
        
        score = scor;
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 840);
        getHighScores();
        sortHighScores();
        sendHighScores();
        System.out.println(Arrays.toString(names));
        System.out.println(Arrays.toString(scores));
        
}
    @Override
    public void render(float delta) {
        
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.font.draw(game.batch, "Your final score was " + score, 430, 100);
        game.font.draw(game.batch, "Your final time was " + game.finalTime + " seconds!", 430, 50);
        game.font.draw(game.batch, "Tap anywhere to play again!", 430, 480);
        
       
        
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new gameScreen(game));
            dispose();
        }
    }
    
    public void getHighScores(){
        try {
        URL url = new URL("ftp://u561320558:lionz1@lionhero.bugs3.com/highscores.txt");
        InputStream in = url.openStream();
        java.util.Scanner s = new java.util.Scanner(in).useDelimiter(" ");
        for(int i=0;i<5;i++)
        {
            scores[i] = s.nextInt();
        }
        for(int i=0;i<5;i++)
        {
            names[i] = s.next();
        }
}
        
    catch(java.io.IOException e){}
  
    }
    public void sendHighScores(){
        String send = "";
        for (int i = 0; i<5;i++)
            send = send + scores[i] + " ";
        for (int i = 0; i<5;i++)
            send = send + names[i] + " ";
        try{
        URL url = new URL("ftp://u561320558:lionz1@lionhero.bugs3.com/highscores.txt");
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStream out = conn.getOutputStream();
        PrintWriter writer = new PrintWriter(out);
        writer.write(send);
        writer.close();
        }
        catch(java.io.IOException e){}
  
    
    
    }
   
    
    public void sortHighScores(){
    if (score > scores[4]){
        scores[4] = score;
        names[4] = "GGWP";
        for (int c = 0; c < 4; c++) {
          for (int d = 0; d < 4 - c; d++) {
            if (scores[d] < scores[d+1]) 
            {
              swap1       = scores[d];
              swap2       = names[d];
              scores[d]   = scores[d+1];
              names[d]    = names[d+1];
              scores[d+1] = swap1;
              names[d+1]  = swap2;
            }
            System.out.println(Arrays.toString(scores));
          }
        }
        }
    
    
    }
    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
    
    
}
