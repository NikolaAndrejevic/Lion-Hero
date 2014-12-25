/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

import com.badlogic.gdx.Screen;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author Nikola
 */
public class gameScreen implements Screen {
    final main game;
        Texture background;
        Texture playerOne;
        Texture a10;
        Texture a20;
        Texture a30;
        Texture a11;
        Texture a21;
        Texture a31;
        Texture b10;
        Texture b11;
        Texture c00;
        Texture c01;
        Texture c10;
        Texture c20;
        Texture c30;
        Texture c11;
        Texture c21;
        Texture c31;
        Texture d10;
        Texture d20;
        Texture d30;
        Texture d11;
        Texture d21;
        Texture d31;
        Texture PowerUp1;
        Texture PowerUp2;
        Texture PowerUp3;
        Texture PowerUp4;
        Texture PowerUp5;
        PowerUp Power;
        Texture PowerUpType;
        int powerRand;
        long lastPowerUpTime;
        long timeBetweenPowerUps;
        long startTime;
        int activePower;
        long timeActivePower;
        int obstSpeed;
        int bombs;
        long gameTime;
        long score;
        long bonusScore;
        int playerSpeed;
        boolean invincible;
        int[] lastSpawned;
        boolean spacePressed;
        Music music = Gdx.audio.newMusic(Gdx.files.internal("Blackhole.mp3"));
        
        Sound sonic = Gdx.audio.newSound(Gdx.files.internal("sonic.mp3"));
        Sound bomb = Gdx.audio.newSound(Gdx.files.internal("Bomb.mp3"));
        Sound reload = Gdx.audio.newSound(Gdx.files.internal("Reload.mp3"));
        Sound lost = Gdx.audio.newSound(Gdx.files.internal("Lost.mp3"));
        Sound slow = Gdx.audio.newSound(Gdx.files.internal("Slow.mp3"));
        Sound invinc = Gdx.audio.newSound(Gdx.files.internal("invincibility.mp3"));
        
        OrthographicCamera camera;
	Player one;
        long lastObstacleTime;
        Array<ObstacleType1> obstacles1;
        Array<ObstacleType2> obstacles2;
        Array<ObstacleType3> obstacles3;
        Array<ObstacleType4> obstacles4;
        boolean flag = false;
        boolean flag2 = false;
        ShapeRenderer shapeRenderer;
        
    
        public gameScreen(final main gam) {
        this.game = gam;
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 1200, 840);
            
            background = new Texture(Gdx.files.internal("m16.jpg"));
            
            a10 = new Texture(Gdx.files.internal("0firstx.png"));
            a20 = new Texture(Gdx.files.internal("0secondx.png"));
            a30 = new Texture(Gdx.files.internal("0thirdx.png"));
            a11 = new Texture(Gdx.files.internal("0firsty.png"));
            a21 = new Texture(Gdx.files.internal("0secondy.png"));
            a31 = new Texture(Gdx.files.internal("0thirdy.png"));
            b10 = new Texture(Gdx.files.internal("1firstx.png"));
            b11 = new Texture(Gdx.files.internal("1firsty.png"));
            c00 = new Texture(Gdx.files.internal("2defaultx.png"));
            c01 = new Texture(Gdx.files.internal("2defaulty.png"));
            c10 = new Texture(Gdx.files.internal("2firstx.png"));
            c20 = new Texture(Gdx.files.internal("2secondx.png"));
            c30 = new Texture(Gdx.files.internal("2thirdx.png"));
            c11 = new Texture(Gdx.files.internal("2firsty.png"));
            c21 = new Texture(Gdx.files.internal("2secondy.png"));
            c31 = new Texture(Gdx.files.internal("2thirdy.png"));
            d10 = new Texture(Gdx.files.internal("3firstx.png"));
            d20 = new Texture(Gdx.files.internal("3secondx.png"));
            d30 = new Texture(Gdx.files.internal("3thirdx.png"));
            d11 = new Texture(Gdx.files.internal("3firsty.png"));
            d21 = new Texture(Gdx.files.internal("3secondy.png"));
            d31 = new Texture(Gdx.files.internal("3thirdy.png"));
            
