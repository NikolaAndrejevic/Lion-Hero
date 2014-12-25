/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
/**
 *
 * @author Nikola
 */
public class mainMenuScreen implements Screen {
     final main game;

    OrthographicCamera camera;
    Texture background;
    Music music = Gdx.audio.newMusic(Gdx.files.internal("Parousia.mp3"));
    public mainMenuScreen(final main gam) {
        game = gam;
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 840);
        background = new Texture(Gdx.files.internal("ScreenMain.png"));
        music.play();
}
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new gameScreen(game));
            music.stop();
            music.dispose();
            dispose();
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
