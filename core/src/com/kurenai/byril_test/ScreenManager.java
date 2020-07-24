package com.kurenai.byril_test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kurenai.byril_test.Screens.GameScreen;
import com.kurenai.byril_test.Screens.MainMenuScreen;
import static com.kurenai.byril_test.Auxiliary.Constants.WORLD_HEIGHT;
import static com.kurenai.byril_test.Auxiliary.Constants.WORLD_WIDTH;

public class ScreenManager extends Game {

	private SpriteBatch batch;
	private GameScreen gameScreen;
	private MainMenuScreen mainMenuScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this, batch, new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
		mainMenuScreen = new MainMenuScreen(this, batch, new FitViewport(WORLD_WIDTH,WORLD_HEIGHT));
		setMainMenuScreen();
	}

	public void setGameScreen(){
		setScreen(gameScreen);
	}

	public void setMainMenuScreen(){
		setScreen(mainMenuScreen);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
