package com.kurenai.byril_test.Auxiliary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.kurenai.byril_test.Battleground.Ship;

import static com.kurenai.byril_test.Auxiliary.Constants.SHIPS_SHEET_ATLAS_PATH;

public class ShipsProvider {

    private TextureAtlas textureAtlas;

    public ShipsProvider(){
        textureAtlas = new TextureAtlas(Gdx.files.internal(SHIPS_SHEET_ATLAS_PATH));
    }

    public Ship get1DeckShip() {
        return new Ship(textureAtlas.findRegion("1deckship"));
    }

    public Ship get2DecksShip() {
        return new Ship(textureAtlas.findRegion("2decksship"));
    }

    public Ship get3DecksShip() {
        return new Ship(textureAtlas.findRegion("3decksship"));
    }

    public Ship get4DecksShip() {
        return new Ship(textureAtlas.findRegion("4decksship"));
    }

    public Ship getShip(int numberOfDecks){
        switch (numberOfDecks){
            case 1: return get1DeckShip();
            case 2: return get2DecksShip();
            case 3: return get3DecksShip();
            case 4: return get4DecksShip();
            default: return null;
        }
    }

}
