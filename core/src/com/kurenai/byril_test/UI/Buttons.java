package com.kurenai.byril_test.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Buttons {

    public static TextButton getTextButton(){
        TextButton button;
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("ui/buttons.atlas"));
        Skin skin = new Skin(textureAtlas);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button_default");
        textButtonStyle.down = skin.getDrawable("button_clicked");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"));

        button = new TextButton("DefaultText", textButtonStyle);
        return button;
    }
}
