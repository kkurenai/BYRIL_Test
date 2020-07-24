package com.kurenai.byril_test.Auxiliary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kurenai.byril_test.ScreenManager;


public abstract class ExtendedScreen implements Screen {

    protected ScreenManager screenManager;
    protected Stage stage;

    public ExtendedScreen(ScreenManager screenManager, SpriteBatch batch, Viewport viewport){
        this.screenManager = screenManager;
        stage = new Stage(viewport,batch);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose(){
        stage.dispose();
    }
}

