package com.kurenai.byril_test.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kurenai.byril_test.Auxiliary.ExtendedScreen;
import com.kurenai.byril_test.ScreenManager;
import com.kurenai.byril_test.UI.Buttons;
import static com.kurenai.byril_test.Auxiliary.Constants.WORLD_WIDTH;
import static com.kurenai.byril_test.Auxiliary.Constants.WORLD_HEIGHT;

public class MainMenuScreen extends ExtendedScreen {

    private TextButton playButton;

    public MainMenuScreen(final ScreenManager screenManager, SpriteBatch batch, Viewport viewport){
        super(screenManager,batch,viewport);

        playButton = Buttons.getTextButton();
        playButton.setX(WORLD_WIDTH / 2f - playButton.getWidth() / 2f);
        playButton.setY(WORLD_HEIGHT / 2f - playButton.getHeight() / 2f);
        playButton.setText("PLAY");
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenManager.setGameScreen();
            }
        });
        stage.addActor(playButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 0);
        stage.draw();
        stage.act();
    }

}