            playerOne = new Texture(Gdx.files.internal("circle.png"));
            timeBetweenPowerUps = 15000;
            
            one = new Player(485,345);
            obstacles1 = new Array<ObstacleType1>();
            obstacles2 = new Array<ObstacleType2>();
            obstacles3 = new Array<ObstacleType3>();
            obstacles4 = new Array<ObstacleType4>();
            PowerUp1 = new Texture(Gdx.files.internal("speed.png"));
            PowerUp2 = new Texture(Gdx.files.internal("slow.png"));
            PowerUp3 = new Texture(Gdx.files.internal("bomb.png"));
            PowerUp4 = new Texture(Gdx.files.internal("invincibility.png"));
            PowerUp5 = new Texture(Gdx.files.internal("star.png"));
            startTime = TimeUtils.millis();
            activePower = 0;
            invincible = false;
            obstSpeed = 70;
            bombs = 1;
            score = 0;
            bonusScore = 0;
            lastSpawned = new int[2];
            lastSpawned[0] = -1;
            lastSpawned[1] = -1;
            lastPowerUpTime = TimeUtils.millis();
            music.play();
            music.setVolume(0.1f);
    }
    
        @Override
	public void render (float delta) {
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            playerSpeed = 200 + (int)(gameTime)/1000;
            if (activePower != 1)
                one.changeSpeed(playerSpeed);
           
            if (activePower != 2)
                obstSpeed = 100 + (int)(gameTime)/350;
            
            score = (int)(gameTime * gameTime /20000) + bonusScore;
            drawGame();
            generateObstacle();
            generatePowerUp();
            movePlayer();
            boundries();
            moveObstacle();
           
            if(TimeUtils.millis() - lastPowerUpTime > 5000)
            {
                Power = null;
            }

            collidesPower();
            flag = collides();
            if (flag && !invincible) {
               lost.play(.7f);
               game.finalTime = (int)((TimeUtils.millis() - startTime)/1000);
               music.stop();
               music.dispose();
               game.setScreen(new endScreen(game,score));
               dispose();
            }

            if(!Gdx.input.isKeyPressed(Keys.SPACE))
                spacePressed = false;
            if (Gdx.input.isKeyPressed(Keys.SPACE)&&!spacePressed&&bombs>0)
            {   
                bomb.play(0.8f);
                bombs = bombs - 1;
                obstacles1.clear();
                obstacles2.clear();
                obstacles3.clear();
                obstacles4.clear();
                spacePressed = true;
               }
              
            if ((TimeUtils.millis() - timeActivePower)> 7000)
            {
            activePower = 0;
            timeActivePower = 0;
            one.changeSpeed(playerSpeed);
            
            invincible = false;
            }
        }
        
        public void drawGame()
        {
             game.batch.begin();
            game.batch.draw(background, 0, 0);
            game.batch.draw(playerOne, one.xCord, one.yCord);
            Texture toDraw = c00;
            for(ObstacleType1 item: obstacles1) 
            {
                if (item.orientation == 0)
                {
                    if (item.number == 0)
                        toDraw = a10;
                    else if (item.number == 1)
                        toDraw = a20;
                    else if (item.number == 2) 
                        toDraw = a30;
                    game.batch.draw(toDraw, item.xCord, item.yCord);
                }    
                else
                {
                    if (item.number == 0)
                        toDraw = a11;
                    else if (item.number == 1)
                        toDraw = a21;
                    else if (item.number == 2)
                        toDraw = a31;
                    game.batch.draw(toDraw, item.xCord, item.yCord);
                }
            }
            for(ObstacleType2 item: obstacles2) 
            {
                if (item.orientation == 0)
                {
                    game.batch.draw(b10, item.xCord, item.yCord);
                    game.batch.draw(b10, item.xCord2, item.yCord2);
                }    
                else
                {
                    game.batch.draw(b11, item.xCord, item.yCord);
                    game.batch.draw(b11, item.xCord2, item.yCord2);
                }
            }
            for(ObstacleType3 item: obstacles3) 
            {
                if (item.orientation == 0)
                {
                    if (item.number == 0)
                        toDraw = c10;
                    else if (item.number == 1)
                        toDraw = c20;
                    else if (item.number ==2)
                        toDraw = c30;
                    game.batch.draw(c00, item.xCord, item.yCord);
                    game.batch.draw(c00, item.xCord3, item.yCord3);
                    game.batch.draw(toDraw, item.xCord2, item.yCord2);
                }    
                else
                {
                    if (item.number == 0)
                        toDraw = c11;
                    else if (item.number == 1)
                        toDraw = c21;
                    else if (item.number == 2)
                        toDraw = c31;
                    game.batch.draw(c01, item.xCord, item.yCord);
                    game.batch.draw(c01, item.xCord3, item.yCord3);
                    game.batch.draw(toDraw, item.xCord2, item.yCord2);
                }
            }
            for(ObstacleType4 item: obstacles4) 
            {
                 Texture toDraw2 = c00;
                if (item.orientation == 0)
                {
                    if (item.type1 == 0)
                        toDraw = d10;
                    else if (item.type1 == 1)
                        toDraw = d20;
                    else if (item.type1 ==2)
                        toDraw = d30;
                    
                    if (item.type2 == 0)
                        toDraw2 = d10;
                    else if (item.type2 == 1)
                        toDraw2 = d20;
                    else if (item.type2 ==2)
                        toDraw2 = d30;
                    game.batch.draw(toDraw, item.xCord, item.yCord);
                    game.batch.draw(toDraw2, item.xCord2, item.yCord2);
                }    
                else
                {
                    if (item.type1 == 0)
                        toDraw = d11;
                    else if (item.type1 == 1)
                        toDraw = d21;
                    else if (item.type1 ==2)
                        toDraw = d31;
                    
                    if (item.type2 == 0)
                        toDraw2 = d11;
                    else if (item.type2 == 1)
                        toDraw2 = d21;
                    else if (item.type2 ==2)
                        toDraw2 = d31;
                    game.batch.draw(toDraw, item.xCord, item.yCord);
                    game.batch.draw(toDraw2, item.xCord2, item.yCord2);
                }
            }
            
            if(Power != null)
            {
                if (Power.type == 1)
                  PowerUpType = PowerUp1;
               
                else if (Power.type ==2)
                  PowerUpType = PowerUp2;
                
                else if (Power.type ==3)
                  PowerUpType = PowerUp3;
               
                else if(Power.type==4)
                  PowerUpType = PowerUp4;
                
                else if(Power.type==5)
                  PowerUpType = PowerUp5;
                
                game.batch.draw(PowerUpType, Power.xCord, Power.yCord);
            }
            gameTime = TimeUtils.millis() - startTime;
            game.font.draw(game.batch,"Score:" + score , 20, 20);
            game.font.draw(game.batch,"Bombs:" + bombs , 600, 20);
           
            if(activePower==4)
                game.font.draw(game.batch,"Invincibility:" +(int)(7-(TimeUtils.millis() - timeActivePower)/1000) , 900, 20);
            
            if(activePower==1)
                game.font.draw(game.batch,"Super Speed:" +(int)(7-(TimeUtils.millis() - timeActivePower)/1000) , 900, 20);
            
            if(activePower==2)
                game.font.draw(game.batch,"Slow Mode:" +(int)(7-(TimeUtils.millis() - timeActivePower)/1000) , 900, 20);
                
            game.batch.end();
        }
        
        public void generatePowerUp()
        {
            if( TimeUtils.millis() - lastPowerUpTime > timeBetweenPowerUps)
            {
                Power = new PowerUp();
                lastPowerUpTime = TimeUtils.millis();
                timeBetweenPowerUps = MathUtils.random(8000, 15000);
            }
        }
     
        public void boundries()
        {
            if(one.xCord < 0) one.xCord = 0;
            if(one.yCord < 0) one.yCord = 0;
            if(one.xCord > 1123) one.xCord = 1123;
            if(one.yCord > 753) one.yCord = 753;
        }
        
        public void movePlayer()
        {
            boolean left = Gdx.input.isKeyPressed(Keys.A);
            boolean right = Gdx.input.isKeyPressed(Keys.D);
            boolean down = Gdx.input.isKeyPressed(Keys.S);
            boolean up = Gdx.input.isKeyPressed(Keys.W);
                
            if ((left ^ right) && (up ^ down))
                {
                if(left) one.changeCordX((int)(-1*one.speed* Gdx.graphics.getDeltaTime()));
                if(right) one.changeCordX((int)(one.speed* Gdx.graphics.getDeltaTime()));
                if(down) one.changeCordY((int)(-1*one.speed* Gdx.graphics.getDeltaTime()));
                if(up) one.changeCordY((int)(one.speed* Gdx.graphics.getDeltaTime()));
               }
            else
                {
                if(left) one.changeCordX((int)(-1*one.speedod * Gdx.graphics.getDeltaTime()));
                if(right) one.changeCordX((int)(one.speedod* Gdx.graphics.getDeltaTime()));
                if(down) one.changeCordY((int)(-1*one.speedod* Gdx.graphics.getDeltaTime()));
                if(up) one.changeCordY((int)(one.speedod* Gdx.graphics.getDeltaTime()));
                }
        }
        
        public void moveObstacle()
        {
            Iterator<ObstacleType1> iter1 = obstacles1.iterator();
            while(iter1.hasNext()) {
                
                ObstacleType1 item = iter1.next();
                if (item.orientation == 0 )
                {  
                    item.changeX((int)(obstSpeed* Gdx.graphics.getDeltaTime()));
                    if(item.rxcord() > 1230||item.rxcord() < -30) iter1.remove();
                }
                else
                { 
                    item.changeY((int)(obstSpeed * Gdx.graphics.getDeltaTime()));
                    if(item.rycord() > 865 || item.rycord() < -30) iter1.remove();
                }
            }
            
             Iterator<ObstacleType2> iter2 = obstacles2.iterator();
             while(iter2.hasNext()) {
                
                ObstacleType2 item = iter2.next();
                if (item.orientation == 0 )
                {
                    item.changeX((int)(obstSpeed* Gdx.graphics.getDeltaTime()));
                    if(item.rxcord() > 1230||item.rxcord() < -30) iter2.remove();
                }
                else
                {  
                    item.changeY((int)(obstSpeed* Gdx.graphics.getDeltaTime()));
                    if(item.rycord() > 870 || item.rycord() < -30) iter2.remove();
                }
            }
            
            Iterator<ObstacleType3> iter3 = obstacles3.iterator();
            while(iter3.hasNext()) {      
                ObstacleType3 item = iter3.next();
                if (item.orientation == 0 )
                {
                    item.changeX((int)(obstSpeed* Gdx.graphics.getDeltaTime()));
                    if(item.rxcord() > 1230||item.rxcord() < -30) iter3.remove();
                }
                else
                {
                    item.changeY((int)(obstSpeed * Gdx.graphics.getDeltaTime()));
                    if(item.rycord() > 870 || item.rycord() < -30) iter3.remove();
                }
            }
            
            Iterator<ObstacleType4> iter4 = obstacles4.iterator();
            while(iter4.hasNext()) {      
                ObstacleType4 item = iter4.next();
                if (item.orientation == 0 )
                {
                    item.changeX((int)(obstSpeed* Gdx.graphics.getDeltaTime()));
                    if(item.rxcord() > 1230||item.rxcord() < -30) iter4.remove();
                }
                else
                {
                    item.changeY((int)(obstSpeed * Gdx.graphics.getDeltaTime()));
                    if(item.rycord() > 870 || item.rycord() < -30) iter4.remove();
                }
            }
        }
        
        public void generateObstacle()
        {
            if(TimeUtils.millis() - lastObstacleTime > (275000/obstSpeed))
            {
            int direction = MathUtils.random(0, 1);
            int orientation = MathUtils.random(0, 1);
            if ((lastSpawned[0] == direction) && (lastSpawned[1] == orientation))
            {
                int rand = MathUtils.random(0, 1);
                if (rand == 0)
                {
                    if (direction == 0)
                        direction = 1;
                    else
                        direction = 0;
                }
                else
                {
                    if (orientation == 0)
                        orientation = 1;
                    else
                        orientation = 0;
                }
            }
            lastSpawned[0] = direction;
            lastSpawned[1] = orientation;
            int type = MathUtils.random(0, 3); 
            if (type == 0)
            {
               ObstacleType1 newOne = new ObstacleType1(direction, orientation);
               obstacles1.add(newOne);
            }
            else if(type == 1)
            {
                ObstacleType2 newOne = new ObstacleType2(direction, orientation);
                obstacles2.add(newOne);
            }
            else if (type == 2)
            {
                ObstacleType3 newOne = new ObstacleType3(direction, orientation);
                obstacles3.add(newOne);
            }
            else
            {
                ObstacleType4 newOne = new ObstacleType4(direction, orientation);
                obstacles4.add(newOne);
            }
            
            lastObstacleTime = TimeUtils.millis(); 
            }
        }
        
        public boolean collides() {
            Iterator<ObstacleType1> iter = obstacles1.iterator();
            while(iter.hasNext()) {
                
                ObstacleType1 item = iter.next();
                if (Intersector.overlaps(one.playerBox, item.hitBox1))  
                    return true;
               }
            
            Iterator<ObstacleType2> iter2 = obstacles2.iterator();
            while(iter2.hasNext()) {  
                ObstacleType2 item = iter2.next();
                if (Intersector.overlaps(one.playerBox, item.hitBox1)||Intersector.overlaps(one.playerBox, item.hitBox2))  
                    return true;
               }
            
            Iterator<ObstacleType3> iter3 = obstacles3.iterator();
            while(iter3.hasNext()) {             
                ObstacleType3 item = iter3.next();
                if (Intersector.overlaps(one.playerBox, item.hitBox1)||Intersector.overlaps(one.playerBox, item.hitBox2)||Intersector.overlaps(one.playerBox, item.hitBox3))  
                    return true;
               }
            Iterator<ObstacleType4> iter4 = obstacles4.iterator();
            while(iter4.hasNext()) {             
                ObstacleType4 item = iter4.next();
                if (Intersector.overlaps(one.playerBox, item.hitBox1)||Intersector.overlaps(one.playerBox, item.hitBox2)) 
                    return true;
               }
            return false;
        }
        
        public boolean collidesPower() {
            if (Power != null)
            {
                if (Intersector.overlaps(one.playerBox, Power.hitBox))  
                {
                    if (Power.type == 1){
                        one.changeSpeed(400);
                        sonic.play(1.0f);
                    }
                    else if (Power.type == 2){
                        obstSpeed = (int)(obstSpeed/2);
                        slow.play(1.0f);
                    }
                    else if (Power.type ==3){
                        reload.play(0.8f);
                        bombs++;
                    }
                    else if (Power.type == 4){

                        invinc.play(0.8f);
                        invincible = true;
                        
                    }
                    else if (Power.type == 5)
                        bonusScore += gameTime * gameTime /20000;

                    activePower = Power.type;
                    timeActivePower =  TimeUtils.millis();
                    Power = null;
                    return true;
                }
            }
            return false;
        }
        
         @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
       
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


