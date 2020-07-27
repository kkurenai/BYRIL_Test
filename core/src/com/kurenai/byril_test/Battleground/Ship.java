package com.kurenai.byril_test.Battleground;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kurenai.byril_test.Auxiliary.Direction;

public class Ship extends TextureRegion{

    private Direction direction;

    private int x, y;

    public Ship(TextureRegion shipTexture){
        setRegion(shipTexture);
    }

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
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
