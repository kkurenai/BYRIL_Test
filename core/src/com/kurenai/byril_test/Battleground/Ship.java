package com.kurenai.byril_test.Battleground;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Ship extends TextureRegion{

    private boolean rotated;

    private int x, y;

    public Ship(TextureRegion shipTexture){
        setRegion(shipTexture);
    }

    public boolean isRotated(){
        return rotated;
    }

    public void setRotated(boolean isRotated){
        rotated = isRotated;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
