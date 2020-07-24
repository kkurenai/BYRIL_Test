package com.kurenai.byril_test.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kurenai.byril_test.Auxiliary.ExtendedScreen;
import com.kurenai.byril_test.Battleground.Map;
import com.kurenai.byril_test.ScreenManager;
import com.kurenai.byril_test.UI.Buttons;

import static com.kurenai.byril_test.Auxiliary.Constants.MAP_PATH;
import static com.kurenai.byril_test.Auxiliary.Constants.MAP_XRAY_SHADER_PATH;
import static com.kurenai.byril_test.Auxiliary.Constants.WORLD_HEIGHT;
import static com.kurenai.byril_test.Auxiliary.Constants.XRAY_WINDOW_IMAGE_PATH;

public class GameScreen extends ExtendedScreen {

    private Map map;
    private TextButton autoButton;
    private TextButton backButton;

    //Texture xRayWindow;
    //ShaderProgram shader;

    public GameScreen(final ScreenManager screenManager, SpriteBatch batch, Viewport viewport){
        super(screenManager,batch,viewport);
        map = new Map(new TextureRegion(new Texture(Gdx.files.internal(MAP_PATH))),
                    batch, 0,0);
        stage.addActor(map);
        
        autoButton = Buttons.getTextButton();
        autoButton.setX(map.getMapX() + map.getWidth() + 25);
        autoButton.setY(map.getHeight() / 2f - autoButton.getHeight() / 2f);
        autoButton.setText("Auto");
        autoButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                map.replaceShips();
            }
        });

        backButton = Buttons.getTextButton();
        backButton.setX(map.getMapX() + map.getWidth() + 25);
        backButton.setY(map.getHeight() / 2f - backButton.getHeight() * 2f);
        backButton.setText("Back");
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screenManager.setMainMenuScreen();
            }
        });

        stage.addActor(autoButton);
        stage.addActor(backButton);

       /* xRayWindow = new Texture(Gdx.files.internal(XRAY_WINDOW_IMAGE_PATH));
        // shader init
        ShaderProgram.pedantic = false;
        shader = new ShaderProgram(Gdx.files.internal(MAP_XRAY_SHADER_PATH + ".vsh"), Gdx.files.internal(MAP_XRAY_SHADER_PATH + ".fsh"));
        shader.begin();
        shader.setUniformf("u_TextureWidth", xRayWindow.getWidth());
        shader.setUniformf("u_TextureHeight", xRayWindow.getHeight());
        shader.end();
        */
    }

   /*
    // if touched then enabling shader
    public void isTouched(){
        stage.getBatch().setShader(null);
        if (Gdx.input.isTouched()){
            stage.getBatch().begin();
            stage.getBatch().setShader(null);
            stage.getBatch().draw(xRayWindow, Gdx.input.getX() - xRayWindow.getWidth() / 2f,WORLD_HEIGHT - Gdx.input.getY() + 10);
            stage.getBatch().setShader(shader);
            stage.getBatch().end();

            shader.begin();
            shader.setUniformf("u_PosX", Gdx.input.getX());
            shader.setUniformf("u_PosY", WORLD_HEIGHT - Gdx.input.getY() + xRayWindow.getHeight() + 20);
            shader.end();
        }
    }
    */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 1, 1, 0);
        stage.draw();
        stage.act();

        //isTouched();
    }

    @Override
    public void dispose() {
        //shader.dispose();
    }
}
